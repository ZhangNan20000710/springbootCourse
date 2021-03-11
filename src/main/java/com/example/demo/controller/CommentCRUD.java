package com.example.demo.controller;

import com.example.demo.annotation.AdminLoginToken;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.dao.ICommentsDao;
import com.example.demo.dao.IUserDao;
import com.example.demo.domian.Entity.Comments;
import com.example.demo.domian.Entity.Users;
import com.example.demo.domian.Information.Message;
import com.example.demo.util.TokenUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "评论接口")
@RequestMapping("/Comment")
@RestController
public class CommentCRUD {

    @Autowired
    ICommentsDao commentsDao;
    @Autowired
    IUserDao userDao;

    int pageSize=20;

    Sort sort = Sort.by(Sort.Direction.DESC, "createTime");

    @ApiOperation("查询全部评论")
    @GetMapping(CRUD.FIND)
//    @AdminLoginToken
    public Message commentShow(@RequestParam(name = "index",defaultValue = "0") int index){
        PageRequest page=PageRequest.of(index,pageSize,sort);
        Page<Comments> comments = commentsDao.findAll(page);
        return Message.success().add("comments",comments);
    }

    @ApiOperation("查询当前登录用户的全部评论")
    @GetMapping(CRUD.FIND_LOGIN)
    @UserLoginToken
    public Message commentShowByLogin(@RequestParam(name = "index", defaultValue = "0") int index){
        String tokenUId = TokenUtil.getTokenUId();
        PageRequest page=PageRequest.of(index,pageSize,sort);
        Page<Comments> commentsByUserId = commentsDao.findByUserUserId(tokenUId,page);

        return Message.success().add("comments",commentsByUserId);
    }

    @ApiOperation("根据父评论id,课程id,用户id查询评论")
    @GetMapping(CRUD.FIND_BY_ID)
    public Message commentShowById(@PathVariable String id,
                                   @RequestParam(name = "index", defaultValue = "0") int index){
        PageRequest page=PageRequest.of(index,pageSize,sort);
        //根据用户id查询评论
        Page<Comments> commentsByUserId = commentsDao.findByUserUserId(id,page);
        //根据课程id查询评论
        Page<Comments> commentsByCourseId = commentsDao.findByCourseCourseIdAndParentCommentIsNull(id,page);

        if(!commentsByUserId.isEmpty()){
            return Message.success().add("comments",commentsByUserId);
        }else {
            return Message.success().add("comments",commentsByCourseId);
        }
    }

    @ApiOperation("根据评论内容搜索评论")
    @GetMapping(CRUD.SEARCH)
    @AdminLoginToken
    public Message searchComment(@PathVariable String name,
                                 @RequestParam(name = "index", defaultValue = "0") int index){
        name="%" + name + "%";
        PageRequest page=PageRequest.of(index,pageSize,sort);
        Page<Comments> comments = commentsDao.findByCommentContentLikeOrCourseCourseNameLikeOrUserNicknameLike(name,name,name,page);
        return Message.success().add("comments",comments);
    }

    @ApiOperation("根据当前登录用户添加评论")
    @PostMapping(CRUD.SAVE)
    @UserLoginToken
    public Message commentAdd(Comments comment){
        //获取当前登录用户id
        String uId = TokenUtil.getTokenUId();
        Users user = userDao.getOne(uId);
        comment.setUser(user);
        comment.setCreateTime(new Date());
        Comments save = commentsDao.save(comment);
        return Message.success("评论成功").add("comment",save);
    }

    @ApiOperation("删除评论")
    @DeleteMapping(CRUD.DELETE_BY_ID)
    public Message commentDelete(@PathVariable String id){
        commentsDao.deleteById(id);
        return Message.success("删除成功").add("id",id);
    }

    //日期之差
//    public void dateDiff(Comments comment){
//        Long time = new Date().getTime();
//        Long time1 = comment.getCreateTime().getTime();
//        long l = time - time1;
//        long year =l/1000/60/60/24/30/12;
//        long month =l/1000/60/60/24/30;
//        long day =l/1000/60/60/24;
//        long hours =l/1000/60/60;
//        long minutes =l/1000/60;
//
//        if(year>=1){
//            comment.setBeforeTime(year+"年前");
//        }else if(month>=1){
//            comment.setBeforeTime(month+"个月前");
//        }else if(day>=1){
//            comment.setBeforeTime(day+"天前");
//        }else if(hours>=1){
//            comment.setBeforeTime(hours+"小时前");
//        }else if(minutes>=1){
//            comment.setBeforeTime(minutes+"分钟前");
//        }else {
//            comment.setBeforeTime("刚刚");
//        }
//
//    }
}
