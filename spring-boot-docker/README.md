# SpringBoot（五）：使用 Docker 部署

对开发和运维人员来说，最希望的就是一次创建或配置，可以在任意地方正常运行。使用 Docker 可以通过定制应用镜像来实现持续集成、持续交付、部署。由于 Docker 确保了执行环境的一致性，使得应用的迁移更加容易。Docker 可以在很多平台上运行，无论是物理机、虚拟机、公有云、私有云，甚至是笔记本，其运行结果是一致的。因此用户可以很轻易的将在一个平台上运行的应用，迁移到另一个平台上，而不用担心运行环境的变化导致应用无法正常运行的情况。

### Dockerfile

Dockerfile 是一个文本文件，其内包含了一条条的 指令（Instruction），每一条指令构建一层，因此每一条指令的内容，就是描述该层应当如何构建。以当前基础的 SpringBoot 项目为例：

```Docker
# 基于 openjdk 8 进行构建
FROM openjdk:8

# 复制打包好的 jar 文件到容器的 /opt 目录中
COPY target/spring-boot-docker-*.jar /opt/spring-boot-docker.jar

# 在容器中执行 java -Xmx128m -Xms128m -jar /opt/spring-boot-docker.jar
CMD ["java", "-Xmx128m", "-Xms128m", "-jar", "/opt/spring-boot-docker.jar"]
```

### 通过 Dockerfile 构建镜像

```Bash
$ docker build -t spring-boot-docker:latest .
Sending build context to Docker daemon  22.18MB
Step 1/3 : FROM openjdk:8
 ---> b273004037cc
Step 2/3 : COPY target/spring-boot-docker-*.jar /opt/spring-boot-docker.jar
 ---> Using cache
 ---> 818928c2b53e
Step 3/3 : CMD ["java", "-Xmx128m", "-Xms128m", "-jar", "/opt/spring-boot-docker.jar"]
 ---> Using cache
 ---> 13b7f0829bf8
Successfully built 13b7f0829bf8
Successfully tagged spring-boot-docker:latest
```

### 查看镜像列表

```Bash
$ docker image ls
REPOSITORY           TAG       IMAGE ID       CREATED         SIZE
spring-boot-docker   latest    9bc67e2909cf   5 seconds ago   548MB
openjdk              8         b273004037cc   9 months ago    526MB
```

### 运行 Docker 镜像

```Bash
$ docker run -p 18080:8080 -d spring-boot-docker:latest
2e40edce9cefb75842cf195a820655eaf809bb50e77a3a49ef3b5bb967e87e27
```

```Bash
$ docker container ls
CONTAINER ID   IMAGE                       COMMAND                  CREATED          STATUS          PORTS                                         NAMES
2e40edce9cef   spring-boot-docker:latest   "java -Xmx128m -Xms1…"   11 seconds ago   Up 10 seconds   0.0.0.0:18080->8080/tcp, :::18080->8080/tcp   tender_banac
```

### 使用 Docker Compose 进行构建

Compose 项目是 Docker 官方的开源项目，负责实现对 Docker 容器集群的快速编排。Compose 定位是 「定义和运行多个 Docker 容器的应用（Defining and running multi-container Docker applications）」，其前身是开源项目 Fig。它允许用户通过一个单独的 docker-compose.yml 模板文件（YAML 格式）来定义一组相关联的应用容器为一个项目。以当前项目为例：

```Yml
# 指定 Docker Compose 的版本
version: "3.3"

# 定义当前项目用到的服务
services:

  # Redis 镜像
  redis:
    image: redis:7
    container_name: redis
    ports:
      - 16379:6379

  # MySQL 镜像
  mysql:
    image: mysql:5.7
    container_name: mysql
    ports:
      - 13306:3306
    environment:
      - MYSQL_ROOT_PASSWORD=123456

  # SpringBoot 应用
  application:
    build: .
    image: spring-boot-docker:latest
    container_name: spring-boot-docker
    ports:
      - 18080:8080

    # 依赖的镜像
    depends_on:
      - redis
      - mysql
```

### 运行 Docker Compose

