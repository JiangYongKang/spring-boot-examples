package com.vincent.service;

/**
 * author: vincent
 * date: 2019-03-18 11:14
 * comment:
 */

public interface MessageService {

    /**
     * 发送一条点对点消息
     * @param message 消息内容
     */
    void sendToQueue(String message);

    /**
     * 发送一条主题消息
     * @param message 消息内容
     */
    void sendToTopic(String message);

}
