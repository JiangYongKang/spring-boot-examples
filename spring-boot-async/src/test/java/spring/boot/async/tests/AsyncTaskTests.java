package spring.boot.async.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import spring.boot.async.task.AsyncTask;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * @author vincent
 * @since 1.0
 */
@Slf4j
@SpringBootTest
public class AsyncTaskTests {

    @Resource
    private AsyncTask asyncTask;

    @Test
    public void taskOneTest() {
        asyncTask.taskOne();
    }

    @Test
    public void taskTwoTest() {
        CompletableFuture<String> taskTwo = asyncTask.taskTwo();
        CompletableFuture<String> taskThree = asyncTask.taskThree();
        CompletableFuture.allOf(taskTwo, taskThree).join();
        log.info("异步任务执行完成");
    }

}
