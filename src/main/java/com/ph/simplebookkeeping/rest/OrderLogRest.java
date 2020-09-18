package com.ph.simplebookkeeping.rest;

import com.ph.simplebookkeeping.entity.AccountType;
import com.ph.simplebookkeeping.entity.OrderLog;
import com.ph.simplebookkeeping.rest.form.OrderForm;
import com.ph.simplebookkeeping.service.OrderLogService;
import com.ph.simplebookkeeping.util.Json;
import com.ph.simplebookkeeping.vo.UserVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/orderLog")
public class OrderLogRest {

    @Resource
    private OrderLogService orderLogService;

    @PostMapping("/add")
    public Json add(@RequestBody OrderLog orderLog) {
        System.out.println(orderLog);
        AccountType result = orderLogService.insert(orderLog);
        if (result == null) {
            return Json.knownFail("fail!");
        }
        return Json.success(result);
    }

    @PostMapping("/find")
    public Json find(@RequestBody OrderForm form) {
        System.out.println(form);
        List<UserVO> resultList = orderLogService.selectList(form);
        return Json.success(resultList);
    }

}
