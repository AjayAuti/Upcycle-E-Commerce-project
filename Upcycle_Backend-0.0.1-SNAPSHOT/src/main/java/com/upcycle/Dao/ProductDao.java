package com.upcycle.Dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.upcycle.Entity.Product;
import com.upcycle.Entity.Seller;




@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
	List<Product> findBySeller(Seller sellerId, Sort sort);
	List<Product> findByCategory(String category, Sort sort);
	
	@Query("SELECT p FROM Product p WHERE "+" p.pname LIKE CONCAT('%',:query, '%')" +" Or p.category LIKE CONCAT('%',:query, '%')")
	List<Product> searchProducts(String query);
	
}
