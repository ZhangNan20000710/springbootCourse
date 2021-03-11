package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.example.demo.dao.ICoursesDao;
import com.example.demo.dao.ICoursesUsersDao;
import com.example.demo.domian.Entity.Courses;
import com.example.demo.domian.Entity.CoursesUsers;
import com.example.demo.domian.Information.Message;
import com.example.demo.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Api(tags = "选课记录接口")
@RestController()
@RequestMapping("/CoursesUser")
public class CoursesUserCRUD {
    @Autowired
    ICoursesUsersDao cUDao;

    @Autowired
    ICoursesDao coursesDao;

    @ApiOperation("查询选课记录")
    @GetMapping(value = CRUD.FIND)
    public Message showCoursesUser(){
        Set<CoursesUsers> coursesUsers = new HashSet<>(cUDao.findAll());

        setNumber(coursesUsers);
        long count = cUDao.count();
        return Message.success().add("coursesUsers",coursesUsers).add("count",count);
    }

    @ApiOperation("根据用户id,课程id查询选课记录")
    @GetMapping(CRUD.FIND_BY_ID)
    public Message showCoursesUserById(@PathVariable String id){
        System.out.println(id);
        Set<CoursesUsers> courses = cUDao.findByUserUserId(id);
        setNumber(courses);
        Set<CoursesUsers> users = cUDao.findByCourseCourseId(id);
        setNumber(users);
        if(!courses.isEmpty()){
            return Message.success().add("coursesUsers",courses).add("count",courses.size());
        }else {
            return Message.success().add("coursesUsers",users).add("count",users.size());
        }
    }

    @ApiOperation("查询登录用户下的选课记录")
    @GetMapping(CRUD.FIND_LOGIN)
    public Message showMyCoursesUser(){
        String userId = TokenUtil.getTokenUId();
        Set<CoursesUsers> coursesUsers = cUDao.findByUserUserId(userId);

        setNumber(coursesUsers);
        return Message.success().add("coursesUsers",coursesUsers);
    }

    @ApiOperation("添加选课记录")
    @PostMapping(CRUD.SAVE)
    public Message CoursesUserAdd(CoursesUsers coursesUser){
        if(!cUDao.findByUserUserIdAndCourseCourseId
                (coursesUser.getUser().getUserId(),
                        coursesUser.getCourse().getCourseId()).isEmpty()){
            return Message.error("你已选择该课程，不能重复选择");
        }
        CoursesUsers save = cUDao.save(coursesUser);
        return Message.success("添加成功").add("coursesUser",save);
    }

    @ApiOperation("修改选课记录")
    @PutMapping(CRUD.ALTER)
    public Message CoursesUserAlter(CoursesUsers coursesUser){
        /*通过id获取之前修改之前的数据*/
        CoursesUsers before = cUDao.getOne(coursesUser.getCoursesUserId());
        /*覆盖之前的数据并忽略空值*/
        BeanUtil.copyProperties(coursesUser,before, CopyOptions.create().setIgnoreNullValue(true));
        CoursesUsers save = cUDao.save(before);
        return Message.success("修改成功").add("coursesUser",save);
    }

    @ApiOperation("根据id删除选课记录")
    @DeleteMapping(CRUD.DELETE_BY_ID)
    public Message CoursesUserDelete(@PathVariable String id){
        cUDao.deleteById(id);
        return Message.success("删除成功").add("id",id);
    }

    @ApiOperation("根据用户id和课程id删除选课记录")
    @DeleteMapping(CRUD.DELETE)
    public Message CoursesUserDelete(String courseId,String userId){
        cUDao.deleteByCourseCourseIdAndUserUserId(courseId,userId);
        return Message.success("删除成功");
    }

    @ApiOperation("删除所有选课记录")
    @DeleteMapping(CRUD.DELETE_ALL)
    public Message CoursesUserDelete(){
        cUDao.deleteAll();
        return Message.success("删除成功");
    }

    public void setNumber(Set<CoursesUsers> coursesUsers){
        for (CoursesUsers coursesUser : coursesUsers) {
            Courses course = coursesDao.getOne(coursesUser.getCourse().getCourseId());
            course.setNumber(cUDao.findByCourseCourseId(course.getCourseId()).size());
            coursesUser.setCourse(course);
        }
    }
}
