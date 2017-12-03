package com.person.vincent.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/12/2
 * Comment:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Resource
    private CustomerService customerService;

    @Test
    public void saveCustomersTest() {
        customerService.saveCustomers();
    }

    @Test
    public void fetchAllCustomersTest() {
        customerService.fetchAllCustomers();
    }

    @Test
    public void fetchIndividualCustomersTest() {
        customerService.fetchIndividualCustomers();
    }
}
