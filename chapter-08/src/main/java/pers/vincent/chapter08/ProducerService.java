package pers.vincent.chapter08;

import javax.jms.Destination;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/7/23
 * Comment: 生产者
 */
public interface ProducerService {
    void sendMessage(Destination destination, String message);
}
