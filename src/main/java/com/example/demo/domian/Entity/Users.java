package com.example.demo.domian.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@ApiModel(value = "用户")
public class Users{
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String username;

//    @JsonIgnore
    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户头像")
    private String headPortrait;

    @ApiModelProperty(value = "生日")
    private String birthday;

    @ApiModelProperty(value = "用户创建时间")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ApiModelProperty(value = "用户更新时间")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ApiModelProperty(value = "登录时间")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;

    @ApiModelProperty(value = "上次登录时间")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginTime;

    @ApiModelProperty(value = "备注")
    private String remarks;


    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    private Set<Courses> courses=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    Set<Comments> comments=new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    private Set<CoursesUsers> coursesUser=new HashSet<>();

    @Override
    public String toString() {
        return "Users{" +
                "UserId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", loginTime=" + loginTime +
                ", lastLoginTime=" + lastLoginTime +
                ", remarks='" + remarks + '\'' +
//                ", role='" + role + '\'' +
                '}';
    }
}
