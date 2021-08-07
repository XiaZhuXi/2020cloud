package com.edu.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private Long id;//
    private Long productId;//商品Id
    private Integer total;//商品总数
    private Integer used;//已用库存
    private Integer residue;//剩余库存

}
