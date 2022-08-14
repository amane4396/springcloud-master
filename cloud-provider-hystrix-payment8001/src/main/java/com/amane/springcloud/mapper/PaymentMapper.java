package com.amane.springcloud.mapper;

import com.amane.springcloud.entities.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author amane
 * @version 1.0
 * @ClassName PaymentMapper
 * @description
 * @date 2022/8/8 18:19:08
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}
