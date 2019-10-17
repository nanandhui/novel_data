package com.novel.Tools;

import java.io.Serializable;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/08/22 17:23 
 */
public class ResultTool implements Serializable {
    private int code;
    private String message;
    private Object data;

    public ResultTool(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultTool(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultTool() {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
