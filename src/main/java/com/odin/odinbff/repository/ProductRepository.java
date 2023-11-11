package com.odin.odinbff.repository;

import org.springframework.data.jpa.repository.JpaRepository;  

import com.odin.odinbff.model.product.Product;

import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long>{

    Set<Product> findAllByIsActive(final boolean isActive);
}
