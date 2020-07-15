package org.yuekeju.common.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.yuekeju.common.vo.ConstantProperties;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@ConditionalOnProperty(name = "myDatasources.enabled", havingValue = "true")
public class AliyunOSSUtil {
	@Autowired
	private ConstantProperties constantProperties;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 上传
	 * 
	 * @param file
	 * @return
	 */
	public String upload(File file) {
		if (file == null) {
			return null;
		}
		log.info("=========>OSS文件上传开始：" + file.getName());
		String endpoint = constantProperties.getEndpoint();
		String accessKeyId = constantProperties.getKeyid();
		String accessKeySecret = constantProperties.getKeysecret();
		String bucketName = constantProperties.getBucketname();
		String fileHost = constantProperties.getFilehost();
		System.out.println(endpoint + "endpoint");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = format.format(new Date());
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			// 容器不存在，就创建
			if (!ossClient.doesBucketExist(bucketName)) {
				ossClient.createBucket(bucketName);
				CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
				createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
				ossClient.createBucket(createBucketRequest);
			}
			// 创建文件路径
			String fileUrl = fileHost + "/"
					+ (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
			// 上传文件
			PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
			// 设置权限 这里是公开读
			ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
			if (null != result) {
				log.info("==========>OSS文件上传成功,OSS地址：" + fileUrl);
				return fileUrl;
			}
		} catch (OSSException oe) {
			log.error(oe.getMessage());
		} catch (ClientException ce) {
			log.error(ce.getMessage());
		} finally {
			// 关闭
			ossClient.shutdown();
		}
		return null;
	}

	/**
	 * 删除
	 * @param fileKey
	 * @return
	 */
	public String deleteBlog(String fileKey) {
		log.info("=========>OSS文件删除开始");
		String endpoint = constantProperties.getEndpoint();
		String accessKeyId = constantProperties.getKeyid();
		String accessKeySecret = constantProperties.getKeysecret();
		String bucketName = constantProperties.getBucketname();
		String fileHost = constantProperties.getFilehost();
		try {
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

			if (!ossClient.doesBucketExist(bucketName)) {
				log.info("==============>您的Bucket不存在");
				return "您的Bucket不存在";
			} else {
				log.info("==============>开始删除Object");
				ossClient.deleteObject(bucketName, fileKey);
				log.info("==============>Object删除成功：" + fileKey);
				return "==============>Object删除成功：" + fileKey;
			}
		} catch (Exception ex) {
			log.info("删除Object失败", ex);
			return "删除Object失败";
		}
	}

	/**
	 * 查询文件名列表
	 * 
	 * @param bucketName
	 * @return
	 */
	public List<String> getObjectList(String bucketName) {
		List<String> listRe = new ArrayList<>();
		String endpoint = constantProperties.getEndpoint();
		String accessKeyId = constantProperties.getKeyid();
		String accessKeySecret = constantProperties.getKeysecret();
		try {
			log.info("===========>查询文件名列表");
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
			// 列出11111目录下今天所有文件
			listObjectsRequest.setPrefix("11111/" + format.format(new Date()) + "/");
			ObjectListing list = ossClient.listObjects(listObjectsRequest);
			for (OSSObjectSummary objectSummary : list.getObjectSummaries()) {
				System.out.println(objectSummary.getKey());
				listRe.add(objectSummary.getKey());
			}
			return listRe;
		} catch (Exception ex) {
			log.info("==========>查询列表失败", ex);
			return new ArrayList<>();
		}
	}

}
