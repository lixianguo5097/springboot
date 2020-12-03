package com.lxg.controller;

import com.aliyun.oss.model.OSSObjectSummary;
import com.lxg.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author lxg
 * @desc
 * @date 2019-07-31 11:31
 */
@Controller
public class OssController {
	@Autowired
	private OssService ossService;

	/**
	 * @desc 文件上传到oss
	 * @Param uploadFile
	 */
	@RequestMapping("oss/file/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile uploadFile) {
		return this.ossService.upload(uploadFile);
	}

	/**
	 * @desc 根据文件名删除oss上的文件
	 * @Param objectName
	 */
	@RequestMapping("oss/file/delete")
	@ResponseBody
	public String delete(@RequestParam("fileName") String objectName) {
		return this.ossService.delete(objectName);
	}

	/**
	 * @desc 查询oss上的所有文件
	 * @return List<OSSObjectSummary>
	 * @Param
	 */
	@RequestMapping("oss/file/list")
	@ResponseBody
	public List<OSSObjectSummary> list() {
		return this.ossService.list();
	}

	/**
	 * @desc 根据文件名下载oss上的文件
	 * @Param objectName
	 */
	@RequestMapping("oss/file/download")
	@ResponseBody
	public void download(@RequestParam("fileName") String objectName, HttpServletResponse response) {
		try {
			// 通知浏览器以附件形式下载
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(objectName.getBytes(), "ISO-8859-1"));
			this.ossService.exportOssFile(response.getOutputStream(), objectName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
