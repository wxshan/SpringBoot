package org.wxshan.springboot.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Administrator on 2017/9/6 0006.
 *
 * Logger 日志记录辅助类
 */
public class log {

    private static final Logger LOGGER = LogManager.getLogger();

    private log(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static Logger getLogger(){
        return LOGGER;
    }

    public static void trace(String msg){
        LOGGER.trace(msg);
    }

    public  static void debug(String msg){
        LOGGER.debug(msg);
    }

    public  static void info(String msg){
        LOGGER.info(msg);
    }

    public  static void warn(String msg){
        LOGGER.warn(msg);
    }

    public  static void error(String msg){
        LOGGER.error(msg);
    }



}
