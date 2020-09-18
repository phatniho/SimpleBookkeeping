package com.ph.simplebookkeeping.service;

import com.ph.simplebookkeeping.entity.AccountType;
import com.ph.simplebookkeeping.entity.OrderLog;
import com.ph.simplebookkeeping.rest.form.OrderForm;
import com.ph.simplebookkeeping.vo.UserVO;

import java.util.List;

public interface OrderLogService {

    AccountType insert(OrderLog orderLog);

    List<OrderLog> select(OrderLog orderLog);

    int update(OrderLog orderLog);

    int delete(OrderLog orderLog);

    List<UserVO> selectList(OrderForm form);

}
