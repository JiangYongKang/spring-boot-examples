package com.person.vincent;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/11/30
 * Comment:
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ElasticSearchDemoApplication.class);
    }
}
