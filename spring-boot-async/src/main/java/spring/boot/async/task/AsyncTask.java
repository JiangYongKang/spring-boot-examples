package spring.boot.async.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author vincent
 * @since 1.0
 */
@Slf4j
@Component
public class AsyncTask {

    @Async
    public void taskOne() {
        log.info("执行异步任务，无返回值");
    }

    @Async
    public CompletableFuture<String> taskTwo() {
        log.info("执行异步任务，有返回值");
        return CompletableFuture.completedFuture(String.valueOf(System.currentTimeMillis()));
    }

    @Async
    public CompletableFuture<String> taskThree() {
        log.info("执行异步任务，有返回值");
        return CompletableFuture.completedFuture(String.valueOf(System.currentTimeMillis()));
    }

}
