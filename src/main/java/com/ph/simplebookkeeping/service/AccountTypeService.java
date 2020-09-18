package com.ph.simplebookkeeping.service;

import com.ph.simplebookkeeping.entity.AccountType;

import java.util.List;

public interface AccountTypeService {

    int insert(AccountType accountType);

    List<AccountType> select(AccountType accountType);

    int update(AccountType accountType);

    int delete(AccountType accountType);

}
