package com.yonyk.talaria.resources.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yonyk.talaria.resources.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
