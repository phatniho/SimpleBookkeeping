package com.ph.simplebookkeeping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ph.simplebookkeeping.entity.AccountType;
import com.ph.simplebookkeeping.entity.OrderLog;
import com.ph.simplebookkeeping.entity.SysUser;
import com.ph.simplebookkeeping.mapper.AccountTypeMapper;
import com.ph.simplebookkeeping.mapper.OrderLogMapper;
import com.ph.simplebookkeeping.mapper.SysUserMapper;
import com.ph.simplebookkeeping.rest.form.OrderForm;
import com.ph.simplebookkeeping.service.AccountTypeService;
import com.ph.simplebookkeeping.service.OrderLogService;
import com.ph.simplebookkeeping.util.DateTimeUtil;
import com.ph.simplebookkeeping.util.IDUtil;
import com.ph.simplebookkeeping.util.StringUtils;
import com.ph.simplebookkeeping.util.SysException;
import com.ph.simplebookkeeping.vo.AccountVO;
import com.ph.simplebookkeeping.vo.OrderLogVO;
import com.ph.simplebookkeeping.vo.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderLogServiceImpl implements OrderLogService {

    @Resource
    private OrderLogMapper orderLogMapper;
    @Resource
    private AccountTypeMapper accountTypeMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private AccountTypeService accountTypeService;

    @Override
    public AccountType insert(OrderLog orderLog) {
        String accountId = orderLog.getAccountId();
        if (StringUtils.isEmpty(accountId)) {
            SysException.throwException("账户id不能为空");
        }
        AccountType accountType = accountTypeMapper.selectById(accountId);
        if (accountType == null) {
            SysException.throwException("没有这个账户:" + accountId);
        }
        return updateOrderLogAndAccountType(orderLog, accountType);
    }

    /**
     * 新增账单记录，并同步更新帐号余额
     *
     * @param orderLog    账单记录
     * @param accountType 账户
     * @return 执行结果
     */
    @Transactional
    protected AccountType updateOrderLogAndAccountType(OrderLog orderLog, AccountType accountType) {
        orderLog.setOrderId(IDUtil.getUUID());

        String accountBalance = accountType.getAccountBalance();
        if (StringUtils.isEmpty(accountBalance)) {
            accountBalance = "0";
        }
        String orderNum = orderLog.getOrderNum();
        if (StringUtils.isEmpty(orderNum)) {
            orderNum = "0";
        }
        BigDecimal balance = new BigDecimal(accountBalance);
        BigDecimal num = new BigDecimal(orderNum);
        accountBalance = String.valueOf(balance.add(num));
        orderLog.setCurrentBalance(accountBalance);
        accountType.setAccountBalance(accountBalance);

        String now = DateTimeUtil.dateToStr(new Date(), DateTimeUtil.DATE_FORMAT_1);
        if (StringUtils.isEmpty(accountType.getCreateDate())) {
            accountType.setCreateDate(now);
        }
        if (StringUtils.isEmpty(orderLog.getOrderDate())) {
            orderLog.setOrderDate(now);
        }
        orderLog.setCreateDate(now);
        orderLog.setUpdateDate(now);
        accountType.setUpdateDate(now);


        int result = orderLogMapper.insert(orderLog);
        if (result == 0) {
            SysException.throwException("账单新增失败");
        }
        result = accountTypeMapper.updateById(accountType);
        if (result == 0) {
            SysException.throwException("账户修改失败");
        }
        return accountType;
    }

    @Override
    public List<OrderLog> select(OrderLog orderLog) {
        QueryWrapper<OrderLog> wrapper = new QueryWrapper<>();
        wrapper.setEntity(orderLog);
        return orderLogMapper.selectList(wrapper);
    }

    @Override
    public int update(OrderLog orderLog) {
        String now = DateTimeUtil.dateToStr(new Date(), DateTimeUtil.DATE_FORMAT_1);
        orderLog.setUpdateDate(now);
        return orderLogMapper.updateById(orderLog);
    }

    @Override
    public int delete(OrderLog orderLog) {
        return orderLogMapper.deleteById(orderLog);
    }

    @Override
    public List<UserVO> selectList(OrderForm form) {
        List<UserVO> resultList = new ArrayList<>();
        if (form == null) {
            form = new OrderForm();
        }
        if (StringUtils.isEmpty(form.getUserId())) {
            List<SysUser> sysUsers = sysUserMapper.selectList(null);
            if (sysUsers == null) {
                SysException.throwException("没有查到任何用户");
            }
            for (SysUser sysUser : sysUsers) {
                UserVO userVO = dealUserVO(sysUser, form);
                resultList.add(userVO);
            }
        } else {
            String userId = form.getUserId();
            SysUser sysUser = sysUserMapper.selectById(userId);
            if (sysUser == null) {
                SysException.throwException("没有查到对应用户：" + userId);
            }
            UserVO userVO = dealUserVO(sysUser, form);
            resultList.add(userVO);
        }
        return resultList;
    }

    /**
     * 根据用户查询账户及账单记录
     *
     * @param sysUser 用户
     * @param form    参数
     * @return 用户账户账单记录
     */
    private UserVO dealUserVO(SysUser sysUser, OrderForm form) {
        UserVO userVO = new UserVO();
        String userId = sysUser.getUserId();
        userVO.setUserId(userId);
        userVO.setUserName(sysUser.getUserName());
        userVO.setUserPhone(sysUser.getUserPhone());

        AccountType accountType = new AccountType();
        accountType.setUserId(userId);
        String accountId = form.getAccountId();
        if (StringUtils.isNotEmpty(accountId)) {
            accountType.setAccountId(accountId);
        }
        List<AccountType> accountTypeList = accountTypeService.select(accountType);
        if (accountTypeList == null) {
            SysException.throwException("没有符合条件的账户：" + accountType);
        }
        List<AccountVO> accountVOList = new ArrayList<>();
        for (AccountType type : accountTypeList) {
            AccountVO accountVO = dealAccountVO(type, form);
            accountVOList.add(accountVO);
        }
        userVO.setAccountVOList(accountVOList);
        return userVO;
    }

    /**
     * 根据账户查询账单记录
     *
     * @param type 账户
     * @param form 参数
     * @return 账户账单记录
     */
    private AccountVO dealAccountVO(AccountType type, OrderForm form) {
        String accountId = type.getAccountId();
        AccountVO accountVO = new AccountVO();
        accountVO.setAccountId(accountId);
        accountVO.setAccountName(type.getAccountName());
        accountVO.setAccountBalance(type.getAccountBalance());
        String nowDate = DateTimeUtil.dateToStr(new Date(), DateTimeUtil.DATE_FORMAT_2);
        String startDate = form.getStartDate();
        if (StringUtils.isEmpty(startDate)) {
            startDate = nowDate;
        }
        String endDate = form.getEndDate();
        if (StringUtils.isEmpty(endDate)) {
            endDate = nowDate;
        }
        List<OrderLogVO> orderLogVOList = orderLogMapper.selectOrderLogByAccountId(accountId, startDate, endDate);
        if (orderLogVOList != null && !orderLogVOList.isEmpty()) {
            accountVO.setOrderLogVOList(orderLogVOList);
        }
        return accountVO;
    }

}
