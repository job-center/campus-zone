package com.jobcenter.campus.common.common;

/**
 * Created by xiayun on 1/4/17.
 */
public enum  ResultEnum {
    SUCCESS(true,1,"操作成功"),
    FAIL(false,0,"操作失败");

    private boolean success;
    private int code;
    private String message;

    ResultEnum(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public static ResultEnum parseResultEnum(boolean result){
        for (ResultEnum item : ResultEnum.values()){
            if (item.isSuccess() == result){
                return item;
            }
        }
        return null;
    }
}
