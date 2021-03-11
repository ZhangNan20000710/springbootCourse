package com.example.demo.dao;

import com.example.demo.domian.Entity.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ICarouselDao extends JpaRepository<Carousel,String>, JpaSpecificationExecutor<Carousel> {
}
