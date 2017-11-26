package com.person.vincent.server;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
public interface PaymentService {
    void pay(long orderId, long price, Long sessionId);
}
