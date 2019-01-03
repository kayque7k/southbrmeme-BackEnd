package com.southbrmeme.InfraStructure.ExternalServices;

import java.io.File;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

public class UploadImageAWS {

    private final String accessKey = "";
    private final String secretKey = "";

    private final long time = System.currentTimeMillis();
    private final String bucketName = "";
    private final String keyName = "SouthBRMemes" + time + ".jpg";

    public String upload(InputStream fileInputStream, FormDataContentDisposition fileMetaData) {
        if (fileInputStream != null) {
            try {
                BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
                AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                        .withRegion("us-east-1").build();

                try {
                    s3client.putObject(new PutObjectRequest(bucketName, keyName, bytesToImage(fileInputStream, fileMetaData)));
                    return keyName;
                } catch (AmazonServiceException ase) {
                } catch (AmazonClientException ace) {
                }
            } catch (AmazonS3Exception e) {
            }
        }
        return null;
    }

    public File bytesToImage(InputStream fileInputStream, FormDataContentDisposition fileMetaData) {

        //dev
        String temp = System.getProperty("java.io.tmpdir");
        //prod
        //String temp = "/tmp/";

        File file = new File(temp + fileMetaData.getFileName());
        try {
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream out = new FileOutputStream(file);
            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            return file;
        } catch (IOException e) {
        }
        return null;
    }
}
