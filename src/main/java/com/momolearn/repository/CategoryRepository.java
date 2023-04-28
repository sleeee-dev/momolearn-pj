package com.momolearn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.momolearn.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Optional<Category> findByCateName(String cate);
}
