package com.tuprofe.api;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.transfer.TransferManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author diegoprietotorres
 */
@Configuration
@PropertySource(value = "classpath:aws-config.properties")
public class S3Config {

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

    @Bean
    public AmazonS3Client amazonS3Client() {
        AmazonS3Client amazonS3Client = new AmazonS3Client(basicAWSCredentials());
        amazonS3Client.setRegion(Region.getRegion(Regions.US_WEST_2));

        return amazonS3Client;
    }

    @Bean
    public TransferManager transferManager() {
        TransferManager transferManager = new TransferManager(basicAWSCredentials());
        return transferManager;
    }
}
