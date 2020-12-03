package com.lxg.service;

/**
 * @author LXG
 * @date 2020-7-8
 */

import com.aliyun.oss.model.OSSObjectSummary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * oss上传管理Service
 * @Create 2019-08-16  20:22
 */
public interface OssService {
    String upload(MultipartFile uploadFile);

    String delete(String objectName);

    List<OSSObjectSummary> list();

    void exportOssFile(OutputStream os, String objectName) throws IOException;
}