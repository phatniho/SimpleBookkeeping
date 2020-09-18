package com.ph.simplebookkeeping.rest.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ph.simplebookkeeping.util.JacksonUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageForm {

    private Integer pageSize = 10;

    private Integer pageNum = 1;

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

}
