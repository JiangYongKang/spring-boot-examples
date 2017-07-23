package pers.vincent.chapter08;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/7/23
 * Comment:
 */
public interface ConsumerService {

    String receiveQueue(String text);

    String receiveTopic(String text);
}
