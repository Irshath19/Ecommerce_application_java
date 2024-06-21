package mypkg;

import java.sql.*;
import java.time.LocalDate;

public class BuyNowdba {
    private DBManipulations dm;
    public BuyNowdba()
    {
        this.dm=new DBManipulations();
    }
    public double Getprice(int pro_id)
    {
         try {
            String sqlQuery = "SELECT product_price FROM products WHERE product_id = ?";
            PreparedStatement preparedStatement = dm.con.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, pro_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double productPrice = resultSet.getDouble("product_price");
                return productPrice;
            } else {
                System.out.println("No product found with product_id: " + pro_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public String buynowdba(int pro,int quant,int user,LocalDate two,double price,String order_status)
    {
        try {
            String query = "INSERT INTO orders (product_id, user_id, quantity, Total_amount, Delivery_date,order_status) VALUES (?, ?, ?, ?, ?, ?)";
             PreparedStatement preparedStatement = dm.con.prepareStatement(query);
            preparedStatement.setInt(1, pro);
            preparedStatement.setInt(2, user);
            preparedStatement.setInt(3, quant);
            preparedStatement.setDouble(4, price);
            preparedStatement.setDate(5, Date.valueOf(two));
            preparedStatement.setString(6,order_status);
            preparedStatement.executeUpdate();
            return "Order Placed...Get ready with your total amount of "+price;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
    public void updatestatusdba()
    {
        try {
            String query = "UPDATE orders SET order_status = 'Complete' WHERE Delivery_date <= ?";
            PreparedStatement statement =dm.con.prepareStatement(query);// 22/04/2024 //24/04/2024
            statement.setDate(1, java.sql.Date.valueOf(LocalDate.now())); 
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " rows updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getQuantityFromStock(int productId) {
        try {
            String query = "SELECT Quantity FROM stock WHERE product_id = " + productId;
            ResultSet resultSet = dm.stmt.executeQuery(query);
             if (resultSet.next()) {
                int quantity = resultSet.getInt("Quantity");
                return quantity;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return -1;
    }
    public String decreaseStockQuantity(int productId, int quantityToReduce) {
        try {
            String query = "UPDATE stock SET Quantity = Quantity - " + quantityToReduce + " WHERE Product_id = " + productId;
            int rowsAffected = dm.stmt.executeUpdate(query);
            if (rowsAffected > 0) {
                return "Decreased";
            } else {
                System.out.println("Insufficient stock for ProductID: " + productId);
            }
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
    
    
    }
