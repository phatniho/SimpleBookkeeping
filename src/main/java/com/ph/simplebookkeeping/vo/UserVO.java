package com.ph.simplebookkeeping.vo;

import com.ph.simplebookkeeping.util.JacksonUtil;

import java.util.List;

/**
 * 用户账单显示
 *
 * @author phatn
 * @date 2020/9/18 11:13
 */
public class UserVO {

    private String userId;

    private String userName;

    private String userPhone;

    private List<AccountVO> accountVOList;

    @Override
    public String toString() {
        return JacksonUtil.toJson(this);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<AccountVO> getAccountVOList() {
        return accountVOList;
    }

    public void setAccountVOList(List<AccountVO> accountVOList) {
        this.accountVOList = accountVOList;
    }
}
