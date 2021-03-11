//package com.example.demo.domian.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.catalina.User;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Getter
//@Setter
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
//@ApiModel(value = "浏览记录")
//public class BrowsingHistory {
//    @Id
//    @GenericGenerator(name="idGenerator", strategy="uuid")
//    @GeneratedValue(generator="idGenerator")
//    @ApiModelProperty(value = "浏览记录id")
//    private String BrowsingHistoryId;
//
//    @JoinColumn(name = "course_id")
//    @ManyToOne
//    @ApiModelProperty(value = "课程id")
//    private Courses course;
//
//    @JoinColumn(name = "user_id")
//    @ManyToOne
//    @ApiModelProperty(value = "用户id")
//    private Users user;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    @ApiModelProperty(value = "用户浏览的时间")
//    private Date browseTime;
//
//    @Override
//    public String toString() {
//        return "BrowsingHistory{" +
//                "BrowsingHistoryId='" + BrowsingHistoryId + '\'' +
//                ", course=" + course +
//                ", user=" + user +
//                '}';
//    }
//}
