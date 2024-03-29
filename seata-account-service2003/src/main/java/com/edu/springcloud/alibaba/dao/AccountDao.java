package com.edu.springcloud.alibaba.dao;

import com.edu.springcloud.alibaba.domain.CommonResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {
   void decrease(@Param("userId")Long userId, @Param("money") BigDecimal money);
}
