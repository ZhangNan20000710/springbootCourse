//package com.example.demo.controller.toolController;
//
//import cn.hutool.core.util.RandomUtil;
//import com.example.demo.dao.ICommentsDao;
//import com.example.demo.dao.ICoursesDao;
//import com.example.demo.dao.IUserDao;
//import com.example.demo.domian.Entity.Comments;
//import com.example.demo.domian.Entity.Courses;
//import com.example.demo.domian.Entity.Users;
//import com.example.demo.domian.Information.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.lang.reflect.Array;
//import java.time.LocalTime;
//import java.util.*;
//
//@RestController
//public class addComment {
//    @Autowired
//    IUserDao userDao;
//
//    @Autowired
//    ICoursesDao coursesDao;
//
//    @Autowired
//    ICommentsDao commentsDao;
//
//    @RequestMapping("/addComment")
//    public Message add(){
//        List<Users> users = userDao.findAll();
//        List<Courses> courses = coursesDao.findAll();
//        for (Courses course : courses) {
//           for(int i=0;i<RandomUtil.randomInt(5,100);i++){
//               Comments comment=new Comments();
//               comment.setCourse(course);
//               comment.setUser(users.get(RandomUtil.randomInt(0,users.size())));
//
//               comment.setCommentContent(getChinese(RandomUtil.randomInt(5,100)));
//               comment.setCreateTime(new Date());
//               commentsDao.save(comment);
//           }
//        }
//        return Message.success();
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
