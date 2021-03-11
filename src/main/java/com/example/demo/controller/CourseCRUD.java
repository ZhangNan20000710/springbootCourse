package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.example.demo.annotation.AdminLoginToken;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.dao.ICoursesDao;
import com.example.demo.dao.ICoursesUsersDao;
import com.example.demo.dao.ITagsDao;
import com.example.demo.dao.IUserDao;
import com.example.demo.domian.Entity.*;
import com.example.demo.domian.Information.Message;
import com.example.demo.service.FileUploadLocalService;
import com.example.demo.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Api(tags = "课程接口")
@RequestMapping("/Course")
@RestController
public class CourseCRUD {

    @Autowired
    private ICoursesDao coursesDao;

    @Autowired
    private ICoursesUsersDao coursesUsersDao;

    @Autowired
    private IUserDao userDao;


    @Autowired
    private FileUploadLocalService fileUpload;

    Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
    int pageSize=40;


    @ApiOperation("分页查询所有课程")
    @GetMapping(value=CRUD.FIND)
    public Message showCourse(
            @RequestParam(name = "index",defaultValue = "0") int index){
        //定义分页
        PageRequest page=PageRequest.of(index,pageSize,sort);

        Page<Courses> courses = coursesDao.findAll(page);

        //给number赋值
        setNumber(courses);

        return Message.success().add("courses",courses);
    }

    @ApiOperation("根据课程id,用户id,typeId分页查询课程")
    @GetMapping(CRUD.FIND_BY_ID)
    public Message showCourseById(@PathVariable String id,
                                  @RequestParam(name = "index",defaultValue = "0") int index){

        PageRequest page=PageRequest.of(index,pageSize,sort);
        try {
            Courses course = coursesDao.getOne(id);
            Page<Courses> coursesByUser =
                    coursesDao.findByUserUserId(id,page);
            //给number赋值
            setNumber(coursesByUser);

            Page<Courses> coursesByType =
                    coursesDao.findByTypeTypeId(id,page);
            //给number赋值
            setNumber(coursesByType);

            if(!coursesByType.isEmpty()){

                return Message.success().
                        add("coursesByType",coursesByType);

            }else if(!coursesByUser.isEmpty()){

                return Message.success()
                        .add("coursesByUser",coursesByUser);

            }else {
                return Message.success()
                        .add("course",course);
            }
        }catch (Exception e){
            return Message.error("当前页没有课程");
        }
    }


    @ApiOperation("分页查询当前登录用户发布的课程")
    @GetMapping(CRUD.FIND_LOGIN)
    @UserLoginToken
    public Message showMyCourse(
            @RequestParam(name = "index",defaultValue = "0") int index){

        PageRequest page=PageRequest.of(index,pageSize,sort);
        String userId = TokenUtil.getTokenUId();
        Page<Courses> courses = coursesDao.findByUserUserId(userId,page);
        //给number赋值
        setNumber(courses);
        return Message.success().add("courses",courses);
    }

    @ApiOperation("按条件(课程名称，发布该课程的用户昵称)搜索课程")
    @GetMapping(CRUD.SEARCH)
    private Message Search(@PathVariable String name,
                           @RequestParam(name = "index", defaultValue = "0") int index) {
        name="%" + name + "%";
        PageRequest page=PageRequest.of(index,pageSize,sort);
        Page<Courses> searchCourses =
                coursesDao.findByCourseNameLikeOrUserNicknameLike(name,name,page);

        //给number赋值
        setNumber(searchCourses);

        return Message.success().add("searchCourses", searchCourses);
    }

    @ApiOperation("首页推荐课程")
    @GetMapping("/recommend")
    public Message recommend(){
        List<Object> CUs = coursesDao.findBySortUserNumber();

        /*提取前20行数据*/
        CUs = CUs.subList(0, 20);
        System.out.println(CUs);
        return Message.success("精品课程").add("courses",CUs);
    }

    @ApiOperation("热门课程排行")
    @GetMapping("/hotRecommend")
    public Message hotRecommend(){
        List<Object> courses = coursesDao.findBySourCommentNumber();
        /*提取前10行数据*/
        courses = courses.subList(0, 10);
        return Message.success("热门课程排行").add("courses",courses);
    }



    @ApiOperation("课程详情下的推荐")
    @GetMapping("/CRecommend")
    public Message CRecommend(String id){
        Courses course = coursesDao.getOne(id);
        Types type = course.getType();
        List<Courses> courses = coursesDao.findByTypeTypeId(type.getTypeId());
        return Message.success("相关推荐").add("courses",courses.subList(0,10));
    }

