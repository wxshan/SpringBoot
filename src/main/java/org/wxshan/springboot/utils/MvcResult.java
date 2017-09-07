package org.wxshan.springboot.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Administrator on 2017/9/7 0007.
 * 对前台返回结果的封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MvcResult {
    private int errcode;
    private String errmsg;
    private Object data;

    public MvcResult(int errcode, String errmsg){
        this.errcode = errcode;
        this.errmsg = errmsg;
    }
}
