//package com.example.demo.controller.toolController;
//
//import cn.hutool.core.util.RandomUtil;
//import com.example.demo.dao.IUserDao;
//import com.example.demo.domian.Entity.Users;
//import com.example.demo.domian.Information.Message;
//import com.example.demo.service.FileUploadLocalService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@RestController
//public class addUser {
//
//    @Autowired
//    IUserDao userDao;
//
//    @Autowired
//    FileUploadLocalService fileUpload;
//
//    @RequestMapping("/addUser")
//    public Message contextLoads1() throws IOException {
//        getFiles("D:/baiduimage/动漫头像/");
//        return Message.success();
//    }
//
//    public void getFiles(String path) throws IOException {
//        File root = new File(path);
//        List<File> files = new ArrayList<>();
//        if (!root.isDirectory()) {
//            files.add(root);
//        } else {
//            File[] subFiles = root.listFiles();
//            for (File subFile : subFiles) {
//                Users user=new Users();
//                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-dd-MM");
//                String format = dateFormat.format(new Date());
//                user.setBirthday(format);
//                user.setUpdateTime(new Date());
//                user.setCreateTime(new Date());
//                user.setUsername(String.valueOf(RandomUtil.randomInt(100000000,1000000000)));
//                user.setNickname(ChineseName.getChineseName());
//                user.setPassword(String.valueOf(RandomUtil.randomInt(100000000,1000000000)));
//                user.setEmail(RandomUtil.randomInt(100000000,1000000000)+"@qq.com");
//                user.setPhoneNumber(RandomUtil.randomNumbers(11));
//                String s[]=new String[]{"男","女"};
//                user.setSex(s[RandomUtil.randomInt(0,2)]);
//                user.setHeadPortrait(fileUpload.uploadLocal(subFile,"user",user.getUsername()));
//                userDao.save(user);
//            }
//        }
//    }
//
//    public String getChinese(int n){
//        String zh_cn = "";
//        String str ="";
//
//        // Unicode中汉字所占区域\u4e00-\u9fa5,将4e00和9fa5转为10进制
//        int start = Integer.parseInt("4e00", 16);
//        int end = Integer.parseInt("9fa5", 16);
//
//        for(int ic=0;ic<n;ic++){
//            // 随机值
//            int code = (new Random()).nextInt(end - start + 1) + start;
//            // 转字符
//            str = new String(new char[] { (char) code });
//            zh_cn=zh_cn+str;
//        }
//        return zh_cn;
//    }
//}
