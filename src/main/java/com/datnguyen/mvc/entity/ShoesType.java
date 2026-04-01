package com.datnguyen.mvc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "shoes_type")
public class ShoesType {
    @Id
    @Column(name = "type_code", nullable = false, length = 10)
    private String typeCode;

    @Nationalized
    @Column(name = "type_name", nullable = false, length = 50)
    private String typeName;

    public ShoesType() {
    }

    public ShoesType(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
