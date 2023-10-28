package com.odin.odinbff.repository;

import org.springframework.data.jpa.repository.JpaRepository;  

import com.odin.odinbff.model.Product;

public interface ProdutoRepository extends JpaRepository<Product,Long >{

}
