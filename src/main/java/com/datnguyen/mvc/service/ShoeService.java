package com.datnguyen.mvc.service;


import com.datnguyen.mvc.dto.ShoeDTO;

import java.util.List;

public interface ShoeService {
    List<ShoeDTO> getAll();
    void save(ShoeDTO shoeRequest);
    boolean isShoesNoDuplicate(String shoesNo);
    ShoeDTO getById(Integer id);
    void update(Integer id, ShoeDTO shoeRequest);
    void delete(Integer id);
    List<ShoeDTO> searchByName(String name);
}
