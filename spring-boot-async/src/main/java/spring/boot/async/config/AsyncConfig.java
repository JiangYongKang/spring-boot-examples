package spring.boot.async.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author vincent
 * @since 1.0
 */

@Slf4j
@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor defaultExecutor = new ThreadPoolTaskExecutor();
        defaultExecutor.setMaxPoolSize(32);
        defaultExecutor.setCorePoolSize(16);
        defaultExecutor.setQueueCapacity(16);
        defaultExecutor.setThreadNamePrefix("async-task-");
        defaultExecutor.initialize();
        return defaultExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (exception, method, args) -> {
            log.error("异步任务 {} 执行异常，参数：{}", method.getDeclaringClass().getName() + "#" + method.getName(), args, exception);
        };
    }

}
