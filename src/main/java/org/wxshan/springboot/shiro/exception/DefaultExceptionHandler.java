package org.wxshan.springboot.shiro.exception;

import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.wxshan.springboot.utils.CustomException;
import org.wxshan.springboot.utils.MvcResult;



/**
 * Created by Administrator on 2017/9/6 0006.
 * controller层的控制器增强器（自动捕获所有controller层的指定异常）
 */
@ControllerAdvice
@Log4j2
public class DefaultExceptionHandler {

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void processUnauthorizedException(){
        System.out.println("权限未认证！");
    }

   @ExceptionHandler({AuthorizationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public  void  processAuthorizationException(){
        System.out.println("权限异常未认证！");
    }

     @ExceptionHandler({UnauthenticatedException.class})
    @ResponseStatus(HttpStatus.INSUFFICIENT_SPACE_ON_RESOURCE)
    public void processUnauthenticatedException(){
        System.out.println("身份未认证！");
    }

    /**
     * 捕获controller类中的异常，组装错误码及提示信息json返回前台
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public MvcResult throwException(Exception e){
        log.error(e.getMessage(),e);
        return new MvcResult(-1,"backend error");
    }

    /**
     * 生成校验字段返回结果方法
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public MvcResult handleInvalidRequestParameter(MethodArgumentNotValidException e){
        StringBuffer stringBuffer = new StringBuffer();
        e.getBindingResult().getAllErrors().forEach(
                objectError -> stringBuffer.append(objectError.getDefaultMessage()).append("\n")
        );
        return new MvcResult(-2,"backend validation error",stringBuffer.toString());
    }

    /**
     * 生成检验字段返回结果方法
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public  MvcResult handleInvalidRequestParameter(BindException e){
        StringBuffer stringBuffer = new StringBuffer();
        e.getBindingResult().getAllErrors().forEach(
                objectError -> stringBuffer.append(objectError.getDefaultMessage()).append("\n")
        );
        return new MvcResult(-2, "backend validation error", stringBuffer.toString());
    }

    /**
     * 捕获自定义运行时异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public MvcResult handleCustomerException(CustomException e){
        log.error(e.getMessage(),e);
        return new MvcResult(-3,e.getMessage());
    }
}
