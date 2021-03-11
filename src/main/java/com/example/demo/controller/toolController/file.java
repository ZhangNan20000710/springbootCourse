//package com.example.demo.controller.toolController;
//
//import com.example.demo.domian.Entity.Users;
//import com.example.demo.domian.Information.Message;
//import com.example.demo.service.FileUploadLocalService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController()
//@RequestMapping("/file")
//public class file {
//
//    @Autowired
//    FileUploadLocalService fileUpload;
//
//    @PostMapping("/upload")
//    public Message upload(@RequestParam("file") MultipartFile[] files) throws IOException {
//        List<String> paths=new ArrayList<>();
//        //添加文件
//        for (MultipartFile file : files) {
//            if(!file.isEmpty()){
//                String upload = fileUpload.upload(file,"file","abc");
//                System.out.println(upload);
//                paths.add(upload);
//            }else {
//                return Message.error("文件为空");
//            }
//        }
//        return Message.success("文件路径").add("paths",paths);
//    }
//}
