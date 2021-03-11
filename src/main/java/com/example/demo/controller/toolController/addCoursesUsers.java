//package com.example.demo.controller.toolController;
//
//import cn.hutool.core.util.RandomUtil;
//import com.example.demo.dao.ICoursesDao;
//import com.example.demo.dao.ICoursesUsersDao;
//import com.example.demo.dao.IUserDao;
//import com.example.demo.domian.Entity.Courses;
//import com.example.demo.domian.Entity.CoursesUsers;
//import com.example.demo.domian.Entity.Users;
//import com.example.demo.domian.Information.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class addCoursesUsers {
//
//    @Autowired
//    ICoursesUsersDao coursesUsersDao;
//    @Autowired
//    IUserDao userDao;
//
//    @Autowired
//    ICoursesDao coursesDao;
//
//
//    @GetMapping("/addCoursesUsers")
//    public Message add(){
//        List<Users> users = userDao.findAll();
//        List<Courses> courses = coursesDao.findAll();
//        for (Users user : users) {
//            for(int i = 0; i< RandomUtil.randomInt(5,10); i++){
//                CoursesUsers coursesUser=new CoursesUsers();
//                coursesUser.setUser(user);
//                coursesUser.setCourse(courses.get(RandomUtil.randomInt(0,courses.size())));
//                coursesUsersDao.save(coursesUser);
//            }
//        }
//        return Message.success();
//    }
//}
