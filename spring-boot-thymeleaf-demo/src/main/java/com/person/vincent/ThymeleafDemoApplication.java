package com.person.vincent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@SpringBootApplication
@Controller
public class ThymeleafDemoApplication {

    @RequestMapping("/login")
    public String index(ModelMap map) {
        map.put("title", "Index");
        map.put("head", "产品列表");
        Map<String, Object> product = new HashMap<>();
        product.put("id", UUID.randomUUID());
        product.put("name", "手机");
        product.put("price", 19.3);
        product.put("time", new Date());
        List<Object> products = new ArrayList<>();
        products.add(product);
        products.add(product);
        map.put("products", products);
        return "/index";
    }

    public static void main(String[] args) {
        SpringApplication.run(ThymeleafDemoApplication.class, args);
    }
}
