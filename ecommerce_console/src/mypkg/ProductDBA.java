package mypkg;

import java.sql.*;
import java.util.ArrayList;

public class ProductDBA {
    private DBManipulations dm;
    public ProductDBA()
    {
        this.dm=new DBManipulations();
    }
    public int getMaxLength(ArrayList<String> arr) {
		int len = 0;
		for (String i : arr)
			len = Math.max(len, i.length());
		return len;
	}
    public String AddProductsDBA(String pro_name,String pro_description,String pro_price,String pro_offer,int color_id,int cat_id)
    {
        try{
            String query="INSERT INTO products(product_name,product_description,product_price,products_offer,color_id,category_id) VALUES(\"" + pro_name + "\",\"" + pro_description + "\",\"" + pro_price + "\",\"" + pro_offer + "\",\"" + color_id + "\",\"" + cat_id + "\")";
            dm.stmt.execute(query);
            return "New product added";
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return null;
    }

    public int getProductId(String productName) {
    try {
        String query = "SELECT product_id FROM products WHERE Product_name = ?";
        
        // Using a PreparedStatement to safely handle the product name parameter
        PreparedStatement preparedStatement = dm.con.prepareStatement(query);
        preparedStatement.setString(1, productName);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            return productId;
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
    return -1; 
}

public String nullStockUpdateDBA(int proid) {
    try {
        String query = "INSERT INTO stock(product_id,Quantity) VALUES(?,?)";
        PreparedStatement preparedStatement = dm.con.prepareStatement(query);
        preparedStatement.setInt(1, proid);  
        preparedStatement.setInt(2, 0);      
        preparedStatement.executeUpdate();
        return "Stock null updated";
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
    return null;
}
public String deleteProduct(int productId) {
    try {
        String stockQuery = "DELETE FROM stock WHERE product_id = ?";
        PreparedStatement stockStatement = dm.con.prepareStatement(stockQuery);
        stockStatement.setInt(1, productId);
        stockStatement.executeUpdate();
        stockStatement.close();
        String productQuery = "DELETE FROM products WHERE product_id = ?";
        PreparedStatement productStatement = dm.con.prepareStatement(productQuery);
        productStatement.setInt(1, productId);
        productStatement.executeUpdate();
        productStatement.close();
        return "Product Deleted";
    } catch (Exception e) {
        System.out.println(e.toString());
    }
    return null;
}


public void displayprodetailfordelete() {
    try {
        String query = "SELECT product_id,product_name FROM products";
        ResultSet rs = dm.con.createStatement().executeQuery(query);
        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        int columnCount = rsmd.getColumnCount();
        data.add(new ArrayList<>());
        for (int i = 1; i <= columnCount; i++) {
            data.add(new ArrayList<>());
            data.get(i).add(rsmd.getColumnName(i));
        }
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                data.get(i).add(rs.getString(i));
            }
        }

        for (int i = 0; i < data.get(1).size(); i++) {
            int totcnt = 0;
            for (int j = 1; j <= columnCount; j++) {
                int len = getMaxLength(data.get(j)) + 4;
                totcnt += len;
                int cur = data.get(j).get(i).length();
                int diff = len - cur;
                for (int k = 0; k < diff / 2; k++)
                    System.out.print(" ");
                System.out.print(data.get(j).get(i));
                for (int k = 0; k < diff / 2 + diff % 2; k++)
                    System.out.print(" ");
                if (j != columnCount)
                    System.out.print("|");
            }
            System.out.println();
            if (i == 0) {
                for (int j = 0; j < totcnt; j++)
                    System.out.print("-");
                System.out.println();
            }
        }

    } catch (Exception e) {
        System.out.println(e.toString());
    }
}
}
