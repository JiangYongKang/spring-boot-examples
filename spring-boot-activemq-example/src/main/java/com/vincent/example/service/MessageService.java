package com.vincent.example.service;

/**
 * author: vincent
 * date: 2019-03-18 11:14
 * comment: 消息生产者
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

    /**
     * 发送延时消息
     * @param message 消息内容
     * @param delay   延时发送时间
     */
    void sendToQueue(String message, Long delay);

}
