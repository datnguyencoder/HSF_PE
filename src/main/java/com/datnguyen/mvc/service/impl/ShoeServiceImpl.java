package com.datnguyen.mvc.service.impl;

import com.datnguyen.mvc.dto.ShoeDTO;
import com.datnguyen.mvc.entity.Shoe;
import com.datnguyen.mvc.entity.ShoesType;
import com.datnguyen.mvc.repository.ShoeRepository;
import com.datnguyen.mvc.repository.ShoesTypeRepository;
import com.datnguyen.mvc.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoeServiceImpl implements ShoeService {
    @Autowired
    private ShoeRepository shoeRepository;

    @Autowired
    private ShoesTypeRepository shoesTypeRepository;


    @Override
    public List<ShoeDTO> getAll() {

        return searchByName("");
    }

    @Override
    public void save(ShoeDTO shoeRequest) {
        Shoe shoe = new Shoe();
        shoe.setShoesNo(shoeRequest.getShoesNo());
        shoe.setShoesName(shoeRequest.getShoesName());
        shoe.setPrice(shoeRequest.getPrice());
        shoe.setType(shoeRequest.getType());
        shoeRepository.save(shoe);
    }

    @Override
    public boolean isShoesNoDuplicate(String shoesNo) {
        return shoeRepository.findByShoesNo(shoesNo) != null;
    }

    @Override
    public ShoeDTO getById(Integer id) {
        return shoeRepository.findById(id).map(this::convertEntityToResponse).orElse(null);
    }

    @Override
    public void update(Integer id, ShoeDTO shoeRequest) {

    }

    @Override
    public void delete(Integer id) {
        shoeRepository.deleteById(id);
    }

    @Override
    public List<ShoeDTO> searchByName(String name) {
        List<Shoe> shoes;
        if (name == null || name.trim().isEmpty()) {
            shoes = shoeRepository.findAllByOrderByShoesNameAsc();
        } else {
            shoes = shoeRepository.searchAllByShoesNameContainingOrderByShoesNameAsc(name.trim());
        }

        List<ShoeDTO> shoeDTOs = new ArrayList<>();
        for (Shoe shoe : shoes) {
            shoeDTOs.add(convertEntityToResponse(shoe));
        }
        return shoeDTOs;
    }

    private ShoeDTO convertEntityToResponse(Shoe shoe) {
        ShoeDTO shoeDTO = new ShoeDTO();
        shoeDTO.setShoesId(shoe.getShoesId());
        shoeDTO.setShoesNo(shoe.getShoesNo());
        shoeDTO.setShoesName(shoe.getShoesName());
        shoeDTO.setPrice(shoe.getPrice());

        String typeName = shoesTypeRepository.findById(shoe.getType())
                .map(ShoesType::getTypeName)
                .orElse(shoe.getType());
        shoeDTO.setType(typeName);

        return shoeDTO;
    }
}
