package com.ph.simplebookkeeping.rest;

import com.ph.simplebookkeeping.entity.OrderLog;
import com.ph.simplebookkeeping.rest.form.TestForm;
import com.ph.simplebookkeeping.service.TestTableService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestRest {

    @Resource
    private TestTableService testTableService;

    @PostMapping("/testStr")
    public String testStr(@RequestBody TestForm form) {
        System.out.println(form);
        return form.toString();
    }

    @PostMapping("/addOrderLog")
    public String addOrderLog(@RequestBody OrderLog orderLog) {
        System.out.println(orderLog);
        int i = testTableService.addOrderLog(orderLog);
        if (i != 0) {
            return "success!";
        }
        return "fail!";
    }

}
