package com.jobcenter.campus.common.common;

/**
 * Created by xiayun on 8/4/17.
 */
public enum StatusEnum {
    STATUS_DELETE((byte) -1, "删除"),
    STATUS_OPEN((byte) 1, "开启"),
    STATUS_CLOSE((byte) 0, "冻结");

    private byte code;
    private String desc;

    StatusEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static StatusEnum parseStatusEnum(byte code) {
        for (StatusEnum item : StatusEnum.values()) {
            if (code == item.getCode()) {
                return item;
            }
        }
        return null;
    }
}
