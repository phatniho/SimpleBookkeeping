package com.ph.simplebookkeeping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ph.simplebookkeeping.entity.SysUser;
import com.ph.simplebookkeeping.mapper.SysUserMapper;
import com.ph.simplebookkeeping.service.SysUserService;
import com.ph.simplebookkeeping.util.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public int insert(SysUser sysUser) {
        String now = DateTimeUtil.dateToStr(new Date(), DateTimeUtil.DATE_FORMAT_1);
        sysUser.setCreateDate(now);
        sysUser.setUpdateDate(now);
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public List<SysUser> select(SysUser sysUser) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.setEntity(sysUser);
        return sysUserMapper.selectList(wrapper);
    }

    @Override
    public int update(SysUser sysUser) {
        String now = DateTimeUtil.dateToStr(new Date(), DateTimeUtil.DATE_FORMAT_1);
        sysUser.setUpdateDate(now);
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public int delete(SysUser sysUser) {
        return sysUserMapper.deleteById(sysUser);
    }
}
