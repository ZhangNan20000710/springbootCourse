//package com.example.demo.domian.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
//public class Roles {
//
//    @Id
//    @GenericGenerator(name="idGenerator", strategy="uuid")
//    @GeneratedValue(generator="idGenerator")
//    private String roleId;
//
//    private String roleName;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "role")
//    private Set<Users> user=new HashSet<>();
//
//    @ManyToMany(cascade = {CascadeType.ALL})
//    private Set<Permissions> permissions=new HashSet<>();
//}
