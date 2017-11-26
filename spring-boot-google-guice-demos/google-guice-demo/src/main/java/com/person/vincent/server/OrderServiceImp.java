package com.person.vincent.server;

import javax.inject.Inject;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
public class OrderServiceImp implements OrderService {

    // Dependencies
    private final PriceService priceService;
    private final PaymentService paymentService;
    private final SessionManager sessionManager;

    // States
    private Long ordersPaid = 0L;

    // 构造函数注入
    @Inject
    public OrderServiceImp(PriceService priceService, PaymentService paymentService, SessionManager sessionManager) {
        this.priceService = priceService;
        this.paymentService = paymentService;
        this.sessionManager = sessionManager;
    }

    public void sendToPayment(long orderId) {
        long price = priceService.getPrice(orderId);
        paymentService.pay(orderId, price, sessionManager.getSessionIdProvider());
        ordersPaid = ordersPaid + 1;
    }
}
