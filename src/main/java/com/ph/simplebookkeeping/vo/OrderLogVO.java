package com.ph.simplebookkeeping.vo;

import com.ph.simplebookkeeping.util.JacksonUtil;

/**
 * 账单显示结果
 *
 * @author phatn
 * @date 2020/9/18 11:13
 */
public class OrderLogVO {

    private String orderName;

    private String orderNum;

    private String currentBalance;

    private String orderDate;

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(String currentBalance) {
        this.currentBalance = currentBalance;
    }
}
