package com.example.demo.domian.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@ApiModel(value = "轮播图")
public class Carousel {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @ApiModelProperty(value = "轮播图id")
    String id;

    @ApiModelProperty(value = "图片")
    String picture;


    @ApiModelProperty(value = "轮播图描述")
    String description;

    @ApiModelProperty(value = "轮播图链接")
    String Url;

    @Override
    public String toString() {
        return "Carousel{" +
                "id=" + id +
                ", picture='" + picture + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }
}
