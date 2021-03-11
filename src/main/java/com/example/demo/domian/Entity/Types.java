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
@ApiModel(value = "课程种类")
public class Types {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @ApiModelProperty(value = "种类id")
    private String typeId;

    @ApiModelProperty(value = "种类名称")
    private String typeName;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "种类创建时间")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(value = "种类更新时间")
    private Date updateTime;

    @JsonIgnore
    @OneToMany(mappedBy = "type",cascade = CascadeType.ALL)
    private Set<Courses> courses= new HashSet<>();

    @Override
    public String toString() {
        return "Types{" +
                "typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
