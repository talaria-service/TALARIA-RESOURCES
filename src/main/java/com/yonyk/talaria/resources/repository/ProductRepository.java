package com.yonyk.talaria.resources.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.yonyk.talaria.resources.entity.Product;
import com.yonyk.talaria.resources.entity.enums.ProductType;

public interface ProductRepository extends JpaRepository<Product, Long> {

  Page<Product> findByProductType(Pageable pageable, ProductType productType);
}
