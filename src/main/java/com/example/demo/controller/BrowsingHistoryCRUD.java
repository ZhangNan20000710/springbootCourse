//package com.example.demo.controller;
//
//import com.example.demo.annotation.UserLoginToken;
//import com.example.demo.dao.BrowsingHistoryDao;
//import com.example.demo.domian.Entity.BrowsingHistory;
//import com.example.demo.domian.Information.Message;
//import com.example.demo.util.TokenUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//@Api(tags = "历史记录接口")
//@RequestMapping("/BrowsingHistory")
//@RestController
//public class BrowsingHistoryCRUD {
//
//    @Autowired
//    BrowsingHistoryDao bhDao;
//
//    @ApiOperation("查询历史记录")
//    @GetMapping(CRUD.FIND)
//    public Message bHShow(){
//        Set<BrowsingHistory> bhs = new HashSet<>(bhDao.findAll());
//        long count=bhDao.count();
//        return Message.success().add("BrowsingHistory",bhs).add("count",count);
//    }
//
//    @ApiOperation("根据用户id查询历史记录")
//    @GetMapping(CRUD.FIND_BY_ID)
//    @UserLoginToken
//    public Message bHShowByUId(@PathVariable String id){
//        Set<BrowsingHistory> bhs = new HashSet<>(bhDao.findByUserUserId(id));
//        return Message.success().add("BrowsingHistory",bhs);
//    }
//
//    @ApiOperation("查询当前登录用户的历史记录")
//    @GetMapping(CRUD.FIND_LOGIN)
//    @UserLoginToken
//    public Message showMyBH(){
//        String userId = TokenUtil.getTokenUId();
//        Set<BrowsingHistory> bhs = new HashSet<>(bhDao.findByUserUserId(userId));
//        return Message.success().add("BrowsingHistory",bhs);
//    }
//
//    @ApiOperation("添加浏览记录")
//    @PostMapping(CRUD.SAVE)
//    @UserLoginToken
//    public Message bHAdd(BrowsingHistory bh){
//        //判断历史记录是否重复
//        BrowsingHistory bh1 = bhDao.findByUserUserIdAndCourseCourseId(bh.getUser().getUserId(), bh.getCourse().getCourseId());
//        if(bh1!=null){
//            bh=bh1;
//        }
//        bh.setBrowseTime(new Date());
//        BrowsingHistory save = bhDao.save(bh);
//        return Message.success().add("BrowsingHistory",save);
//    }
//
//    @ApiOperation("根据userId和courseId删除浏览记录")
//    @DeleteMapping(CRUD.DELETE)
//    @UserLoginToken
//    public Message bHDelete(String userId,String courseId){
//        bhDao.deleteByUserUserIdAndCourseCourseId(userId, courseId);
//        return Message.success();
//    }
//}