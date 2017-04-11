package com.pers.vincent.springboot.admin;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * User: vincent
 * Date: 2017/4/11
 * Comment: Druid 过滤器
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {

        /**
         * 设置忽略的资源
         */
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
})
public class DruidStatFilter extends WebStatFilter {
}
