package com.odin.odinbff.repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.odin.odinbff.model.product.Brand;


public interface BrandRepository extends JpaRepository<Brand,Long>{

}
