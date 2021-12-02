package com.lxg.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author LXG
 * @date 2021-11-4
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileUrlController {

    /**
     * 根据url读取文件
     *
     * @throws IOException
     */
    @GetMapping("/readFileByUrl")
    public void readFileByUrl() throws IOException {
        String url = "http://img.baidu.com/hunter/alog/alog.min.js";
        URL getUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        connection.disconnect();
    }

    /**
     * 利用 commonio 库下载文件，
     *
     * @param url
     * @param saveDir
     * @param fileName
     */
    public static void downloadByApacheCommonIO(String url, String saveDir, String fileName) throws IOException {
        FileUtils.copyURLToFile(new URL(url), new File(saveDir, fileName));
    }
}
