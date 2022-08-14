## 使用openFeign

****

1、引入依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

2、配置yaml

```yaml
server:
  port: 80

eureka:
  client:
    service-url:
      #defaultZone: http://localhost:7001/eureka
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
    register-with-eureka: false
```

3、主启动类添加注解

```java
@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
```

4、创建service，并且加上注解

```java
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface ConsumerFeignService {

    @GetMapping("/payment/select/{id}")
    public RespBean getPayment(@PathVariable("id") Long id);
}
```

其中@FeignClient里面的值为注册中心注册的服务名称

@GetMapping中的值为访问服务端的url地址



5、编写controller并且调用service

```java
@RestController
@Slf4j
public class OrderController {

    @Resource
    private ConsumerFeignService consumerFeignService;

    @GetMapping("/consumer/feign/get/{id}")
    public RespBean getPayment(@PathVariable("id") Long id){
        return consumerFeignService.getPayment(id);
    }
}
```

***5、客户端请求服务端超时（默认1s）！！！！！***

修改yaml文件

```yaml
ribbon:
  ReadTimeout: 5000 #设置等待时间为5s
  ConnectTimeout: 5000 #设置等待时间为5s
```

6、日志打印

*日志打印级别*

![image-20220813151033066](..\imgs\image-20220813151033066.png)

①添加bean

```java
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
}
```

②配置yaml

```yaml
logging:
  level:
    #feign监控哪个类，以什么级别记录日志
    com.amane.springcloud.service.ConsumerFeignService: debug
```

效果：

![image-20220813151836961](..\imgs\image-20220813151836961.png)

