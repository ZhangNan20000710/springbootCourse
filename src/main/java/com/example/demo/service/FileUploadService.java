//package com.example.demo.service;
//
//import com.aliyun.oss.OSS;
//import com.aliyun.oss.model.DeleteObjectsRequest;
//import com.example.demo.config.AliyunConfig;
//import com.example.demo.domian.Information.FileUploadResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//import java.util.UUID;
//
//
///**
// * @author lastwhisper
// * @desc
// * @email gaojun56@163.com
// */
//@Service
//public class FileUploadService {
//    // 允许上传的格式
//    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg",
//            ".jpeg", ".gif", ".png"};
//    @Autowired
//    private OSS ossClient;
//    @Autowired
//    private AliyunConfig aliyunConfig;
//
//    @Value("${aliyun.bucketName}")
//    private String bucketName;
//    @Value("${aliyun.endpoint}")
//    private String endpoint;
//
//    /**
//     * 文件上传
//     *
//     * @param uploadFile
//     * @return
//     */
//    public FileUploadResult upload(MultipartFile uploadFile) {
//        // 校验图片格式
//        boolean isLegal = false;
//        for (String type : IMAGE_TYPE) {
//            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(),
//                    type)) {
//                isLegal = true;
//                break;
//            }
//        }
//        //封装Result对象，并且将文件的byte数组放置到result对象中
//        FileUploadResult fileUploadResult = new FileUploadResult();
//        if (!isLegal) {
//            fileUploadResult.setStatus("error");
//            return fileUploadResult;
//        }
//        //文件新路径
//        String filePath = "images/" + UUID.randomUUID() + ".jpg";
//        // 上传到阿里云
//        try {
//
//            ossClient.putObject(aliyunConfig.getBucketName(), filePath, uploadFile.getInputStream());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            //上传失败
//            fileUploadResult.setStatus("error");
//            return fileUploadResult;
//        }
//        fileUploadResult.setStatus("done");
//        fileUploadResult.setResponse("success");
//        fileUploadResult.setUrl("https://" + bucketName + "." + endpoint + "/" + filePath);
//        fileUploadResult.setUid(String.valueOf(System.currentTimeMillis()));
//        return fileUploadResult;
//    }
//
//    public void delete(String s) {
//        ossClient.deleteObject(bucketName, s);
//    }
//
//    public void deleteAll(List<String> s) {
//        ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(s));
//    }
//
//}