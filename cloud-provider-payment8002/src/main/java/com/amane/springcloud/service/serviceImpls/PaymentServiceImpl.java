package com.amane.springcloud.service.serviceImpls;

import com.amane.springcloud.entities.Payment;
import com.amane.springcloud.mapper.PaymentMapper;
import com.amane.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author amane
 * @version 1.0
 * @ClassName PaymentServiceImpl
 * @description
 * @date 2022/8/8 18:18:01
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>implements PaymentService{
}
