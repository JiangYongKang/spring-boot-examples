package com.pers.vincent.springboot.admin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Date;

/**
 * 配置文件管理类
 * <p>
 * Created by IDEA.
 * User: vincent
 * Date 2017/4/8
 *
 * @Configuration @Configuration 是 JavaConfig 形式的 IOC 容器
 *                相当于spring 的一系列 XML 配置文件，
 * @PropertySource 指定配置文件路径
 * @ConfigurationProperties 指定配置文件属性前缀
 */
@Configuration
@ConfigurationProperties(prefix = "com.pers")
@PropertySource("classpath:application.properties")
public class PropertiesConfig {
    private String create;
    private String user;
    private String date;
    private String version;

    private String secret;
    private int number;
    private long bignumber;
    private String uuid;

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getBignumber() {
        return bignumber;
    }

    public void setBignumber(long bignumber) {
        this.bignumber = bignumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "PropertiesConfig{" +
                "create='" + create + '\'' +
                ", user='" + user + '\'' +
                ", date='" + date + '\'' +
                ", version='" + version + '\'' +
                ", secret=" + secret +
                ", number=" + number +
                ", bignumber=" + bignumber +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
