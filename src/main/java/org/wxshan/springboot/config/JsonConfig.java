package org.wxshan.springboot.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Administrator on 2017/9/6 0006.
 * 配置接口返回json时自动将对象中的时间对象转换为需要的字符串格式
 */
@JsonComponent
public class JsonConfig {

    public static class Serializer extends JsonSerializer<LocalDateTime> {
        private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value == null) {
                gen.writeString("");
            } else {
                gen.writeString(value.format(dateTimeFormatter));
            }
        }
    }

    public static class LocalDateSerializer extends JsonSerializer<LocalDate> {
        private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value == null) {
                gen.writeString("");
            } else {
                gen.writeString(value.format(dateFormatter));
            }
        }
    }
}
