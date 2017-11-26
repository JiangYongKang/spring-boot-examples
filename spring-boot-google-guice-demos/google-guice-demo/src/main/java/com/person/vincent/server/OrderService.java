package com.person.vincent.server;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
public interface OrderService {
    void sendToPayment(long OrderId);
}
