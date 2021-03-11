package com.example.demo.dao;

import com.example.demo.domian.Entity.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface ITypesDao extends JpaRepository<Types,String>, JpaSpecificationExecutor<Types> {
    Set<Types> findByTypeNameLike(String name);
}
