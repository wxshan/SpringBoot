package org.wxshan.springboot.utils;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by Administrator on 2017/9/7 0007.
 * 自定义LocalDateTimeToString 转换器
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    private  final DateTimeFormatter dateTimeFormatter;

    public LocalDateTimeFormatter() {
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public LocalDateTimeFormatter(String pattern){
        dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalDateTime parse(String s, Locale locale) throws ParseException {
        return LocalDateTime.parse(s,dateTimeFormatter);
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        return localDateTime.format(dateTimeFormatter);
    }


}
