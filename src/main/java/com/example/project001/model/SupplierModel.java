package com.example.project001.model;

import com.example.project001.dto.SupplierDto;
import com.example.project001.tm.Supplier1;

import java.sql.*;
import java.util.ArrayList;

public class SupplierModel {

    public static int saveData(SupplierDto supplierDto) {
        try {

            String SQL = "INSERT INTO Supplier VALUES(?, ?, ?, ?)";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem","root","1234");

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, supplierDto.getId());
            ps.setString(2, supplierDto.getName());
            ps.setString(3, supplierDto.getAddress());
            ps.setInt(4, supplierDto.getTel());

            int result = ps.executeUpdate();
            return result;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int updateData(SupplierDto supplierDto) {
        try {

            String SQL = "UPDATE  Supplier SET sname = ?, address = ?, tel = ? WHERE sid = ?";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem","root","1234");

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, supplierDto.getName());
            ps.setString(2, supplierDto.getAddress());
            ps.setInt(3, supplierDto.getTel());
            ps.setString(4, supplierDto.getId());

            int result = ps.executeUpdate();
            return result;


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

        public static int deleteData(SupplierDto supplierDto) {

        try {
            String delete = "Delete FROM Supplier WHERE sid = ? limit 1";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem", "root", "1234");

            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setString(1,supplierDto.getId());

            int result = ps.executeUpdate();
            return result;


        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


            return 0;
        }

            public static SupplierDto searchData(String supplierId) {

            SupplierDto found = null;
            try {
                String SQL = "SELECT * FROM Supplier WHERE sid = ?";

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/possystem", "root", "1234");

                PreparedStatement ps = conn.prepareStatement(SQL);
                ps.setString(1, supplierId);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    found = new SupplierDto(
                            rs.getString("sid"),
                            rs.getString("sname"),
                            rs.getString("address"),
                            rs.getInt("tel")
                    );
                }

                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return found;
        }

    public static ArrayList<Supplier1> getAllSuppliers() {

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
