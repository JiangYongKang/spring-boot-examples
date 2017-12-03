package com.person.vincent.server;

import com.google.inject.Guice;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.List;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */

public class OrderServiceTest {

    // 成员属性注入
    @Inject
    private OrderService orderService;

    @Inject
    private Provider<List<String>> supportedCurrenciesProvider;

    // 成员属性注入
    @Before
    public void setUp() {
        Guice.createInjector(new ServerModule()).injectMembers(this);
    }

    @Test
    public void sendToPaymentTest() {
        orderService.sendToPayment(789L);
    }

    @Test
    public void testSupportedCurrencies() {
        System.out.println(supportedCurrenciesProvider.get());
    }
}
