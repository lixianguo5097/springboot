package com.lxg.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件上传下载
 * 访问 http://localhost:8080/file.html进入测试页面
 * @author LXG
 * @date 2019-11-1
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Value("${filepath}")
    private String filepath;

    /**
     * 处理文件上传
     */
    @PostMapping(value = "/upload")
    public String uploading(@RequestParam("file") MultipartFile file) {
        File targetFile = new File(filepath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try (FileOutputStream out = new FileOutputStream(filepath + file.getOriginalFilename());) {
            out.write(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文件上传失败!");
            return "uploading failure";
        }
        log.info("文件上传成功!");
        return "uploading success";
    }

    @GetMapping("/download")
    public void downLoad(HttpServletResponse response) {
        String filename = "JAVA核心知识点整理.pdf";
        String filePath = "D:/file";
        File file = new File(filePath + "/" + filename);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 BufferedInputStream bis = new BufferedInputStream(fis)) {
                response.setContentType("application/octet-stream");
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(filename, "utf8"));
                byte[] buffer = new byte[1024];
                //输出流
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 可以用作web页面勾选文件列表，将勾选的文件/文件夹打包下载
     *
     * 父文件夹与子文件夹不能有同名文件，否则会报java.util.zip.ZipException: duplicate entry
     *
     * 因为它是遍历文件夹内所有的子文件夹，将所有文件打包到同一个zip中，同名文件会有冲突
     *
     * @param response
     */
    @GetMapping("/downloadZip")
    public void downloadZip(HttpServletResponse response)  {
        List<String> list = new ArrayList<>();
        list.add("D:\\chandao\\xampp\\zentaoep\\bin");
        downloadZip(response, list,"test.zip");
    }

    public static void downloadZip(HttpServletResponse response, List<String> fileList, String fileName) {

        //生成压缩包
        try ( // 关联response输出流，直接将zip包文件内容写入到response输出流并下载
              ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {

            //zip文件作为附件下载
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Type", "application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            // 循环读取文件路径集合，获取每一个文件的路径
            for (String fp : fileList) {
                // 根据文件路径创建文件
                File file = new File(fp);
                // 将每一个文件写入zip文件包内，即进行打包
                zipFile(file, zos);
                // 刷新缓冲区
                response.flushBuffer();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 封装压缩文件的方法
     *
     * @param inputFile
     * @param zipoutputStream
     */
    public static void zipFile(File inputFile, ZipOutputStream zipoutputStream) {
        try  {
            // 判断文件是否存在
            if (inputFile.exists()) {
                // 判断是否属于文件，还是文件夹
                if (inputFile.isFile()) {
                    // 创建输入流读取文件
                    FileInputStream fis = new FileInputStream(inputFile);
                    BufferedInputStream bis = new BufferedInputStream(fis);

                    // 将文件写入zip内，即将文件进行打包
                    ZipEntry ze = new ZipEntry(inputFile.getName());
                    // 获取文件名
                    zipoutputStream.putNextEntry(ze);
                    // 写入文件的方法，同上
                    byte[] b = new byte[1024];
                    long l = 0;
                    while (l < inputFile.length()) {
                        int j = bis.read(b, 0, 1024);
                        l += j;
                        zipoutputStream.write(b, 0, j);
                    }
                    bis.close();
                    fis.close();
                } else {
                    // 如果是文件夹，则使用穷举的方法获取文件，写入zip
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], zipoutputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
