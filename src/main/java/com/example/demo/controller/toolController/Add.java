//package com.example.demo.controller.toolController;
//
//
//import com.example.demo.dao.ITagsDao;
//import com.example.demo.dao.ITypesDao;
//import com.example.demo.domian.Entity.Tags;
//import com.example.demo.domian.Entity.Types;
//import com.example.demo.domian.Information.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.FileNotFoundException;
//@RestController
//public class Add {
//    @Autowired
//    ITagsDao tagsDao;
//
//    @Autowired
//    ITypesDao typeDao;
//
//    @RequestMapping("/add")
//    public Message contextLoads(){
//        String a="国家精品\n" +
//                "计算机\n" +
//                "外语\n" +
//                "理学\n" +
//                "工学\n" +
//                "22考研\n" +
//                "期末不挂科\n" +
//                "应试英语\n" +
//                "实用英语\n" +
//                "经济管理\n" +
//                "心理学\n" +
//                "文史哲\n" +
//                "艺术设计\n" +
//                "医药卫生\n" +
//                "教育教学\n" +
//                "法学\n" +
//                "农林园艺\n" +
//                "音乐与舞蹈\n" +
//                "21考研\n" +
//                "考证就业\n" +
//                "名师专栏";
//        String b="前沿技术\n" +
//                "软件工程\n" +
//                "听力/口语\n" +
//                "写作/翻译\n" +
//                "数学\n" +
//                "物理\n" +
//                "化学\n" +
//                "天文学\n" +
//                "地理科学\n" +
//                "生物科学\n" +
//                "大气与海洋\n" +
//                "力学\n" +
//                "材料\n" +
//                "数学\n" +
//                "名师英语\n" +
//                "408计算机\n" +
//                "数学不挂科\n" +
//                "物理不挂科\n" +
//                "四六级\n" +
//                "万词班\n" +
//                "考研英语\n" +
//                "万词班\n" +
//                "新概念\n" +
//                "经济\n" +
//                "金融\n" +
//                "电商与贸易\n" +
//                "会计\n" +
//                "文学文化\n" +
//                "新闻传播\n" +
//                "哲学\n" +
//                "历史\n" +
//                "艺术学\n" +
//                "美术学\n" +
//                "戏剧与影视\n" +
//                "设计学\n" +
//                "基础医学\n" +
//                "临床医学\n" +
//                "公共卫生\n" +
//                "口腔医学\n" +
//                "教学方法\n" +
//                "教学能力\n" +
//                "信息化教学\n" +
//                "职业素养\n" +
//                "法学\n" +
//                "思政\n" +
//                "社会\n" +
//                "植物\n" +
//                "动物\n" +
//                "生态\n" +
//                "数学抢分\n" +
//                "政治押题\n" +
//                "公务员考试\n" +
//                "办公技能\n" +
//                "热门专栏\n" +
//                "情商\n" +
//                "效率";
//        String[] splitA = a.split("\n");
//        String[] splitB = b.split("\n");
//
//        for (String s : splitA) {
//            Types type = new Types();
//            type.setTypeName(s);
//            typeDao.save(type);
//        }
//
//        for (String s : splitB) {
//            Tags tag=new Tags();
//            tag.setTagName(s);
//            tagsDao.save(tag);
//        }
//        return Message.success();
//    }
//}
