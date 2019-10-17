package com.novel.handleException;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/08/22 17:27 
 */
public class UniteException extends RuntimeException {
    private Integer code;

    public UniteException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
