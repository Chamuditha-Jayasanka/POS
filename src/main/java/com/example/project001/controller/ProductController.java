package com.example.project001.controller;

import com.example.project001.dto.ProductDto;
import com.example.project001.dto.SupplierDto;
import com.example.project001.model.ProductModel;
import com.example.project001.model.SupplierModel;
import com.example.project001.tm.Supplier1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ProductController {
    public TextField txtName;
    public TextField txtDes;
    public TextField txtPrice;
    public TableView tableView;
    public ComboBox txtid;

    public void initialize() {
        loadSupplierIds();

    }

    public void addBtn(ActionEvent actionEvent) {
        String name  = txtName.getText();
        String des   = txtDes.getText();
        String price = txtPrice.getText();
        Object id    = txtid.getValue();

        ProductDto productDto = new ProductDto(name, des, price, id);
        int result = ProductModel.saveProduct(productDto);

        if (result >= 0) {
            System.out.println("Added Successfully");
            initialize();
        } else {
            System.out.println("Not Added Successfully");
        }

    }

    public void updateBtn(ActionEvent actionEvent) {
        String name = txtName.getText();
        String des = txtDes.getText();
        String price = txtPrice.getText();
        Object id = txtid.getValue();

        ProductDto productDto = new ProductDto(name, des, price, id);
        int result = ProductModel.updateData(productDto);

        if (result >= 0) {
            System.out.println("Update Successfully");
            initialize();
        } else {
            System.out.println("Not Upadte Successfully");
        }

    }

    public void deleteBtn(ActionEvent actionEvent) {
        String name = txtName.getText();

        ProductDto productDto = new ProductDto(name);

        int result =  ProductModel.deleteData(productDto);

        if (result > 0) {
            System.out.println("Deleted Successfully");
            initialize();
        }else {
            System.out.println("Not Deleted Successfully");
        }
    }

    public void searchBtn(ActionEvent actionEvent) {
        String name = txtName.getText();

        ProductDto productDto = ProductModel.searchProduct(name);

        if (productDto != null) {
            txtName.setText(productDto.getName());
            txtDes.setText(productDto.getDescription());
            txtPrice.setText(String.valueOf(productDto.getUnit_price()));
            txtid.setValue(productDto.getSupplier_id());
            System.out.println("Product Found");
        } else {
            System.out.println("Product Not Found");
        }
    }

    public void loadSupplierIds(){
        ArrayList<Supplier1> supplier1s = SupplierModel.getAllSuppliers();

        ObservableList idsList = FXCollections.observableArrayList();

        supplier1s.forEach(supplier1 -> {
            idsList.add(supplier1.getId());
        });
        txtid.setItems(idsList);


    }
}
