package com.southbrmeme.InfraStructure.ExternalServices;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kayque.andrade
 */ 

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;
import javax.imageio.ImageIO;

public class DownloadImageAWS {
    
    private final String accessKey = "";
    private final String secretKey = "";
    private final String bucketName = "";
    
    public byte[] download(String key) {

        if (key != null) {
            BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
            AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .withRegion("us-east-1").build();

            try {
                S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName,key));
                try {
                    return imagem(s3object.getObjectContent());
                } catch (IOException ex) {

                }

            } catch (AmazonServiceException ase) {
            } catch (AmazonClientException ace) {
            }
        }

        return null;
    }

    public static byte[] imagem(InputStream input) throws IOException {
        InputStream is = new BufferedInputStream(input);
        BufferedImage image = ImageIO.read(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return Base64.getEncoder().encode(baos.toByteArray());
    }

}