package com.example.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.example.demo.annotation.AdminLoginToken;
import com.example.demo.dao.ITypesDao;
import com.example.demo.domian.Entity.Tags;
import com.example.demo.domian.Information.Message;
import com.example.demo.domian.Entity.Types;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Api(tags = "种类接口接口")
@RequestMapping("/Type")
@RestController
public class TypeCRUD {

    @Autowired
    ITypesDao typesDao;

    @ApiOperation("查询类别")
    @GetMapping(CRUD.FIND)
    public Message showType(){
        /*展示所有类别*/
        Set<Types> types = new HashSet<>(typesDao.findAll());
        long count = typesDao.count();
        return Message.success().add("types",types).add("count",count);
    }

    @ApiOperation("搜索种类")
    @GetMapping(CRUD.SEARCH)
    @AdminLoginToken
    public Message searchTag(@PathVariable String name){
        Set<Types> types = typesDao.findByTypeNameLike("%"+name+"%");
        return Message.success().add("types", types).add("count", types.size());
    }

    @ApiOperation("保存种类")
    @PostMapping(CRUD.SAVE)
    public Message addType(Types type){
        type.setCreateTime(new Date());
        type.setUpdateTime(new Date());
        Types save = typesDao.save(type);
        return Message.success("添加成功").add("type",save);
    }

    @ApiOperation("修改种类")
    @PostMapping(CRUD.ALTER)
    public Message alterType(Types type){
        /*通过id获取之前修改之前的数据*/
        Types before = typesDao.getOne(type.getTypeId());
        /*覆盖之前的数据并忽略空值*/
        BeanUtil.copyProperties(type,before, CopyOptions.create().setIgnoreNullValue(true));
        before.setUpdateTime(new Date());
        Types save = typesDao.save(before);
        return Message.success("添加成功").add("type",save);
    }

    @ApiOperation("删除种类")
    @DeleteMapping(CRUD.DELETE_BY_ID)
    @AdminLoginToken
    public Message deleteType(@PathVariable String id){
        typesDao.deleteById(id);
        return Message.success("删除成功").add("id",id);
    }

    @ApiOperation("删除全部种类")
    @DeleteMapping(CRUD.DELETE_ALL)
    @AdminLoginToken
    public Message deleteAllType(){
        typesDao.deleteAll();
        return Message.success("删除成功");
    }
}