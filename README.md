# 一、仓库说明

这是一个 Maven 多模块仓库，里面放了五个互相独立的 Spring Boot 小例子。每个例子只演示一件事：定时任务、异步方法、Redis、Swagger 文档，或者用 Docker 启动一个接口。

这个仓库准备归档，示例代码按现在的样子保留。这份说明来自对源码、配置和测试的静态阅读，整理时没有启动应用，也没有跑测试。

旧的总说明徽章上写着 Spring Boot 3.0.3。父工程 pom.xml 里 parent 的版本是 2.7.11。依赖和配置按 2.7 这一代来写，例如 Redis 的配置前缀是 spring.redis。

各子目录有自己的 README，按该模块源码说明启动类、配置和测试。这些说明原来是教程，标题从“SpringBoot（二）”编到“SpringBoot（七）”，中间没有（一）和（六）。现在按当前源码重写。下面各节是仓库总览。

# 二、模块一览

父工程的 packaging 是 pom，自己没有启动类。五个子模块各自打成 jar，都可以单独启动。父工程还引入了 spring-boot-maven-plugin，子模块执行打包时会打出可执行 jar。

公共依赖有 Lombok、Hutool 5.8.12，以及测试用的 spring-boot-starter-test。源码里用 Lombok 打日志。Hutool 只出现在父工程的依赖声明里。

五个模块都引入了 spring-boot-starter-web。单独启动时，内嵌 Web 容器使用默认端口 8080。只有 spring-boot-redis 带了自己的 application.properties。

| 目录 | 启动类 | 这个模块在做什么 |
| --- | --- | --- |
| spring-boot-schedule | SpringBootScheduleApplication | 用三种规则触发定时任务，并换成 8 线程的调度池 |
| spring-boot-async | Application | 用自定义线程池执行无返回值和带返回值的异步方法 |
| spring-boot-redis | Application | 读写 Redis 字符串、哈希，并监听一条 Stream |
| spring-boot-swagger | SpringBootSwagger | 用 Springfox 2.9.2 给一个 POST 接口出文档 |
| spring-boot-docker | Application | 提供一个返回当前时间的接口，并附带镜像和 Compose 文件 |

# 三、定时任务

相关代码在 spring-boot-schedule。ScheduleConfig 打开了 @EnableScheduling，并实现 SchedulingConfigurer，把调度器换成 ThreadPoolTaskScheduler。池大小是 8，线程名前缀是 run-schedule-，任务取消后会从队列里去掉。

ScheduledTasks 里有三个任务，触发间隔都是大约一秒，方法体只打一行日志。

1. asyncFixedRateTask 使用 fixedRate = 1000，从上一次开始时刻起满 1 秒再触发。方法名里带 async，方法上没有 @Async，它跑在上面的调度池里。
2. fixedDelayTask 使用 fixedDelay = 1000，上一次方法返回之后再等 1 秒。
3. cronTask 的 cron 表达式是 */1 * * * * ?，按秒触发。

任务类的包名是 spring.boot.schedule.schdule，schdule 少写了一个字母。

测试类 SpringBootScheduleApplicationTests 里的 contentLoad 是空方法，作用是把 Spring 上下文拉起来。上下文起来之后，上面三个定时任务会开始跑。测试本身没有断言。

# 四、异步任务

相关代码在 spring-boot-async。AsyncConfig 打开了 @EnableAsync，并实现 AsyncConfigurer。

线程池是 ThreadPoolTaskExecutor，核心线程 16，最大线程 32，队列容量 16，线程名前缀是 async-task-。没有返回值的异步方法抛异常时，异常处理器会把类名、方法名、参数和异常打成错误日志。返回 CompletableFuture 的方法如果失败，异常放在 Future 里，不走这个处理器。

AsyncTask 有三个方法。

1. taskOne 没有返回值，只打“执行异步任务，无返回值”。
2. taskTwo 和 taskThree 都返回已经完成的 CompletableFuture，内容是当时的毫秒时间，并打“执行异步任务，有返回值”。

测试在 AsyncTaskTests。

1. taskOneTest 调用 taskOne 后就结束，没有等待异步方法真正跑完。
2. taskTwoTest 同时调用 taskTwo 和 taskThree，用 CompletableFuture.allOf 等到两个都完成，再打“异步任务执行完成”。

ApplicationTests 里也有一个空的 contentLoad，用来加载上下文。这个模块没有 Controller。

# 五、Redis

相关代码在 spring-boot-redis。application.properties 把连接指到本机 6379 端口、0 号库，命令超时是 5000 毫秒。Lettuce 池的最大空闲和最大活跃都是 8，最小空闲是 0，借连接的 max-wait 写成 -1ms，表示一直等到有连接为止。客户端关闭超时是 100 毫秒。

RedisConfig 注册两个模板。StringRedisTemplate 的 key 和 hash key 用字符串序列化。另一个 Bean 的类型是 RedisTemplate<String, Serializable>，同样只设置了 key 和 hash key 的字符串序列化，value 使用模板默认的 JDK 序列化。

ApplicationTests 做了三件事。

