package com.odin.odinbff.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;  

import com.odin.odinbff.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}