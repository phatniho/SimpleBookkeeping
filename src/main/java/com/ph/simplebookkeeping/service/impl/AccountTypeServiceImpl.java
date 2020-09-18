package com.ph.simplebookkeeping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ph.simplebookkeeping.entity.AccountType;
import com.ph.simplebookkeeping.entity.SysUser;
import com.ph.simplebookkeeping.mapper.AccountTypeMapper;
import com.ph.simplebookkeeping.mapper.SysUserMapper;
import com.ph.simplebookkeeping.service.AccountTypeService;
import com.ph.simplebookkeeping.util.DateTimeUtil;
import com.ph.simplebookkeeping.util.IDUtil;
import com.ph.simplebookkeeping.util.StringUtils;
import com.ph.simplebookkeeping.util.SysException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    @Resource
    private AccountTypeMapper accountTypeMapper;
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public int insert(AccountType accountType) {
        String userId = accountType.getUserId();
        if (StringUtils.isEmpty(userId)) {
            SysException.throwException("用户id不能为空");
            return 0;
        }
        SysUser sysUser = sysUserMapper.selectById(userId);
        if (sysUser == null) {
            SysException.throwException("没有查到对应用户" + userId);
            return 0;
        }
        if (StringUtils.isEmpty(accountType.getAccountId())) {
            accountType.setAccountId(IDUtil.getUUID());
        }
        String now = DateTimeUtil.dateToStr(new Date(), DateTimeUtil.DATE_FORMAT_1);
        accountType.setCreateDate(now);
        accountType.setUpdateDate(now);
        return accountTypeMapper.insert(accountType);
    }

    @Override
    public List<AccountType> select(AccountType accountType) {
        QueryWrapper<AccountType> wrapper = new QueryWrapper<>();
        wrapper.setEntity(accountType);
        return accountTypeMapper.selectList(wrapper);
    }

    @Override
    public int update(AccountType accountType) {
        String now = DateTimeUtil.dateToStr(new Date(), DateTimeUtil.DATE_FORMAT_1);
        accountType.setUpdateDate(now);
        return accountTypeMapper.updateById(accountType);
    }

    @Override
    public int delete(AccountType accountType) {
        return accountTypeMapper.deleteById(accountType);
    }
}
