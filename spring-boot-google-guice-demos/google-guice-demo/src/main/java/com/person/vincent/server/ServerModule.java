package com.person.vincent.server;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.util.List;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
public class ServerModule extends AbstractModule {

    // 注入需要注入的类
    @Override
    protected void configure() {
        bind(OrderService.class).to(OrderServiceImp.class);
        bind(PaymentService.class).to(PaymentServiceImp.class);
        bind(PriceService.class).to(PriceServiceImp.class);
    }

    @Provides
    @SessionId
    Long generateSessionId() {
        return System.currentTimeMillis();
    }

    @Provides
    List<String> getSupportedCurrencies(PriceService priceService) {
        return priceService.getSupportedCurrencies();
    }
}
