package com.example.project001.model;

import com.example.project001.db.DataBaseConnection;
import com.example.project001.dto.ProductDto;
import com.example.project001.dto.SupplierDto;
import com.example.project001.tm.Product;
import com.example.project001.tm.Supplier1;

import java.sql.*;
import java.util.ArrayList;

public class ProductModel {

    public static int saveProduct(ProductDto productDto) {
        try {
            String addNewProduct = "INSERT INTO product (name, description, unit_price, supplier_id) VALUES (?,?,?,?)";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem", "root", "1234");

            PreparedStatement ps = conn.prepareStatement(addNewProduct);
            ps.setString(1, productDto.getName());
            ps.setString(2, productDto.getDescription());
            ps.setDouble(3,productDto.getUnit_price());
            ps.setString(4,productDto.getSupplier_id());

            int result = ps.executeUpdate();
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int updateData(ProductDto productDto) {
        try {

            String SQL = "UPDATE  product SET name = ?,description = ?, unit_price = ? WHERE supplier_id = ?";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem","root","1234");

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, productDto.getName());
            ps.setString(2, productDto.getDescription());
            ps.setDouble(3, productDto.getUnit_price());
            ps.setString(4, productDto.getSupplier_id());


            int result = ps.executeUpdate();
            return result;


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int deleteData(ProductDto productDto) {

        try {
            String delete = "Delete FROM product WHERE name = ? ";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem", "root", "1234");

            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setString(1,productDto.getName());

            int result = ps.executeUpdate();
            return result;


        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return 0;
    }

    public static ProductDto searchProduct(String productName) {
        try {
            String SQL = "SELECT * FROM product WHERE name = ?";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/possystem", "root", "1234");

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, productName);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ProductDto productDto = new ProductDto();
                productDto.setName(rs.getString("name"));
                productDto.setDescription(rs.getString("description"));
                productDto.setUnit_price(rs.getDouble("unit_price")); // ✅ matches DB column
                productDto.setSupplier_id(rs.getString("supplier_id")); // ✅ matches DB column

                conn.close();
                return productDto; // found product
            }

            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<Product> getAllProducts() {

        try {
            String SQL = "SELECT * FROM Product";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem","root","1234");
            PreparedStatement ps = conn.prepareStatement(SQL);


            ResultSet result = ps.executeQuery();

            ArrayList<Product> products = new ArrayList<>();


            while (result.next()){
                products.add(new Product(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getDouble("unit_price"),
                        result.getString("supplier_id")

                ));

            }
            return products;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



}
