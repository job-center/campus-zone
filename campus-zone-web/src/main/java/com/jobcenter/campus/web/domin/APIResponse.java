package com.jobcenter.campus.web.domin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jobcenter.campus.common.common.BaseEntity;
import com.jobcenter.campus.common.common.ResultEnum;
import com.jobcenter.campus.common.utils.JsonMapper;

/**
 * 接口返回类
 * Created by xiayun on 27/3/17.
 */
public class APIResponse<T> extends BaseEntity {

    public static final int SUCCESS = 1;
    public static final int FAILED = 0;

    private String msg;
    private int code;
    private Boolean success;

    public APIResponse() {
    }

    public APIResponse(ResultEnum result) {
        if (result != null) {
            this.msg = result.getMessage();
            this.code = result.getCode();
            this.success = result.isSuccess();
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public String getMsg() {
        return msg;
    }

    public APIResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public APIResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public APIResponse setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public T getData() {
        return data;
    }

    public APIResponse setData(T data) {
        this.data = data;
        return this;
    }

    public String toJson(){
        return JsonMapper.buildNonDefaultBinder().toJson(this);
    }
}
