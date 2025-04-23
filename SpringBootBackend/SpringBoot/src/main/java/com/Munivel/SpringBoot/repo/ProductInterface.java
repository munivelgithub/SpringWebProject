package com.Munivel.SpringBoot.repo;

import com.Munivel.SpringBoot.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

// if you need to search by name , id, quantity
@Repository
public interface ProductInterface extends JpaRepository<Product,Integer> {
    // if you want to search by brand or name what you will do/ to search literly write
    // jpql jpa query language (similar to sql in sql we use only the tables here we using the class name in sql we use column name here we use field name
    // in sql we used select * from the table where name like '%term%'; to find by an key word
    //use jpql
    // converting every text with lower and comparing with keyword
    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")

    //lower is an inbuild method
  List<Product> searchProduct(String keyword);
}
