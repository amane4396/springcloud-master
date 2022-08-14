## 注册consul

*****

1、添加依赖

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```

2、主启动类加上注解@EnableDiscoveryClient

3、修改yaml

```yaml
server:
  port: 8006

spring:
  application:
    name: cloud-provider-payment
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        service-name: ${spring.application.name}
```

4、去官网下载consul

https://www.consul.io/downloads

5、启动consul

```
./consul agent -dev
```

6、ip:8500访问consul

![image-20220810182323234](C:\Users\amane\AppData\Roaming\Typora\typora-user-images\image-20220810182323234.png)

