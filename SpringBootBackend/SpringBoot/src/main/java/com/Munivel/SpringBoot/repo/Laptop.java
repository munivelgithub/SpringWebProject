package com.Munivel.SpringBoot.repo;

import com.Munivel.SpringBoot.Model.Laptopmodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Laptop extends JpaRepository<Laptopmodel,Integer> {
}
