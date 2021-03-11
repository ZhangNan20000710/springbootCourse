package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.RandomUtil;
import com.example.demo.annotation.AdminLoginToken;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.dao.IUserDao;
import com.example.demo.domian.Entity.Comments;
import com.example.demo.domian.Entity.Users;
import com.example.demo.domian.Information.Message;
import com.example.demo.service.FileUploadLocalService;
import com.example.demo.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Api(tags = "用户接口")
@RequestMapping("/User")
@RestController
public class UserCRUD {

    @Autowired
    private IUserDao userDao;

    @Autowired
    FileUploadLocalService fileUpload;

    @Autowired
    StringRedisTemplate template;

    Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
    int pageSize=20;

    @ApiOperation("用户查询")
    @GetMapping(CRUD.FIND)
//    @AdminLoginToken
    public Message showUser(
            @RequestParam(name = "index",defaultValue = "0") int index){
        PageRequest page=PageRequest.of(index,pageSize,sort);
        Page<Users> users =userDao.findAll(page);
        long count = userDao.count();
        return Message.success().add("users",users);
    }

    @ApiOperation("根据id查询用户")
    @GetMapping(CRUD.FIND_BY_ID)
    public Message showUserById(@PathVariable String id){
        Users user = userDao.getOne(id);
        return Message.success().add("users",user);
    }

    @ApiOperation("查询登录用户下的user信息")
    @GetMapping(CRUD.FIND_LOGIN)
    @UserLoginToken
    public Message showMyUser(){
        /*获取当前登录的用户id*/
        String userId= TokenUtil.getTokenUId();
        Users user = userDao.getOne(userId);
        return Message.success().add("user",user);
    }

    @ApiOperation("根据用户名、昵称、手机号码、邮箱搜索用户")
    @GetMapping(CRUD.SEARCH)
    @AdminLoginToken
    public Message searchUser(@PathVariable String name,
                                 @RequestParam(name = "index", defaultValue = "0") int index){
        name="%"+name+"%";
        PageRequest page=PageRequest.of(index,pageSize,sort);
        Page<Users> users = userDao.findByUsernameLikeOrNicknameLikeOrPhoneNumberLikeOrEmailLike(name,name,name,name,page);

        return Message.success().add("users",users);
    }

    @ApiOperation("用户添加/注册")
    @PostMapping(CRUD.SAVE)
    public Message addUser(Users user,/*@RequestParam(value = "file") Object file,*/String VCode){
        //判断手机号是否重复
        if(userDao.findByPhoneNumber(user.getPhoneNumber())!=null){
            return Message.error("手机号码已注册");
        }

        if(userDao.findByUsername(user.getUsername())!=null){
            return Message.error("用户名重复");
        }

        //判断验证码是否正确
        if(!VCode.equals(template.opsForValue().get(user.getPhoneNumber()))){
            return Message.error("验证码无效");
        }

        user.setHeadPortrait("/images/portrait/user/default/user.png");
        /*添加时间*/
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        Users save = userDao.save(user);
        return Message.success("添加成功").add("user",save);
    }

    @ApiOperation("修改用户信息")
    @PutMapping(CRUD.ALTER)
    public Message alterUser(Users user){
        System.out.println(user);
        /*通过id获取之前修改之前的数据*/
        Users before = userDao.getOne(user.getUserId());
        /*覆盖之前的数据并忽略空值*/
        BeanUtil.copyProperties(user,before,
                CopyOptions.create().setIgnoreNullValue(true));
        before.setUpdateTime(new Date());
        Users save = userDao.save(before);
        return Message.success("修改成功").add("user",save);
    }
    //添加图片
    @ApiOperation("上传文件")
    @PostMapping("/addHeadPortrait")
    public Message addHeadPortrait(String userId,
                                   @RequestParam("file") MultipartFile file) throws IOException {
        //添加文件
        Users user = userDao.getOne(userId);
        if(!file.isEmpty()){
            String upload = fileUpload.upload(file,"user",user.getUsername());
            System.out.println(upload);
            user.setHeadPortrait(upload);
            userDao.save(user);
            return Message.success("上传成功");
        }else {
            return Message.error("文件为空");
        }
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping(CRUD.DELETE_BY_ID)
    public Message deleteUser(@PathVariable String id){
        userDao.deleteById(id);
        return Message.success("删除成功").add("id",id);
    }

    @ApiOperation("删除全部用户信息")
    @DeleteMapping(CRUD.DELETE_ALL)
    public Message deleteAllUser(){
        userDao.deleteAll();
        return Message.success("删除成功");
    }

    @ApiOperation("发送验证码")
    @PostMapping("/checkVCode")
    public Message sendVCode(String phoneNumber){
        System.out.println(phoneNumber);
        int i = RandomUtil.randomInt(100000, 1000000);
        if(userDao.findByPhoneNumber(phoneNumber)!=null){
            System.out.println(Message.error("手机号码已注册"));
            return Message.error("手机号码已注册");
        }
        System.out.println(i);
        template.opsForValue().set(phoneNumber,String.valueOf(i));
        return Message.success("发送成功");
    }
}
