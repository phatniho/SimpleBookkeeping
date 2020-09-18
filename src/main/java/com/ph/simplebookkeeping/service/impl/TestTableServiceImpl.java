package com.ph.simplebookkeeping.service.impl;

import com.ph.simplebookkeeping.entity.OrderLog;
import com.ph.simplebookkeeping.mapper.OrderLogMapper;
import com.ph.simplebookkeeping.service.TestTableService;
import com.ph.simplebookkeeping.util.DateTimeUtil;
import com.ph.simplebookkeeping.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TestTableServiceImpl implements TestTableService {

    @Resource
    private OrderLogMapper orderLogMapper;

    @Override
    public int addOrderLog(OrderLog orderLog) {
        String orderDate = orderLog.getOrderDate();
        if (StringUtils.isEmpty(orderDate)) {
            orderLog.setOrderDate(DateTimeUtil.dateToStr(new Date(), DateTimeUtil.DATE_FORMAT_1));
        }
        return orderLogMapper.insert(orderLog);
    }
}
