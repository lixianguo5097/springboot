package com.lxg.xss;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LXG
 * @date 2020-4-17
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {
    //这里添加不需要过滤的json格式参数的url
    private static List<String> unUrlPatterns = new ArrayList<String>(){{
        add("/xss/userss");
    }};

    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(value);
        if (value != null) {
            if (!unUrlPatterns.contains(request.getRequestURI())) {
                String encodedValue = StringEscapeUtils.escapeHtml4(value);
                jsonGenerator.writeString(encodedValue);
            } else {
                jsonGenerator.writeString(value);
            }
        }
    }
}