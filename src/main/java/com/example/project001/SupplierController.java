package com.example.project001;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

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

        ArrayList<Supplier1> List = getAllSuppliers();
        tableView.setItems(FXCollections.observableList(List));
    }

    public void addBtn(ActionEvent actionEvent) {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtTel.getText());

        try {

            String SQL = "INSERT INTO Supplier VALUES(?, ?, ?, ?)";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem","root","1234");

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, address);
            ps.setInt(4, tel);



            int result = ps.executeUpdate();

            if (result >= 1) {
                System.out.println("Added Successfully");
            }else {
                System.out.println("Not Added Successfully");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void updateBtn(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtTel.getText());

        try {

            String SQL = "UPDATE  Supplier SET sname = ?, address = ?, tel = ? WHERE sid = ?";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem","root","1234");

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setInt(3, tel);
            ps.setString(4, id);



            int result = ps.executeUpdate();

            if (result >= 0) {
                System.out.println("Updated Successfully");
            }else {
                System.out.println("Not Updated Successfully");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteBtn(ActionEvent actionEvent) {
        String id = txtId.getText();


        try {

            String SQL = "Delete FROM Supplier WHERE sid = ?";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem","root","1234");

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, id);



            int result = ps.executeUpdate();

            if (result >= 0) {
                System.out.println("Delete Successfully");
            }else {
                System.out.println("Not Delete Successfully");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchBtn(ActionEvent actionEvent) {
        String id = txtId.getText();


        try {

            String SQL = "SELECT * FROM Supplier WHERE sid = ?";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem","root","1234");

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, id);




            ResultSet result = ps.executeQuery();

            if (result.next()){


               txtName.setText(result.getString("sname"));
               txtAddress.setText(result.getString("address"));
               txtTel.setText(Integer.toString(result.getInt("tel")));
                System.out.println("Search Successfully");

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public ArrayList<Supplier1> getAllSuppliers() {

        try {
        String SQL = "SELECT * FROM Supplier";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem","root","1234");

        PreparedStatement ps = conn.prepareStatement(SQL);


        ResultSet result = ps.executeQuery();

            ArrayList<Supplier1> suppliers = new ArrayList<>();


            while (result.next()){
                suppliers.add(new Supplier1(
                        result.getString("sid"),
                        result.getString("sname"),
                        result.getString("address"),
                        String.valueOf(result.getInt("tel"))
                ));

        }
            return suppliers;

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
        return null;
    }
}