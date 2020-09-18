package com.ph.simplebookkeeping.vo;

import com.ph.simplebookkeeping.util.JacksonUtil;

import java.util.List;

/**
 * 账户账单显示
 *
 * @author phatn
 * @date 2020/9/18 11:13
 */
public class AccountVO {

    private String accountId;

    private String accountName;

    private String accountBalance;

    private List<OrderLogVO> orderLogVOList;

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<OrderLogVO> getOrderLogVOList() {
        return orderLogVOList;
    }

    public void setOrderLogVOList(List<OrderLogVO> orderLogVOList) {
        this.orderLogVOList = orderLogVOList;
    }
}
