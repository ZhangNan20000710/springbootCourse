package com.example.demo.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.example.demo.annotation.AdminLoginToken;
import com.example.demo.dao.ITagsDao;
import com.example.demo.domian.Entity.Courses;
import com.example.demo.domian.Information.Message;
import com.example.demo.domian.Entity.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Api(tags = "标签接口")
@RestController
@RequestMapping("/Tag")
public class TagCRUD {

    @Autowired
    ITagsDao tagsDao;


    @ApiOperation("查询标签")
    @GetMapping(CRUD.FIND)
    public Message showTag(){
        Set<Tags> tags = new HashSet<>(tagsDao.findAll());
        long count = tagsDao.count();
        return Message.success().add("Tags", tags).add("count", count);
    }

    @ApiOperation("根据id查询标签")
    @GetMapping(CRUD.FIND_BY_ID)
    public Message showTagById(@PathVariable String id,@RequestParam(name = "index",defaultValue = "0") Integer index){
        List<Courses> courses = tagsDao.find(id);
        return Message.success().add("courses",courses);
    }

    @ApiOperation("搜索标签")
    @GetMapping(CRUD.SEARCH)
    @AdminLoginToken
    public Message searchTag(@PathVariable  String name){
        Set<Tags> tags =tagsDao.findByTagNameLike("%"+name+"%");
        return Message.success().add("Tags", tags).add("count", tags.size());
    }

    @ApiOperation("添加标签")
    @PostMapping(CRUD.SAVE)
    public Message addTag(Tags tag){
        tag.setCreateTime(new Date());
        tag.setUpdateTime(new Date());
        Tags save = tagsDao.save(tag);
        return Message.success("添加成功").add("tag",save);
    }


    @ApiOperation("修改标签")
    @PutMapping(CRUD.ALTER)
    public Message alterTag(Tags tag){
        /*通过id获取之前修改之前的数据*/
        Tags before = tagsDao.getOne(tag.getTagId());
        /*覆盖之前的数据并忽略空值*/
        BeanUtil.copyProperties(tag,before, CopyOptions.create().setIgnoreNullValue(true));
        before.setUpdateTime(new Date());
        Tags save = tagsDao.save(before);
        return Message.success("修改成功").add("tag",save);
    }


    @ApiOperation("删除标签")
    @DeleteMapping(CRUD.DELETE_BY_ID)
    @AdminLoginToken
    public Message deleteTag(@PathVariable String id){
        tagsDao.deleteById(id);
        return Message.success("删除成功").add("id",id);
    }

    @ApiOperation("删除全部标签")
    @DeleteMapping(CRUD.DELETE_ALL)
    @AdminLoginToken
    public Message deleteAllTag(){
        tagsDao.deleteAll();
        return Message.success("删除成功");
    }
}
