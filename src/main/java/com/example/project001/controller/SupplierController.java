package com.example.project001.controller;


import com.example.project001.dto.SupplierDto;
import com.example.project001.model.SupplierModel;
import com.example.project001.tm.Supplier1;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class SupplierController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtTel;
    public TableView<Supplier1> tableView;

    public void initialize() {
        tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("tel"));

        ArrayList<Supplier1> List = SupplierModel.getAllSuppliers();

        tableView.setItems(FXCollections.observableList(List));
    }

    public void addBtn(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtTel.getText());

        SupplierDto supplierDto = new SupplierDto(id, name, address, tel);

        int result = SupplierModel.saveData(supplierDto);


        if (result >= 0) {
            System.out.println("Added Successfully");
            initialize();
        } else {
            System.out.println("Not Added Successfully");
        }

    }

    public void updateBtn(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtTel.getText());

        SupplierDto supplierDto = new SupplierDto(id, name, address, tel);
        int result = SupplierModel.updateData(supplierDto);

        if (result >= 0) {
            System.out.println("Updated Successfully");
            initialize();
        } else {
            System.out.println("Not Updated Successfully");
        }
    }

    public void deleteBtn(ActionEvent actionEvent) {

        String id = txtId.getText();

        SupplierDto supplierDto = new SupplierDto(id);

        int result =  SupplierModel.deleteData(supplierDto);

        if (result > 0) {
            System.out.println("Deleted Successfully");
            initialize();
        }else {
            System.out.println("Not Deleted Successfully");
        }

    }

    public void searchBtn(ActionEvent actionEvent) {

        String id = txtId.getText();

        SupplierDto supplier = SupplierModel.searchData(id);

        if (supplier != null) {
            txtName.setText(supplier.getName());
            txtAddress.setText(supplier.getAddress());
            txtTel.setText(Integer.toString(supplier.getTel()));
            System.out.println("Search Successfully");
        } else {
            System.out.println("Supplier not found");
            txtName.clear();
            txtAddress.clear();
            txtTel.clear();
        }
    }

}