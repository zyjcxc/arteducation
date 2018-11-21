package com.edu.admin.server.format;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author xuqiang
 * @Title: DecimalFormat转换价格格式化类
 * @date 2018/2/8下午1:55
 */
public class DataJsonSerializer extends JsonSerializer<BigDecimal> {

    private DecimalFormat df = new DecimalFormat("##0.00");

    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provide)
            throws IOException, JsonProcessingException {
        BigDecimal b = new BigDecimal(value.doubleValue() * 100);
        jgen.writeString(formatDouble5(b.doubleValue()));
    }

    /**
     * 如果只是用于程序中的格式化数值然后输出，那么这个方法还是挺方便的。
     * 应该是这样使用：System.out.println(String.format("%.2f", d));
     *
     * @param d
     * @return
     */
    public static String formatDouble5(double d) {
        return String.format("%.2f", d);
    }

}
