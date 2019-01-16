package com.vincent.springboot.configuration.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Author: vincent
 * Date: 2019-01-16 16:10:00
 * Comment:
 */

@Component
@ConfigurationProperties(prefix = "configuration.example")
public class ProjectMetaInfo {

    private String name;

    private String version;

    private String author;

    private String license;

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String name() {
        return name;
    }

    public String version() {
        return version;
    }

    public String author() {
        return author;
    }

    public String license() {
        return license;
    }
}
