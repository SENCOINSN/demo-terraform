package com.sid.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sid.gl.models.Product;



public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
