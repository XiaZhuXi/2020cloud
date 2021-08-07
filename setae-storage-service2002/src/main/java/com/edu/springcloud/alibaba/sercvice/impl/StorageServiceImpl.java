package com.edu.springcloud.alibaba.sercvice.impl;

import com.edu.springcloud.alibaba.dao.StorageDao;
import com.edu.springcloud.alibaba.sercvice.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class StorageServiceImpl implements StorageService {
private static final Logger LOGGER=LoggerFactory.getLogger(StorageServiceImpl.class);
    @Resource
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("<<<<---STORAGE 开始调用库存，扣减商品--->>>>");
        storageDao.decrease(productId,count);
        LOGGER.info("<<<<<----STORAGE，扣减商品SUCCESS--->>>>");

    }
}
