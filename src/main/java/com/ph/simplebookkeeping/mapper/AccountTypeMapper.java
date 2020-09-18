package com.ph.simplebookkeeping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ph.simplebookkeeping.entity.AccountType;
import com.ph.simplebookkeeping.vo.AccountVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountTypeMapper extends BaseMapper<AccountType> {

    List<AccountVO> selectAccountListByUserId(@Param("userId") String userId);

}
