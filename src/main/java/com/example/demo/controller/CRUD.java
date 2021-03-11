package com.example.demo.controller;

//import org.springframework.data.jpa.domain.Specification;
//
//import javax.persistence.criteria.*;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;

public class CRUD<T> {
    static final String FIND="/show";
    static final String SAVE="/add";
    static final String ALTER="/alter";
    static final String DELETE_BY_ID="/delete/{id}";
    static final String DELETE="/delete";
    static final String DELETE_ALL="/deleteAll";
    static final String FIND_LOGIN="/showLogin";
    static final String FIND_BY_ID="/show/{id}";
    static final String SEARCH="/search/{name}";

//    public Specification<T> getSpec(Object o){
//        Specification<T> spec=new Specification<T>() {
//            @Override
//            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
//
//                List<Predicate> predicates=new ArrayList<>();
//                try {
//                    Class aClass = o.getClass();
//                    for (Field df : aClass.getDeclaredFields()){
//                        /*设置获取方法名*/
//                        String methodName = "get" + df.getName().substring(0, 1).toUpperCase() + df.getName().substring(1);
//                        Method dm = aClass.getDeclaredMethod(methodName);
//                        if (dm.invoke(o)!=null&&(!(dm.invoke(o)instanceof Set))) {
//                            System.out.println(dm.invoke(o));
//                            predicates.add(cb.equal(root.get(df.getName()), dm.invoke(o)));
//                        }
//                    }
//                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//                return cb.or(predicates.toArray(new Predicate[predicates.size()]));
//            }
//        };
//        return spec;
//    }
}
