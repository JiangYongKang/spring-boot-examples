package com.pers.vincent.springboot.admin.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * User: vincent
 * Date: 2017/4/11
 * Comment: 定时器类
 */
@Service
public class StatusCheckScheduler {

    private static Long count = 1L;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @Scheduled 定义该方法为一个定时器类
     * fixedRate: 执行间隔时间，单位毫秒
     */
    @Scheduled(fixedRate = 1000)
    public void tasks() {
        logger.info("调度任务 >>> [tasks] >>> 第[" + count++ + "]次执行 ... ");
    }

}
