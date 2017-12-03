package com.person.vincent.server;

import java.util.List;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/26
 * Comment:
 */
public interface PriceService {
    long getPrice(long orderId);

    List<String> getSupportedCurrencies();
}
