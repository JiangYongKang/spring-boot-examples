package com.person.vincent.server;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
public class PriceServiceImp implements PriceService {

    @Override
    public long getPrice(long orderId) {
        return 456;
    }

    @Override
    public List<String> getSupportedCurrencies() {
        return Arrays.asList("CNY", "USD", "EUR");
    }
}
