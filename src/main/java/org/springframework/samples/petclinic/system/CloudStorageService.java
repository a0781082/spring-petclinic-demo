package org.springframework.samples.petclinic.system;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudStorageService {

	@Autowired
	private AmazonS3 s3client;

	public PutObjectResult uploadFileToBucket(String bucketName, String key, File file) {
		return s3client.putObject(bucketName, key, file);
	}

	public S3Object downloadFileFromBucket(String bucketName, String key) {
		return s3client.getObject(bucketName, key);
	}
}
