package org.wxshan.springboot.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/7 0007.
 */
public class CommonUtils {

    public  static  String datetimeParse(long seconds){
        return datetimeParse(seconds,"yyyy-MM-dd HH:mm:ss");
    }

    public static String datetimeParse(long seconds, String pattern){
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneId.of("Asia/Shanghai")).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String datetimeParse(LocalDateTime localDateTime,String pattern){
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String datetimeParse (LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static LocalDate dateParse(String day){
        return LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static boolean isEmpty(Object o){
        if (o == null){
            return true;
        }
        if (o instanceof String){
            if (o.toString().trim().equals("")){
                return true;
            }
        } else if (o instanceof List) {
            if (((List)o).size() == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map)o).size() == 0){
                return true;
            }
        } else if (o instanceof  Set) {
            if (((Set) o).size() == 0){
                return true;
            }
        } else if (o instanceof  Object[]) {
            if (((Object[]) o).length == 0){
                return true;
            }
        } else if (o instanceof  int[]){
            if (((int[])o).length == 0){
                return true;
            }
        } else if (o instanceof long[]){
            if (((long[])o).length == 0){
                return true;
            }
        }
        return false;
    }

    public static String timeToCron(LocalDateTime localDateTime) {

        String datetimeParse = datetimeParse(localDateTime);
        String HH = datetimeParse.substring(11, 13);
        String mm = datetimeParse.substring(14, 16);
        return HH + " : " + mm;
    }
}