1. stringRedisTemplateTest 把 MSG 写成 Hi，再读出来，要求两者相等。
2. redisTemplateTest 在哈希 HMSG 里写入字段 MSG、值 Hi，读出来转成字符串后再比较。
3. redisStreamTest 往流 MESSAGE_EVENT 追加一条 MSG=Hi 的消息。这个测试没有断言，也没有等待监听器处理。

Stream 的监听在应用启动时建好。RedisStreamConfig 先看键 MESSAGE_EVENT 在不在。键不在时，先追加一条字段名和值都是空字符串的记录，再以这个键创建消费组 MESSAGE_GROUP，然后按记录编号删掉那条占位记录。

这里调用的 createGroup 只传了键和组名。Spring Data Redis 2.7（由 Spring Boot 2.7.11 带入）里，这个方法把组建在流的最新位置，占位记录不会进入待消费范围。键已经存在时，这段代码不会再建组。

监听容器使用 16 个线程的固定线程池，每批最多拉 10 条，轮询间隔 1 秒，序列化用字符串。出错时打日志“Redis stream error!”。消费者名字是 DEFAULT_CONSUMER，组名是 MESSAGE_GROUP，偏移使用 ReadOffset.lastConsumed()，也就是从该组已消费位置之后继续读。容器在 Bean 创建过程中就启动。使用的是 receive，消息要等监听器自己确认。

MessageEventListener 收到消息后先打日志，再调用 acknowledge，传入的两个参数是 STREAM_KEY 和整条消息。STREAM_KEY 的值是 MESSAGE_EVENT。Spring Data Redis 2.7 里，字符串加整条 Record 的这个重载，第一个参数是消费组名字，流的键从消息里取。确认时用的组名是 MESSAGE_EVENT，监听时加入的组是 MESSAGE_GROUP，两边不一致。这样调用时，MESSAGE_GROUP 里的待确认记录去不掉。这是按方法签名读出来的结论，没有连上 Redis 验证。

应用关闭时，RedisStreamConfig 先停止监听容器，再最多等 5 秒让订阅结束。创建那个 16 线程池的代码没有配套的关闭调用。

# 六、Swagger 文档

相关代码在 spring-boot-swagger。依赖是 springfox-swagger2 和 springfox-swagger-ui，版本都是 2.9.2。SwaggerConfiguration 上有 @EnableSwagger2。文档标题是“基于 Swagger 构建的 Rest API 文档”，版本号 1.0。联系人、博客地址和邮箱写在 Contact 里。扫描范围是包 spring.boot.swagger 下的全部路径。

TestController 只暴露一个接口。

1. 路径是 POST /test，文档标签是“测试接口”，操作说明是“POST请求”。
2. 查询参数 name 必填，示例值是 vincent。
3. 方法收到 name 之后没有使用它，直接返回字符串 success。

Springfox 2.9.2 的文档页一般在 /swagger-ui.html，本模块没有另外写路径。这个版本依赖较早的路径匹配方式。Spring Boot 2.6 起默认改成了 PathPatternParser，而本模块没有配置 spring.mvc.pathmatch.matching-strategy。按这两个版本公开的兼容情况，文档组件有可能在启动时失败。这次归档没有启动验证。这个模块也没有测试类。

# 七、Docker 部署

相关代码在 spring-boot-docker。MessageController 提供 GET /messages，响应体是当前毫秒时间。除此之外没有业务。

Dockerfile 的基础镜像是 openjdk:8，和父工程的 Java 8 编译目标一致。它把 target 目录下名字匹配 spring-boot-docker-*.jar 的文件复制成 /opt/spring-boot-docker.jar，启动时堆上限和初始堆都是 128MB。构建镜像之前，需要先在这个模块里打出 jar。

docker-compose.yml 的文件版本是 3.3，里面有三个服务。

1. redis 使用镜像 redis:7，容器名 redis，把宿主机 16379 映射到容器 6379。
2. mysql 使用镜像 mysql:5.7，容器名 mysql，把宿主机 13306 映射到容器 3306。root 密码直接写在文件里，值是 123456。
3. application 在当前目录构建，镜像名 spring-boot-docker:latest，容器名 spring-boot-docker，把宿主机 18080 映射到容器 8080。它声明依赖 redis 和 mysql。

这个模块的 Java 代码没有 Redis 客户端，也没有数据源。Compose 里的 Redis 和 MySQL 没有被接口用到。这个模块没有测试类。

# 八、测试和持续集成

.github/workflows/maven-test.yml 的名字是 Java CI with Maven。向 master 推送，或者向 master 提拉取请求时，会在 ubuntu-latest 上跑。步骤是检出代码、安装最新版 Redis、执行 redis-cli ping、安装 Temurin JDK 17，然后执行 mvn -B package。

工作流用 JDK 17 编译，父工程把字节码目标定在 1.8。Redis 模块的测试会连 localhost:6379，所以工作流里先把 Redis 拉起来。Swagger 和 Docker 两个模块没有测试。定时任务模块只加载上下文。异步模块除了加载上下文，还会调用前面那两个测试方法。

.gitignore 忽略了 target、.mvn、IDE 工程文件，以及 mvnw 和 mvnw.cmd。仓库里没有 Maven Wrapper。

# 九、许可证

根目录 LICENSE 是 GNU General Public License 第 3 版的全文。
