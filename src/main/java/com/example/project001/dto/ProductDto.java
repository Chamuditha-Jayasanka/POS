package com.example.project001.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {

    private String name;
    private String description;
    private double unit_price;
    private String supplier_id;

    public ProductDto(String name, String des, String price, Object id) {
        this.name = name;
        this.description = des;
        this.unit_price = Double.parseDouble(price); // convert string to double
        this.supplier_id = id.toString(); // convert object to string
    }

    public ProductDto(String name) {
        this.name = name;
    }
}