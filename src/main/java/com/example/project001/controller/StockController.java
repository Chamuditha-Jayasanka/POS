package com.example.project001.controller;

import com.example.project001.dto.ProductStockDto;
import com.example.project001.model.ProductModel;
import com.example.project001.model.ProductStockModel;
import com.example.project001.tm.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.util.ArrayList;


public class StockController {

    public ComboBox productField;
    public TextField quantityField;
    public TextField stock_id;

    public void initialize() {
        loadProductIds();

    }



    public void insert(ActionEvent actionEvent) {
        try {
            int productId = Integer.parseInt(productField.getValue().toString());
            int qty = Integer.parseInt(quantityField.getText());

            ProductStockDto dto = new ProductStockDto(productId, qty);
            int result = ProductStockModel.saveData(dto);

            if (result > 0) {
                System.out.println("Added Successfully");
                initialize();
            }else {
                System.out.println("Insert Failed");
            }
            } catch(NumberFormatException e ){
                System.out.println("Invalid number format: ");
            } catch(Exception e ){
                e.printStackTrace();

        }
    }

    public void update(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(stock_id.getText());
            int productId = Integer.parseInt(productField.getValue().toString());
            int qty = Integer.parseInt(quantityField.getText());

            ProductStockDto dto = new ProductStockDto(id, productId, qty, java.time.LocalDateTime.now());
            int result = ProductStockModel.updateData(dto);

            if (result > 0) {
                System.out.println("Updated Successfully");
                initialize();
            } else {
                System.out.println("Update Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(stock_id.getText());
            int result = ProductStockModel.deleteData(id);

            if (result > 0) {
                System.out.println("Deleted Successfully");
                initialize();
            } else {
                System.out.println("Delete Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(stock_id.getText());
            ProductStockDto dto = ProductStockModel.searchData(id);

            if (dto != null) {
                productField.setValue(dto.getProduct_id());
                quantityField.setText(String.valueOf(dto.getQty()));
                System.out.println("Search Successful");
            } else {
                System.out.println("No record found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void loadProductIds() {
        ArrayList<Product> products = ProductModel.getAllProducts();

        ObservableList idsList = FXCollections.observableArrayList();

        products.forEach(product -> {
            idsList.add(product.getId());
        });
        productField.setItems(idsList);
    }
}
