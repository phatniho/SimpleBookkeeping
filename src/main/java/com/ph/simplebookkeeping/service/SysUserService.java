package com.ph.simplebookkeeping.service;

import com.ph.simplebookkeeping.entity.AccountType;
import com.ph.simplebookkeeping.entity.SysUser;

import java.util.List;

public interface SysUserService {

    int insert(SysUser sysUser);

    List<SysUser> select(SysUser sysUser);

    int update(SysUser sysUser);

    int delete(SysUser sysUser);

}
