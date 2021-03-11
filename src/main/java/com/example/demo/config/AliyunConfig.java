//package com.example.demo.config;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.OSSClientBuilder;
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
///**
// * 阿里云问价上传
// */
//@Configuration
//@PropertySource(value = {"classpath:application.properties"})
//@ConfigurationProperties(prefix = "aliyun")
//@Data
//public class AliyunConfig {
//    private String endpoint;
//    private String accessKeyId;
//    private String accessKeySecret;
//    private String bucketName;
//
//    @Bean
//    public OSS oSSClient() {
//        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//    }
//}