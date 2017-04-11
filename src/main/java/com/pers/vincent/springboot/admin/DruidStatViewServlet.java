package com.pers.vincent.springboot.admin;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * User: vincent
 * Date: 2017/4/11
 * Comment: Druid 内置监控页面的配置，訪問路徑 http://127.0.0.1/druid/index.html
 */
@WebServlet(urlPatterns = "/druid/*", initParams = {
        /**
         * IP 白名单，没有配置或者为空时，则允许所有访问。多个 IP 以英文逗号隔开
         */
        @WebInitParam(name = "allow", value = "127.0.0.1"),

        /**
         * IP 黑名单，共同存在时黑名单优先级高于白名单
         */
        @WebInitParam(name = "dent", value = "192.168.16.111"),

        /**
         * 用户名
         */
        @WebInitParam(name = "loginUsername", value = "root"),

        /**
         * 密码
         */
        @WebInitParam(name = "loginPassword", value = "root"),

        /**
         * 禁用页面上的 "Reset All" 功能
         */
        @WebInitParam(name = "resetEnable", value = "false")
})
public class DruidStatViewServlet extends StatViewServlet {
}
