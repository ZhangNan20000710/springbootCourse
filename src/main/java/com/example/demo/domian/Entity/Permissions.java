//package com.example.demo.domian.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//public class Permissions {
//    @Id
//    @GenericGenerator(name="idGenerator", strategy="uuid")
//    @GeneratedValue(generator="idGenerator")
//    private String permissionId;
//
//    private String permissionName;
//
//    @JsonIgnore
//    @ManyToMany(mappedBy = "permissions")
//    private Set<Roles> roles=new HashSet<>();
//
//}
