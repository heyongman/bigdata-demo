package com.he.utils;

/**
 * 处理结果的工具类
 */
public class Result {
    /**
     * 状态
     */
    private boolean state;
    /**
     * 对应状态的提示信息
     */
    private String message;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
