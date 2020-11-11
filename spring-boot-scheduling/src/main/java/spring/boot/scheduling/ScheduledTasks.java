package spring.boot.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Author: vincent
 * Date: 2020-11-11 19:10
 * Comment:
 */

@Component
public class ScheduledTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(fixedRate = 1000)
    public void taskOne() {
        logger.info("--> task one");
    }

    @Scheduled(fixedDelay = 1000)
    public void taskTwo() {
        logger.info("--> task two");
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void taskThree() {
        logger.info("--> task three");
    }

    @Scheduled(cron = "* * * * * *")
    public void taskFour() {
        logger.info("--> task four");
    }
}
