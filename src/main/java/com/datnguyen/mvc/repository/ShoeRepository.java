package com.datnguyen.mvc.repository;

import com.datnguyen.mvc.entity.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Integer> {
    List<Shoe> findAllByOrderByShoesNameAsc();
    List<Shoe> searchAllByShoesNameContainingOrderByShoesNameAsc(String shoesName);

    Shoe findByShoesNo(String shoesNo);
}
