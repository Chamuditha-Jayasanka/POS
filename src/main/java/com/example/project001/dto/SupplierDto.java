package com.example.project001.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor

@Getter
@Setter
public class SupplierDto {
    private String id;
    private String name;
    private String address;
    private int tel;

    public SupplierDto(String id) {
        this.id = id;
    }
}
