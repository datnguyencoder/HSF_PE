package com.datnguyen.mvc.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ShoeDTO {

    private Integer shoesId;

    @NotBlank(message = "Shoes No is required")
    @Size(max = 10, message = "Max 10 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Alphanumeric only, no leading/trailing spaces")
    private String shoesNo;

    @NotBlank(message = "Shoes Name is required")
    @Size(max = 100, message = "Max 100 characters")
    @Pattern(regexp = "^\\S+(?:\\s+\\S+)*$", message = "No leading/trailing spaces allowed")
    private String shoesName;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Must be greater than 0")
    @DecimalMax(value = "100000.0", inclusive = false, message = "Must be less than 100,000")
    private Double price;

    @NotBlank(message = "Type is required")
    private String type;

    public ShoeDTO() {
    }

    public ShoeDTO(Integer shoesId, String shoesNo, String shoesName, Double price, String type) {
        this.shoesId = shoesId;
        this.shoesNo = shoesNo;
        this.shoesName = shoesName;
        this.price = price;
        this.type = type;
    }

    public Integer getShoesId() {
        return shoesId;
    }

    public void setShoesId(Integer shoesId) {
        this.shoesId = shoesId;
    }

    public String getShoesNo() {
        return shoesNo;
    }

    public void setShoesNo(String shoesNo) {
        this.shoesNo = shoesNo;
    }

    public String getShoesName() {
        return shoesName;
    }

    public void setShoesName(String shoesName) {
        this.shoesName = shoesName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
