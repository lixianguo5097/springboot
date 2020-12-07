package com.lxg.common.utils;

import com.lxg.common.exception.MyException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

@Component
public class HttpRequestUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static String sendPostRequest(String url1, String param) {
        try {
            // Configure and open a connection to the site you will send the request
            URL url = new URL(url1);
            URLConnection urlConnection = url.openConnection();
            // 设置doOutput属性为true表示将使用此urlConnection写入数据
            urlConnection.setDoOutput(true);
            // 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
            urlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            // 得到请求的输出流对象
            OutputStream out = urlConnection.getOutputStream();
            // 把数据写入请求的Body
            out.write(param.getBytes("UTF-8"));
            out.flush();
            out.close();

            // 从服务器读取响应
            InputStream inputStream = urlConnection.getInputStream();
            String encoding = urlConnection.getContentEncoding();
            return IOUtils.toString(inputStream, StringUtils.isEmpty(encoding) ? "UTF-8" : encoding);
        } catch (Exception e) {
            LOGGER.error("HttpRequest发送请求失败, url: " + url1, " ,param: " + param, e);
            throw MyException.newException("系统异常，请联系管理员");
        }

    }

    public static String sendGetRequest(String url) throws IOException {
        return sendGetRequest(url, 30 * 1000);
    }

    public static String sendGetRequest(String url1, Integer timeout) throws IOException {
        try {
            URL url = new URL(url1);
            URLConnection urlConnection = url.openConnection();

            // 从服务器读取响应
            urlConnection.setConnectTimeout(timeout);
            InputStream inputStream = urlConnection.getInputStream();
            String encoding = urlConnection.getContentEncoding();
            return IOUtils.toString(inputStream, encoding == null ? "utf-8" : encoding);
        } catch (Exception e) {
            LOGGER.error("HttpRequest sendGetRequest 发送请求失败: " + url1, e);
            throw MyException.newException("系统异常，请联系管理员");
        }
    }

}
