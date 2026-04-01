package com.datnguyen.mvc.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "shoes")
public class Shoe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoes_id", nullable = false)
    private Integer shoesId;


    @Nationalized
    @Column(name = "shoes_no", nullable = false, length = 10)
    private String shoesNo;

    @Nationalized
    @Column(name = "shoes_name", nullable = false, length = 100)
    private String shoesName;


    @Column(name = "price", nullable = false)
    private Double price;


    @Nationalized
    @Column(name = "type", nullable = false, length = 10)
    private String type;

    public Shoe() {
    }

    public Shoe(Integer shoesId, String shoesNo, String shoesName, Double price, String type) {
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
