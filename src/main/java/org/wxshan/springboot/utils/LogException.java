package org.wxshan.springboot.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Administrator on 2017/9/6 0006.
 */
public class LogException {
    public static String getTrace(Throwable t){
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}
