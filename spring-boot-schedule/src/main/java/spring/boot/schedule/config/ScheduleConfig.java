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

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.addFixedDelayTask(new FixedDelayTask(() -> log.info("动态添加的定时任务，每秒钟执行一次"), Duration.ofMillis(1000), Duration.ZERO));
    }
}
