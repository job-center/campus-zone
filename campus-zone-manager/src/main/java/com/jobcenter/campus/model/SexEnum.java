package com.jobcenter.campus.model;

import com.jobcenter.campus.common.common.BaseEntity;

/**
 * Created by xiayun on 30/3/17.
 */
public enum  SexEnum {
    MALE(0,"男"),
    FEMALE(1,"女");

    private int code;
    private String desc;

    SexEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static SexEnum parseSexEnum(Byte code){
        if (code == null){
            return null;
        }
        for (SexEnum item: SexEnum.values()) {
            if (item.getCode() == code.intValue()){
                return item;
            }
        }
        return null;
    }
}
