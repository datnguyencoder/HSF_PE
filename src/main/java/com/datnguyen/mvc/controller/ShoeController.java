package com.datnguyen.mvc.controller;

import com.datnguyen.mvc.dto.ShoeDTO;
import com.datnguyen.mvc.service.ShoeService;
import com.datnguyen.mvc.service.ShoesTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShoeController {

    @Autowired
    private ShoeService shoeService;

    @Autowired
    private ShoesTypeService shoesTypeService;

    @GetMapping("/")
    public String listShoes(@RequestParam(name = "shoesName", required = false) String shoesName, Model model) {
        if (shoesName != null && !shoesName.trim().isEmpty()) {
            model.addAttribute("shoes", shoeService.searchByName(shoesName.trim()));
            model.addAttribute("shoesName", shoesName);
        } else {
            model.addAttribute("shoes", shoeService.getAll());
        }
        return "list";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteConfirmation(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("shoe", shoeService.getById(id));
        return "delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteShoe(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        shoeService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Deleted successfully");
        return "redirect:/";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("shoeDTO", new ShoeDTO());
        model.addAttribute("types", shoesTypeService.getAllShoesTypes());
        return "add";
    }

    @PostMapping("/add")
    public String processAddForm(@Valid @ModelAttribute("shoeDTO") ShoeDTO shoeDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (shoeDTO.getShoesNo() != null && !shoeDTO.getShoesNo().isEmpty()) {
            if (shoeService.isShoesNoDuplicate(shoeDTO.getShoesNo())) {
                bindingResult.rejectValue("shoesNo", "error.shoeDTO", "Shoes No already exists");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("types", shoesTypeService.getAllShoesTypes());
            return "add";
        }

        shoeService.save(shoeDTO);
        redirectAttributes.addFlashAttribute("message", "Created new shoes successfully");
        return "redirect:/add";
    }

    @GetMapping("/view/{id}")
    public String viewShoe(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("shoeDTO", shoeService.getById(id));
        return "view";
    }
}
