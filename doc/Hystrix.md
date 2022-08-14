## Hystrix

*****

1、引入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
</dependency>
```

2、服务降级（自定义或者发生异常）

简介：设置自身调用超时时间的峰值,峰值内可以正常运行,  超过了需要有兜底的方法处理,做服务降级fallback

```java
@Override
@HystrixCommand(fallbackMethod = "getInfoTimeoutFallback", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000" //最长调用时间为3s
        )})
public String getInfoTimeOut(String id) {
    int seconds = 5;
    try {
        TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return "**************调用成功"+"\t"+"线程池："+Thread.currentThread().getName()+" port:"+serverPort+" id:"+id+" 耗时："+seconds+"s";
}

@Override
public String getInfoTimeoutFallback(String id) {
    return "系统繁忙";
}
```

在需要降级的方法中添加@HystrixCommand注解，其中的fallbakMethod中的值为降级时调用的方法，commandPropeties中为降级的条件，注意服务的fallback方法除了方法名称和服务不同之外其他参数如返回数据类型、参数类型都相同。

在主启动类添加注解@EnableCircuitBreaker激活

***服务端***

1、修改yaml

```yaml
feign:
  hystrix:
    enabled: true
```

2、主启动类添加注解@EnableHystrix

```java
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderFeignHystrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignHystrixMain80.class, args);
    }
}
```



***设置默认fallback方法***

添加注解@DefaultProperties,并且给方法加上@HystrixCommand注解

```java
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallback") //添加注解
public class OrderController {

    @Resource
    private ConsumerFeignService consumerFeignService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String ok(@PathVariable("id") String id){
        return consumerFeignService.hystrixOk(id);
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "timeoutFallback",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2110")
//    })
    @HystrixCommand //注解不可缺少
    public String timeout(@PathVariable("id") String id){
        return consumerFeignService.hystrixTimeout(id);
    }

    public String timeoutFallback(String id){
        return "80timeout系统繁忙"+id;
    }

    public String globalFallback(){
        return "系统繁忙，请稍后再试！";
    }
}
```



***客户端设置fallback默认方法（通过继承service）***

1、继承客户端调用的service，并且实现方法，并且加上@Component注解

```java
/**
 * @author xt
 * @date 2022/8/14 14:23:24
 */
@Component
public class ConsumerFeignFallback implements ConsumerFeignService{
    @Override
    public String hystrixOk(String id) {
        return "hystrixOk--fallback" + id;
    }

    @Override
    public String hystrixTimeout(String id) {
        return "hystrixTimeout--fallback" + id;
    }
}
```

2、在@FeignClient注解中添加属性fallback来指定类

```java
@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = ConsumerFeignFallback.class)
public interface ConsumerFeignService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String hystrixOk(@PathVariable("id") String id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String hystrixTimeout(@PathVariable("id") String id);

}
```

3、yaml配置

![image-20220814143315509](..\imgs\image-20220814143315509.png)



***服务熔断***

****

**原理图：**

![image-20220814162405869](..\imgs\image-20220814162405869.png)

1、给服务端方法service上加上注解和添加commandProperties，其中commandProperties的配置信息和默认值在类HystrixCommandProperties中可查

```java
@HystrixCommand(fallbackMethod = "circuitBreakPaymentFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //10次为有效次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //在10s内的10次
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "70") //错误率达70%
    })
	//上述配置的作用是设置在10s内10次请求中错误率大于等于7次的时候触发熔断，熔断的作用是暂时关闭该服务的调用，即在熔断期间调用circuitBreakPayment的时候总是返回的是调用circuitBreakPaymentFallback的结果，经过一段时间以后若该服务的错误率减少，熔断器恢复，服务正常使用
    @Override
    public String circuitBreakPayment(Integer number) {
        if (number < 0){
            throw new RuntimeException("数字不可小于0");
        }
        String uuid = UUID.randomUUID().toString();
        String result = number+"  "+uuid;
        log.info(result);
        return result;
    }

    @Override
    public String circuitBreakPaymentFallback(Integer id) {
        return "FALLBACK"+id;
    }
```

![image-20220814162818796](..\imgs\image-20220814162818796.png)

​																					（即使是整数返回的还是调用了fallback方法）

***dashboard***

****

添加依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

主启动类添加注解@EnableHystrixDashboard

如果不行就添加

```java
@Bean
public ServletRegistrationBean getServlet(){
    HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(streamServlet);
    servletRegistrationBean.setLoadOnStartup(1);
    servletRegistrationBean.addUrlMappings("/hystrix.stream");
    servletRegistrationBean.setName("HystrixMetricsStreamServlet");
    return servletRegistrationBean;
}
```



注意：

![image-20220814020920601](..\imgs\image-20220814020920601.png)