    @ApiOperation("个人爱好推荐")
    @GetMapping("/PHRecommend")
    @UserLoginToken
    public Message PHRecommend(){
        Set<CoursesUsers> coursesUsers =
                coursesUsersDao.findByUserUserId(TokenUtil.getTokenUId());
        Map<Types,Integer> TypeNumber =new HashMap<>();
//        Map<Tags,Integer> tagNumber=new HashMap<>();

        /*统计标签和类别对应的选择个数*/
        for (CoursesUsers coursesUser : coursesUsers) {
            Types type = coursesUser.getCourse().getType();

            if(TypeNumber.containsKey(type)){
                TypeNumber.put(type,TypeNumber.get(type)+1);
            }else {
                TypeNumber.put(type,1);
            }

            /*标签个数*/
            /*Set<Tags> tags = coursesUser.getCourse().getTags();
            for (Tags tag : tags) {
                if(tagNumber.containsKey(tag)){
                    tagNumber.put(tag,tagNumber.get(tag)+1);
                }else {
                    tagNumber.put(tag,1);
                }
            }*/
        }

        List<Map.Entry<Types, Integer>> typeNumberL =
                new ArrayList<>(TypeNumber.entrySet());
        /*排序*/
        Collections.sort(typeNumberL, new
                Comparator<Map.Entry<Types, Integer>>() {
            @Override
            public int compare(Map.Entry<Types, Integer> o1,
                               Map.Entry<Types, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });

        typeNumberL=typeNumberL.subList(0,
                Math.min(typeNumberL.size(), 5));

        /*标签排序*/
        /*List<Map.Entry<Tags, Integer>> tagNumberL = new ArrayList<>(tagNumber.entrySet());
        Collections.sort(tagNumberL, new Comparator<Map.Entry<Tags, Integer>>() {
            @Override
            public int compare(Map.Entry<Tags, Integer> o1, Map.Entry<Tags, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        tagNumberL=tagNumberL.subList(0,Math.min(tagNumberL.size(), 5));*/

        List<Courses> coursesSet=new ArrayList<>();
        for (Map.Entry<Types, Integer> typesIntegerEntry : typeNumberL) {
            coursesSet.addAll(coursesDao.
                    findByTypeTypeId(typesIntegerEntry
                            .getKey()
                            .getTypeId())
                    .subList(0,20/typeNumberL.size()));
        }


        return Message.success("您感兴趣的").add("courses",coursesSet);
    }



    @ApiOperation("添加课程信息")
    @PostMapping(CRUD.SAVE)
    @UserLoginToken
    public Message addCourse(Courses course, @RequestPart("file")MultipartFile file) throws IOException {
        //获取token中的userId
        String tokenUId = TokenUtil.getTokenUId();
        Users user = userDao.getOne(tokenUId);
        //为课程添加发布的用户
        course.setUser(user);
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        if(!file.isEmpty()){
            String upload = fileUpload.upload(file,"course",course.getCourseName());
            course.setPictures(upload);
            System.out.println("封面上传成功");
        }
        Courses save = coursesDao.save(course);
        return Message.success("课程信息添加成功").add("course",save);
    }

    @ApiOperation("添加课程封面")
    @PostMapping("/addPortrait")
    @UserLoginToken
    public Message addPortrait(String courseId,@RequestParam("file")MultipartFile file) throws IOException {
        Courses course = coursesDao.getOne(courseId);
        if(!file.isEmpty()){
            String upload = fileUpload.upload(file,"course",course.getCourseName());
            course.setPictures(upload);
        }

        Courses save = coursesDao.save(course);
        return Message.success("封面上传成功").add("course",course);
    }

    @ApiOperation("修改课程信息")
    @PutMapping(CRUD.ALTER)
    @UserLoginToken
    public Message alterCourse(Courses course){

        /*通过id获取之前修改之前的数据*/
        Courses before = coursesDao.getOne(course.getCourseId());

        /*覆盖之前的数据并忽略空值*/
        BeanUtil.copyProperties(course,before,CopyOptions.create().setIgnoreNullValue(true));
        before.setUpdateTime(new Date());

        Courses save = coursesDao.save(before);
        return Message.success("修改成功").add("course",save);
    }

    @ApiOperation("删除课程")
    @DeleteMapping(CRUD.DELETE_BY_ID)
    public Message deleteCourse(@PathVariable String id){
        coursesDao.deleteById(id);
        return Message.success("删除成功").add("Id",id);
    }

    @ApiOperation("删除全部课程")
    @DeleteMapping(CRUD.DELETE_ALL)
    @AdminLoginToken
    public Message deleteAllUser(){
        coursesDao.deleteAll();
        return Message.success("删除成功");
    }




    /*判断课程是否是我选择的*/
    /*public void isMe(Courses course){
        String tokenUId = TokenUtil.getTokenUId();
        for (CoursesUsers user : course.getUsers()) {
            course.setIsMe(tokenUId.equals(user.getUser().getUserId()));
        }
    }*/

    public void setNumber(Page<Courses> courses){
        for (Courses course : courses) {
            course.setNumber(coursesUsersDao.findByCourseCourseId(course.getCourseId()).size());
        }
    }
}
