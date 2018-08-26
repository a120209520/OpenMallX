package org.ppl.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.ppl.mall.util.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传Controller
 * @author Smith
 *
 */

@Controller
public class FileController {
	
	@Value("${fastdfs_url}")
	private String FASTDFS_SERVER;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map<String, Object> uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile) {
		Map<String, Object> result = new HashMap<>();
		try {
			FastDFSClient client = new FastDFSClient("classpath:conf/fast-dfs.properties");
			String fileName = uploadFile.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
			String url = FASTDFS_SERVER + client.uploadFile(uploadFile.getBytes(), fileType);
			System.out.println(url);
			result.put("error", 0);
			result.put("url", url);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", 1);
			result.put("message", "上传失败!");
			return result;
		}
	}
}
