package com.mhj.ranking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mhj.ranking.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
