package com.example.tacademy.retrofit1.dao;

import java.util.List;

/**
 * Created by Tacademy on 2015-11-12.
 */
public class GetObject {
    public Integer code = null;
    public Boolean success = null;
    public List<Result> result = null;
    public Object err = null;
    public String msg = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Object getErr() {
        return err;
    }

    public void setErr(Object err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}