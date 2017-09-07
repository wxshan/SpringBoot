package org.wxshan.springboot.utils;

/**
 * Created by Administrator on 2017/9/7 0007.
 */
public class CustomException extends RuntimeException {

    public CustomException(String message){
        super(message);
    }
}
