package com.pers.vincent.springboot.demo.domain;

/**
 * User: vincent
 * Date: 2017/4/8
 * Comment: 返回结果类
 */
public class Result {

    /**
     * 状态码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 数据
     */
    private Object data;

    public Result() {
    }

    public Result(int code, String message, String url, Object data) {
        this.code = code;
        this.message = message;
        this.url = url;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                ", data=" + data +
                '}';
    }
}
