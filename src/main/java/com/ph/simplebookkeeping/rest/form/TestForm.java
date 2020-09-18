package com.ph.simplebookkeeping.rest.form;

import com.ph.simplebookkeeping.util.JacksonUtil;

import java.io.Serializable;

public class TestForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msg;

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
