## **注册zookeeper**

*****

1、springboot添加依赖：

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
</dependency>
```

2、主启动类加上注解@EnableDiscoveryClient

3、配置yml

```yaml
spring:
  cloud:
    zookeeper:
      connect-string: 114.55.234.204:2181
```

4、服务器安装zookeeper

https://www.cnblogs.com/caoweixiong/p/12325410.html

部署命令：

```
docker run -d -e TZ="Asia/Shanghai" -p 2181:2181 -v $PWD/data:/data --name zookeeper --restart always zookeeper
```

命令说明：

```
-e TZ="Asia/Shanghai" # 指定上海时区 
-d # 表示在一直在后台运行容器
-p 2181:2181 # 对端口进行映射，将本地2181端口映射到容器内部的2181端口
--name # 设置创建的容器名称
-v # 将本地目录(文件)挂载到容器指定目录；
--restart always #始终重新启动zookeeper
```

连接zk

```
docker run -it --rm --link zookeeper:zookeeper zookeeper zkCli.sh -server zookeeper
```

zk命令

```
# 查看zookeeper容器实例进程信息
docker top zookeeper

# 停止zookeeper实例进程
docker stop zookeeper

# 启动zookeeper实例进程
docker start zookeeper

# 重启zookeeper实例进程
docker restart zookeeper

# 查看zookeeper进程日志
docker logs -f zookeeper

# 杀死zookeeper实例进程
docker kill -s KILL zookeeper

# 移除zookeeper实例
docker rm -f -v zookeeper
```