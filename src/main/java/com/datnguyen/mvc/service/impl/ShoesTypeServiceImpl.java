package com.datnguyen.mvc.service.impl;


import com.datnguyen.mvc.entity.ShoesType;
import com.datnguyen.mvc.repository.ShoeRepository;
import com.datnguyen.mvc.repository.ShoesTypeRepository;
import com.datnguyen.mvc.service.ShoesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoesTypeServiceImpl implements ShoesTypeService {

    @Autowired
    private ShoeRepository shoeRepository;
    @Autowired
    private ShoesTypeRepository shoesTypeRepository;

    @Override
    public List<ShoesType> getAllShoesTypes() {
        return shoesTypeRepository.findAll();
    }
}
