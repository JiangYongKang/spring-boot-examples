# 一、这个模块做什么

这个模块演示 @Async。一个方法只打日志，另外两个方法返回已经完成的 CompletableFuture。线程池和未捕获异常的日志写在配置类里。

它是父工程的一个子模块，依赖 spring-boot-starter-web。没有 Controller，也没有 application.properties。单独启动时，内嵌 Web 容器占用默认端口 8080。仓库总览写在根目录 README.md。

这份说明按当前源码静态整理，没有启动应用。

# 二、线程池和异常日志

配置类是 AsyncConfig，包名是 spring.boot.async.config。类上有 @Configuration 和 @EnableAsync，并实现了 AsyncConfigurer。

getAsyncExecutor 返回 ThreadPoolTaskExecutor，返回前调用了 initialize。

1. 核心线程 16，最大线程 32，队列容量 16。
2. 线程名前缀是 async-task-。

getAsyncUncaughtExceptionHandler 用来处理没有返回值的异步方法抛出的异常。日志正文是“异步任务 类名#方法名 执行异常，参数：”，后面附上参数和异常。返回 CompletableFuture 的方法如果失败，异常放在 Future 里，不走这个处理器。

# 三、三个异步方法

任务类是 AsyncTask，包名是 spring.boot.async.task，类上是 @Component。三个 public 方法都标了 @Async。

1. taskOne 没有返回值。日志是“执行异步任务，无返回值”。
2. taskTwo 返回 CompletableFuture<String>。内容是调用当时的毫秒时间，日志是“执行异步任务，有返回值”。
3. taskThree 和 taskTwo 的写法相同，也是立刻完成的 Future。

方法体里没有睡眠，也没有故意抛异常。

# 四、测试

ApplicationTests 使用 @SpringBootTest。contentLoad 是空方法，用来加载上下文。

AsyncTaskTests 按字段注入 AsyncTask，有两个测试。

1. taskOneTest 调用 taskOne 后测试方法就结束，没有等待异步日志打出来。
2. taskTwoTest 同时调用 taskTwo 和 taskThree，用 CompletableFuture.allOf 等到两个都完成，再打“异步任务执行完成”。
