# SpringBoot（二）：定时任务

在开发过程中，很多需求都需要定时任务才能完成。SpringBoot 提供了简单快捷的实现方式，我们只需要添加一些注解就可以使用。那么什么场景下需要使用到定时任务呢？例如：整点发放优惠券，每天自动更新收益，定时生成统计报表等等。

### 启用定时任务

通过在启动类上添加 `@EnableScheduling` 注解即可启动定时任务。

```java
@EnableScheduling
@SpringBootApplication
public class SpringBootScheduling {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootScheduling.class, args);
    }
}
```

### 设置定时任务

```java
package spring.boot.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Author: vincent
 * Date: 2020-11-11 19:10
 * Comment:
 */

@Slf4j
@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 1000)
    public void fixedRateTask() {
        log.info("固定速率执行，每秒执行一次");
    }

}
```

`@Scheduled` 可以设置两种类型的定时任务，一种是基于固定值间隔的定时任务，另一种是基于 `cron` 表达式的定时任务。`cron` 直接传表达式就可以了。例如：

- `@Scheduled(cron = "*/5 * * * * ?")` 每秒钟执行一次
- `@Scheduled(fixedRate = 1000)` 上一次开始执行时间点之后一秒再执行
- `@Scheduled(fixedDelay = 1000)` 上一次执行完毕时间点之后一秒再执行
- `@Scheduled(initialDelay = 1000, fixedRate = 1000)` ：第一次延迟一秒后执行，之后按 `fixedRate` 的规则每秒执行一次

### 定时任务异步执行

因为有时候需要执行的定时任务会很多，如果是串行执行会带来一些问题，比如一个很耗时的任务阻塞住了，一些需要短周期循环执行的任务也会卡住，所以可以配置一个线程池来并行执行定时任务，通过 `@EnableAsync` 来开启异步定时任务，通过 `@Async` 来设定方法异步执行。

```Java
package spring.boot.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author vincent
 * @since 1.0
 */

@EnableAsync
@EnableScheduling
@Configuration
public class ScheduleConfig {

    /**
     * 配置定时任务的线程池
     * 默认情况下 SpringBoot 的定时任务是单线程的，如果需要多线程并发执行，需要配置一个线程池，并且在定时任务上添加 @Async 注解
     * 1. CorePoolSize 核心线程数量，核心线程不会被回收
     * 2. MaxPoolSize 最大线程数量
     * 3. QueueCapacity 执行线程队列大小
     * 4. KeepAliveSeconds 线程空闲时长。如果超过该时长，非核心线程就会被回收
     * 5. RejectedExecutionHandler 设置线程的拒绝策略
     * - AbortPolicy 放弃任务并抛出 RejectedExecutionException 异常（默认）
     * - CallerRunsPolicy 由调用线程处理该任务
     * - DiscardPolicy 放弃任务，但是不抛出异常。可以配合这种模式进行自定义的处理方式
     * - DiscardOldestPolicy 放弃队列最早的未处理任务，然后重新尝试执行任务
     *
     * @return ThreadPoolTaskExecutor
     */
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("schedule-exec-");
        executor.setCorePoolSize(16);
        executor.setMaxPoolSize(64);
        executor.setQueueCapacity(8);
        executor.setKeepAliveSeconds(30);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
```

```Java
package spring.boot.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Author: vincent
 * Date: 2020-11-11 19:10
 * Comment:
 */

@Slf4j
@Component
public class ScheduledTasks {

    @Async
    @Scheduled(fixedRate = 1000)
    public void fixedRateTask() {
        log.info("固定速率执行，每秒执行一次");
    }

}
```

### 动态添加定时任务

上面提到的添加定时任务的方式，存在这两个问题：每次修改执行频率都要改代码，重启服务。无法提供定时任务的启停、修改接口。通过实现 `SchedulingConfigurer` 接口，可以动态的去控制定时任务。

```Java
package spring.boot.schedule.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.FixedDelayTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.Duration;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author vincent
 * @since 1.0
 */

@Slf4j
@EnableAsync
@EnableScheduling
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.addFixedDelayTask(new FixedDelayTask(() -> log.info("动态添加的定时任务，每秒钟执行一次"), Duration.ofMillis(1000), Duration.ZERO));
    }
}

```
