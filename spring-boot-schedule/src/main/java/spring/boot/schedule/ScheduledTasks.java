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

    @Async
    @Scheduled(fixedDelay = 1000)
    public void fixedDelayTask() {
        log.info("固定延迟执行，距离上一次调用成功后一秒执行");
    }

    @Async
    @Scheduled(cron = "*/1 * * * * ?")
    public void cronTask() {
        log.info("使用 Cron 表达式，每秒执行一次");
    }
}
