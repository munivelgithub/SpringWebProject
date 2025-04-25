package com.Munivel.SpringBoot.repo;

import com.Munivel.SpringBoot.Model.Electronic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectronicInterface extends JpaRepository<Electronic,Integer> {
}
