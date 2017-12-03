package com.person.vincent.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
public class PaymentServiceImp implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImp.class);

    @Override
    public void pay(long orderId, long price, Long sessionId) {
        logger.info("orderId = " + orderId + ", price = " + price + ",sessionId = " + sessionId);
    }
}
