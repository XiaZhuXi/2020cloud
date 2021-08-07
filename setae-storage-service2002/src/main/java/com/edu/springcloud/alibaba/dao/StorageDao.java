package com.edu.springcloud.alibaba.dao;

import com.edu.springcloud.alibaba.domain.CommonResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;
@Mapper
public interface StorageDao {
    void  decrease(@Param("productId")Long productId, @Param("count")Integer count);
}
