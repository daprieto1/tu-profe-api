package com.tuprofe.api.logic.implementation;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.tuprofe.api.logic.services.IS3Services;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author diego
 */
@Service
@Qualifier("S3ServicesClient")
public class S3ServicesClient implements IS3Services{

    @Autowired
    private AmazonS3Client amazonS3Client;
    
    @Override
    public void uploadFile(MultipartFile input, String bucketName, String key) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(input.getContentType());

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, input.getInputStream(), objectMetadata);
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        
        amazonS3Client.putObject(putObjectRequest);
    }
    
    
    
}
