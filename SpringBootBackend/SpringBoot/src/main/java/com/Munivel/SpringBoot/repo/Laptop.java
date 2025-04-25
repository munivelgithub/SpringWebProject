package com.Munivel.SpringBoot.repo;

import com.Munivel.SpringBoot.Model.Laptopmodel;
import com.Munivel.SpringBoot.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface Laptop extends JpaRepository<Laptopmodel,Integer
        > {
}
