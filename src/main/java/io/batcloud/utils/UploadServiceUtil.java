package io.batcloud.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;

import com.alibaba.fastjson.JSONObject;
import com.zz.common.log.LogService;
import com.zz.common.util.FileUtils;

public class UploadServiceUtil {

	/**
	 * 
	 * @param appkey
	 * @param path
	 * @param prefix
	 * @param suffix
	 * @return
	 *//*
	public static FileUploadResponse uploadFile(String appkey,String path,String prefix,String suffix) throws Exception{
		 
		byte[]  fileData = FileUtils.readBytes(new File(path));
		return uploadFile(appkey,fileData, prefix, suffix); 
	}
	
	*//**
	 * 
	 * @param appkey
	 * @param path
	 * @param prefix
	 * @param suffix
	 * @return
	 *//*
	public static FileUploadResponse uploadFile(String appkey,byte[]  fileData,String prefix,String suffix)throws Exception {
		FileUploadResponse response = null;
		try { 
			
			RequestData requestData = new RequestData(1, fileData, suffix, prefix);
			FileUploadRequest fileRequest = new FileUploadRequest(requestData);
			
			response = FileServiceClient.uploadFile(appkey, fileRequest);
			
		} catch (Exception e) {
			LogService.error("UploadServiceUtil.uploadFile:"+ e.getMessage(),e);
			throw new Exception("UploadServiceUtil.uploadFile:"+ e.getMessage());
		}
		
		if(response != null && response.getCode() == 0) {
			return response;
		} else {
			String msg = "UploadServiceUtil.uploadFile ,appkey:"+ appkey + ",prefix:"+ prefix + ",suffix:"+ suffix + ",response:"+  (response!=null ? JSONObject.toJSONString(response):"null");
			LogService.error(msg);
			throw new Exception(msg);
		}
	}
	
	*//** 原图上传到S3,不裁剪,可以不上传图片
	 * 
	 * @param appkey
	 * @param path
	 * @param prefix
	 * @param suffix
	 * @return
	 *//*
	public static ImageUploadResponse uploadImage(String appkey,String path,String prefix,String suffix) throws Exception {
		 
		byte[]  fileData = FileUtils.readBytes(new File(path));
		return  uploadImage(appkey, fileData, prefix, suffix); 
	}
	
	public static ImageUploadResponse uploadImage(String appkey,byte[]  fileData ,String prefix,String suffix) throws Exception {
		ImageUploadResponse response = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(fileData);
			BufferedImage sourceImg = ImageIO.read(bis);
			
			ImageHandleRegulation handleRegulation = new ImageHandleRegulation(1,0, sourceImg.getWidth(), sourceImg.getHeight(), 1);
			
			RequestData requestData = new RequestData(1, fileData, suffix, prefix);
			ImageUploadRequest fileRequest = new ImageUploadRequest(requestData,1,handleRegulation);
			response = FileServiceClient.uploadImage(appkey, fileRequest);
		
		} catch (Exception e) {
			LogService.error("UploadServiceUtil.uploadImage:"+ e.getMessage(),e);
			throw new Exception("UploadServiceUtil.uploadImage:"+ e.getMessage());
			
		}
		if(response != null && response.getCode() == 0) {
			return response;
		} else {
			//上传文件异常
			String msg = "UploadServiceUtil.uploadImage,appkey:"+ appkey + ",prefix:"+ prefix + ",suffix:"+ suffix + ",response:"+ (response!=null ? JSONObject.toJSONString(response):"null");
			LogService.error(msg);
			throw new Exception(msg);
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
		String appkey = "AKIAIKKIUAT5MGXBXBMA";
		String filePath = "C:\\Users\\zhengjianhao\\Desktop\\AD-icon.png",suffix = "png";
		String prefix = appkey;
		ImageUploadResponse response = uploadImage(appkey, filePath, prefix, suffix);
		System.out.println(JSONObject.toJSONString(response));
	}*/
	
}
