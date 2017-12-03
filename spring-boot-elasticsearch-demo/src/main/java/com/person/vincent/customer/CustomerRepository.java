package com.person.vincent.customer;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/12/2
 * Comment:
 */
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}
