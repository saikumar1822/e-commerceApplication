package com.learn.Ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.Ecommerce.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByProductNameLike(String productName);

}
