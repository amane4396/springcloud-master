## 使用Ribbon+RestTemplate

****

1、添加依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
</dependency>
```

2、添加RestTemplate组件

```java
@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
```

***其中的@LoadBalanced必须加上***

3、添加负载均衡策略

```java
@Configuration
public class MyRule {
    @Bean
    public IRule iRule(){
        return new RandomRule();//根据需要更换不同的策略
    }
}
```

***这个Ribbon配置类不可以放在springboot启动类同级的目录或者子目录中***



4、在springboot启动类中加上注解

```java
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MyRule.class)
```

其中 name为注册中心的微服务名称，configuration为步骤3中所定义的负载均衡策略



