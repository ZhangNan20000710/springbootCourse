//package com.example.demo.controller.toolController;
//
//import cn.hutool.core.util.RandomUtil;
//import com.example.demo.dao.ICoursesDao;
//import com.example.demo.dao.ITagsDao;
//import com.example.demo.dao.ITypesDao;
//import com.example.demo.dao.IUserDao;
//import com.example.demo.domian.Entity.Courses;
//import com.example.demo.domian.Entity.Tags;
//import com.example.demo.domian.Entity.Types;
//import com.example.demo.domian.Entity.Users;
//import com.example.demo.domian.Information.Message;
//import com.example.demo.service.FileUploadLocalService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.xml.transform.Source;
//import java.io.*;
//import java.util.*;
//
//@RestController
//public class AddCourses {
//
//    @Autowired
//    ICoursesDao coursesDao;
//
//    @Autowired
//    IUserDao userDao;
//
//    @Autowired
//    ITypesDao typesDao;
//
//    @Autowired
//    ITagsDao tagsDao;
//
//    @Autowired
//    FileUploadLocalService fileUpload;
//
//    @RequestMapping("/addCourse")
//    public Message contextLoads1() throws IOException {
//        getFiles("C:/Users/ASUS/Desktop/类别信息");
//        return Message.success();
//    }
//
//    @RequestMapping("/updateCourse")
//    public Message updateCourse() throws IOException {
//
//        File root = new File("C:/Users/ASUS/Desktop/课程详情");
//        List<File> files = new ArrayList<>();
//        if (!root.isDirectory()) {
//            files.add(root);
//        } else {
//            File[] subFiles = root.listFiles();
//            for (File f : subFiles) {
//                BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));
//                String s;
//                StringBuilder sb= new StringBuilder();
//                while((s=reader.readLine())!=null) {
//                    sb.append(s);
//                }
//                String name = f.getName().substring(0, f.getName().indexOf(".txt"));
//                System.out.println(name);
//                List<Courses> byCourseName = coursesDao.findByCourseName(name);
//                String[] split = sb.toString().split("【课程名】");
//                int count=0;
//                for(int i=1;i<split.length;i++){
//                    if(byCourseName.isEmpty()){
//                        count++;
//                        break;
//                    }
//                    if(i-1==byCourseName.size()){
//                        break;
//                    }
//                    Courses courses = byCourseName.get(i-1);
//                    String s1 = split[i].substring(split[i].indexOf("【课程详情】") + 6, split[i].indexOf("【课程概述】"));
//                    courses.setDescribes(s1);
//                    List<Users> users = userDao.findAll();
//                    courses.setUser(users.get(RandomUtil.randomInt(0, users.size())));
//                    List<Types> types = typesDao.findAll();
//                    courses.setType(types.get(RandomUtil.randomInt(0, types.size())));
//                    List<Tags> tags = tagsDao.findAll();
//                    HashSet<Tags> tagList = new HashSet<>();
//                    tagList.add(tags.get(RandomUtil.randomInt(0, tags.size())));
//                    tagList.add(tags.get(RandomUtil.randomInt(0, tags.size())));
//                    courses.setUpdateTime(new Date());
//                    courses.setCreateTime(new Date());
//                    coursesDao.save(courses);
//                }
//                System.out.println(count);
//            }
//        }
//        return Message.success();
//    }
//
//    public void getFiles2(String path) throws IOException {
//        File root = new File(path);
//        List<File> files = new ArrayList<>();
//        if(!root.isDirectory()){
//            files.add(root);
//        }else {
//            File[] subFiles = root.listFiles();
//            for(File f : subFiles){
//                BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));
//                String s;
//                StringBuilder sb= new StringBuilder();
//                while((s=reader.readLine())!=null) {
//                    sb.append(s);
//                }
//                Courses courses = new Courses();
//                courses.setCourseName(f.getName().split("\\.")[0]);
//                String[] split = sb.toString().split("【课程名】");
//                for(int i=1;i<split.length;i++){
//                    String s1 = split[i].substring(split[i].indexOf("【课程详情】") + 6, split[i].indexOf("【课程概述】"));
//
//                    courses.setDescribes(s1);
//                    List<Users> users = userDao.findAll();
//                    courses.setUser(users.get(RandomUtil.randomInt(0, users.size())));
//                    List<Types> types = typesDao.findAll();
//                    courses.setType(types.get(RandomUtil.randomInt(0, types.size())));
//                    List<Tags> tags = tagsDao.findAll();
//                    HashSet<Tags> tagList = new HashSet<>();
//                    tagList.add(tags.get(RandomUtil.randomInt(0, tags.size())));
//                    tagList.add(tags.get(RandomUtil.randomInt(0, tags.size())));
//                    courses.setUpdateTime(new Date());
//                    courses.setCreateTime(new Date());
//                    coursesDao.save(courses);
//                }
//            }
//        }
//    }
//
//    public void getFiles(String path) throws IOException {
//        File root = new File(path);
//        List<File> files = new ArrayList<>();
//        if(!root.isDirectory()){
//            files.add(root);
//        }else{
//            File[] subFiles = root.listFiles();
//            for(File f : subFiles){
//                BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream(f),"GBK"));
//                String s;
//                while((s=reader.readLine())!=null){
//                    Courses courses = new Courses();
//                    List<Users> users = userDao.findAll();
//                    courses.setUser(users.get(RandomUtil.randomInt(0, users.size())));
//
//                    List<Types> types = typesDao.findAll();
//                    courses.setType(types.get(RandomUtil.randomInt(0, types.size())));
//
//                    List<Tags> tags = tagsDao.findAll();
//                    HashSet<Tags> tagList = new HashSet<>();
//                    tagList.add(tags.get(RandomUtil.randomInt(0, tags.size())));
//                    tagList.add(tags.get(RandomUtil.randomInt(0, tags.size())));
//                    courses.setTags(tagList);
//                    String[] split = s.split(",");
//                    courses.setCourseName(split[0]);
//                    courses.setPictures(split[1]);
//
//    //                String course = fileUpload.uploadLocal(f, "course", courses.getCourseName());
//
//                    courses.setUpdateTime(new Date());
//                    courses.setCreateTime(new Date());
//                    coursesDao.save(courses);
//                }
//
//            }
//        }
//}
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
//            if(ic%10==0){
//                str+="，";
//            }
//
//            zh_cn=zh_cn+str;
//        }
//        return zh_cn;
//    }
//}
