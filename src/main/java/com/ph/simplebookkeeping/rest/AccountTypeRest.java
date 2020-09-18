package com.ph.simplebookkeeping.rest;

import com.ph.simplebookkeeping.entity.AccountType;
import com.ph.simplebookkeeping.service.AccountTypeService;
import com.ph.simplebookkeeping.util.Json;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/accountType")
public class AccountTypeRest {

    @Resource
    private AccountTypeService accountTypeService;

    @PostMapping("/add")
    public Json add(@RequestBody AccountType accountType) {
        System.out.println(accountType.toString());
        int i = accountTypeService.insert(accountType);
        if (i == 0) {
            return Json.knownFail("fail!");
        }
        return Json.success("success!");
    }

    @PostMapping("/find")
    public Json find(@RequestBody AccountType accountType) {
        System.out.println(accountType.toString());
        List<AccountType> accountTypeList = accountTypeService.select(accountType);
        if (accountTypeList == null || accountTypeList.isEmpty()) {
            return Json.knownFail("no account type found!");
        }
        return Json.success(accountTypeList);
    }

    @PostMapping("/update")
    public Json update(@RequestBody AccountType accountType) {
        System.out.println(accountType.toString());
        int i = accountTypeService.update(accountType);
        if (i == 0) {
            return Json.knownFail("fail!");
        }
        return Json.success("success!");
    }

    @PostMapping("/delete")
    public Json delete(@RequestBody AccountType accountType) {
        System.out.println(accountType.toString());
        int i = accountTypeService.delete(accountType);
        if (i == 0) {
            return Json.knownFail("fail!");
        }
        return Json.success("success!");
    }


}
