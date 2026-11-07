# 一、这个模块做什么

这个模块演示把一个 Spring Boot 接口打成镜像，并用 Compose 一起描述 Redis 和 MySQL。Java 代码本身只有一个返回当前时间的接口。

它是父工程的一个子模块，依赖 spring-boot-starter-web。没有 application.properties，也没有测试类。仓库总览写在根目录 README.md。

这份说明按当前源码和容器文件静态整理，没有构建镜像，也没有启动容器。

# 二、接口

MessageController 的类路径是 /messages。index 方法映射 GET /messages，响应体是 System.currentTimeMillis() 的结果。

没有请求参数，也没有连接外部服务。不走容器、直接启动时，接口在默认端口 8080。

# 三、镜像

Dockerfile 做三件事。

1. 基础镜像是 openjdk:8，和父工程的 Java 8 编译目标一致。
2. 把 target 目录下名字匹配 spring-boot-docker-*.jar 的文件复制为 /opt/spring-boot-docker.jar。
3. 启动命令把堆上限和初始堆都设为 128MB。

父工程配置了 spring-boot-maven-plugin。在本模块执行打包后，target 里会有可执行 jar，镜像复制的就是这个文件。文件里没有 EXPOSE，容器端口沿用应用默认的 8080。

# 四、Compose

docker-compose.yml 的文件版本是 3.3，定义了三个服务。

1. redis 使用镜像 redis:7，容器名 redis，把宿主机 16379 映射到容器 6379。
2. mysql 使用镜像 mysql:5.7，容器名 mysql，把宿主机 13306 映射到容器 3306。root 密码直接写在文件里，值是 123456。
3. application 在当前目录构建，镜像名 spring-boot-docker:latest，容器名 spring-boot-docker，把宿主机 18080 映射到容器 8080。它用 depends_on 声明依赖 redis 和 mysql。

本模块的 Java 代码没有 Redis 客户端，也没有数据源。Compose 里的 Redis 和 MySQL 没有被 /messages 用到。从宿主机访问这个接口时，端口是 18080。
