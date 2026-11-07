# 一、这个模块做什么

这个模块演示 Spring 的定时任务。应用启动后，三个任务大约每秒各执行一次，方法里只打一行日志。

它是父工程的一个子模块，依赖 spring-boot-starter-web。没有 Controller，也没有 application.properties。单独启动时，内嵌 Web 容器占用默认端口 8080。仓库总览写在根目录 README.md。

这份说明按当前源码静态整理，没有启动应用。

# 二、调度线程池

调度配置在 ScheduleConfig，包名是 spring.boot.schedule.config。类上有 @Configuration 和 @EnableScheduling，并实现了 SchedulingConfigurer。

configureTasks 里创建 ThreadPoolTaskScheduler，初始化后交给 ScheduledTaskRegistrar。

1. 池大小是 8。
2. 线程名前缀是 run-schedule-。
3. setRemoveOnCancelPolicy(true)，任务取消后会从队列里去掉。

# 三、三个定时任务

任务写在 ScheduledTasks。包名是 spring.boot.schedule.schdule，schdule 少写了一个字母。类上是 @Component。

1. asyncFixedRateTask 使用 fixedRate = 1000。间隔从上次开始时刻算起，满 1 秒再触发。日志是“固定速率执行，每秒执行一次”。方法名里带 async，方法上没有 @Async，它跑在上面的调度池里。
2. fixedDelayTask 使用 fixedDelay = 1000。上一次方法返回之后再等 1 秒。日志是“固定延迟执行，距离上一次调用成功后一秒执行”。
3. cronTask 的表达式是 */1 * * * * ?。日志是“使用 Cron 表达式，每秒执行一次”。

三个方法都没有参数，也没有业务数据。

# 四、测试

测试类是 SpringBootScheduleApplicationTests，注解为 @SpringBootTest。唯一的测试方法 contentLoad 是空的，用来把 Spring 上下文拉起来。

上下文起来之后，上面三个定时任务会开始跑。测试没有断言。
