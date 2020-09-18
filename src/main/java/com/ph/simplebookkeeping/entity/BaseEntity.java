package com.ph.simplebookkeeping.entity;


import com.ph.simplebookkeeping.util.JacksonUtil;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;

    private String createDate;

    private String updateDate;

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