在 docker-compose.yml 目录下执行 docker-compose up -d 命令，通过日志可以观察到 Docker Compose 会根据 Yml 文件中配置的顺序进行构建：拉取 Redis 镜像 -> 拉取 MySQL 镜像 -> 打包 SpringBoot 镜像 -> 启动 MySQL 镜像 -> 启动 Redis 镜像 -> 启动 SpringBoot 镜像。

```Bash
$ docker-compose up -d
Creating network "spring-boot-docker_default" with the default driver
Pulling redis (redis:7)...
7: Pulling from library/redis
26c5c85e47da: Pull complete
39f79586dcf2: Pull complete
79c71d0520e5: Pull complete
60e988668ca1: Pull complete
873c3fc9fdc6: Pull complete
50ce7f9bf183: Pull complete
Digest: sha256:f50031a49f41e493087fb95f96fdb3523bb25dcf6a3f0b07c588ad3cdbe1d0aa
Status: Downloaded newer image for redis:7
Pulling mysql (mysql:5.7)...
5.7: Pulling from library/mysql
e83e8f2e82cc: Pull complete
0f23deb01b84: Pull complete
f5bda3b184ea: Pull complete
ed17edbc6604: Pull complete
33a94a6acfa7: Pull complete
f153bd2953e4: Pull complete
ab532edfb813: Pull complete
c76bdfe4f3d0: Pull complete
8a7ffe2f2551: Pull complete
857ada4fbbcc: Pull complete
b7c508404c3c: Pull complete
Digest: sha256:f57eef421000aaf8332a91ab0b6c96b3c83ed2a981c29e6528b21ce10197cd16
Status: Downloaded newer image for mysql:5.7
Building application
Step 1/3 : FROM openjdk:8
8: Pulling from library/openjdk
001c52e26ad5: Pull complete
d9d4b9b6e964: Pull complete
2068746827ec: Pull complete
9daef329d350: Pull complete
d85151f15b66: Pull complete
52a8c426d30b: Pull complete
8754a66e0050: Pull complete
Digest: sha256:86e863cc57215cfb181bd319736d0baf625fe8f150577f9eb58bd937f5452cb8
Status: Downloaded newer image for openjdk:8
 ---> b273004037cc
Step 2/3 : COPY target/spring-boot-docker-*.jar /opt/spring-boot-docker.jar
 ---> caf004c07798
Step 3/3 : CMD ["java", "-Xmx128m", "-Xms128m", "-jar", "/opt/spring-boot-docker.jar"]
 ---> Running in 77dacfecc541
Removing intermediate container 77dacfecc541
 ---> be6f4d7e787f

Successfully built be6f4d7e787f
Successfully tagged spring-boot-docker:latest
WARNING: Image for service application was built because it did not already exist. To rebuild this image you must use `docker-compose build` or `docker-compose up --build`.
Creating mysql ... done
Creating redis ... done
Creating spring-boot-docker ... done
```

### 查看运行中的容器

```Bash
$ docker container ls
CONTAINER ID   IMAGE                       COMMAND                  CREATED          STATUS          PORTS                                                    NAMES
23525def2d5e   spring-boot-docker:latest   "java -Xmx128m -Xms1…"   46 seconds ago   Up 44 seconds   0.0.0.0:18080->8080/tcp, :::18080->8080/tcp              spring-boot-docker
392f9531ce3d   redis:7                     "docker-entrypoint.s…"   47 seconds ago   Up 45 seconds   0.0.0.0:16379->6379/tcp, :::16379->6379/tcp              redis
9d805ece3bee   mysql:5.7                   "docker-entrypoint.s…"   47 seconds ago   Up 45 seconds   33060/tcp, 0.0.0.0:13306->3306/tcp, :::13306->3306/tcp   mysql
```

### 使用 Docker Compose 停止容器

```Bash
$ docker-compose down
Stopping spring-boot-docker ... done
Stopping redis              ... done
Stopping mysql              ... done
Removing spring-boot-docker ... done
Removing redis              ... done
Removing mysql              ... done
Removing network spring-boot-docker_default
```
