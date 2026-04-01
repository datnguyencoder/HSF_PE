package com.datnguyen.mvc.repository;

import com.datnguyen.mvc.entity.ShoesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesTypeRepository extends JpaRepository<ShoesType, String> {
}
