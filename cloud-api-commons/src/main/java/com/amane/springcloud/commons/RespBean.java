package com.amane.springcloud.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    private Integer code;
    private String message;
    private Object obj;
    public static RespBean success(){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(),null);
    }

    public static RespBean success(String msg, Object obj){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), msg,obj);
    }

    public static RespBean success(Object obj){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), obj);
    }

    public static RespBean error(RespBeanEnum respBean){
        return new RespBean(respBean.getCode(), respBean.getMessage(), null);
    }

    public static RespBean error(String msg){
        return new RespBean(500,msg,null);
    }

    public static RespBean error(RespBeanEnum respBean,Object obj){
        return new RespBean(respBean.getCode(), respBean.getMessage(), obj);
    }

    public static RespBean error(Integer code, String msg){
        return new RespBean(code,msg,null);
    }
}
