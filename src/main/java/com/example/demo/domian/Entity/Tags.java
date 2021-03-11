package com.example.demo.domian.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@ApiModel(value = "课程标签")
public class Tags {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @ApiModelProperty(value = "标签id")
    private String tagId;

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "标签创建时间")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "标签更新时间")
    private Date updateTime;

//    @JsonIgnore
//    @OneToMany(mappedBy = "tag",cascade = CascadeType.ALL)
//    private Set<coursesTags> courses=new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private Set<Courses> courses=new HashSet<>();

    @Override
    public String toString() {
        return "Tags{" +
                "tagId='" + tagId + '\'' +
                ", tagName='" + tagName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

