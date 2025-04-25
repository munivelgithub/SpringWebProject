package com.Munivel.SpringBoot.repo;

import com.Munivel.SpringBoot.Model.SamsungModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SamsungModelInterface extends JpaRepository<SamsungModel,Integer> {
}
