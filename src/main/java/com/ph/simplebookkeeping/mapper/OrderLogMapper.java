package com.ph.simplebookkeeping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ph.simplebookkeeping.entity.OrderLog;
import com.ph.simplebookkeeping.vo.OrderLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderLogMapper extends BaseMapper<OrderLog> {

    double countBalanceByAccountId(@Param("accountId") String accountId);

    List<OrderLogVO> selectOrderLogByAccountId(@Param("accountId") String accountId, @Param("startDate") String startDate, @Param("endDate") String endDate);

}
