package com.person.vincent.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/12/2
 * Comment:
 */
@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Resource
    private CustomerRepository customerRepository;

    public void saveCustomers() {
        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Smith"));
    }

    public void fetchAllCustomers() {
        logger.info("Customer found with findAll(): ");
        for (Customer customer : customerRepository.findAll()) {
            logger.info(customer.toString());
        }
    }

    public void fetchIndividualCustomers() {
        logger.info("Customer found with findByFirstName('Alice'): ");
        logger.info(customerRepository.findByFirstName("Alice").toString());

        logger.info("Customer found with findByFirstName('Smith'): ");
        for (Customer customer : customerRepository.findByLastName("Smith")) {
            logger.info(customer.toString());
        }
    }
}
