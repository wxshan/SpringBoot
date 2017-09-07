package org.wxshan.springboot.base;


import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.wxshan.springboot.utils.LocalDateTimeFormatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/6 0006.
 */
public class BaseController {

    /**
     * 生成校验字段返回结果方法
     * @param bindingResult
     * @return
     */
    protected Map<String, Object> generateValidErrorInfo(BindingResult bindingResult){
        List<FieldError> errors = bindingResult.getFieldErrors();
        StringBuffer msgs = new StringBuffer();
        for (FieldError err : errors) {
            msgs.append( err.getDefaultMessage() + "\n");
        }
        return generateFailureResult(-2,"backend validation error",msgs.toString());
    }

    /**
     * 生成正常返回结果（不带data）方法
     * @return
     */
    protected Map<String, Object> generateSuccessResult(){
        return generateFailureResult(0,"ok");
    }

    /**
     * 生成正常返回结果（带data方法）
     * @param data
     * @return
     */
    protected Map<String, Object> generateSuccessResult(Object data){
        return generateFailureResult(0,"ok",data);
    }

    /**
     * 错误情况下生成返回结果(不带data)方法
     * @param errcode 错误返回码
     * @param errmsg 错误信息
     * @return
     */
    protected Map<String, Object> generateFailureResult(int errcode,String errmsg){
        Map<String, Object> result = new HashMap<>();
        result.put("errcode", errcode);
        result.put("errmsg",errmsg);
        return result;
    }

    /**
     * 错误情况下生成返回结果（带data方法）
     * @param errcode 错误返回码
     * @param errmsg  错误信息
     * @param data    内容实体
     * @return
     */
    protected Map<String, Object> generateFailureResult(int errcode,String errmsg,Object data){
        Map<String, Object> result = new HashMap<>();
        result.put("errcode", errcode);
        result.put("errmsg", errmsg);
        return result;
    }

    /**
     * 将controller接受到属性对象中为空字符串的属性字段转为null
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
        binder.addCustomFormatter(new LocalDateTimeFormatter());
    }
}
