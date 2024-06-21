package mypkg;

import java.sql.*;
import java.util.*;

public class StockDBA {
    private DBManipulations dm;
    public StockDBA()
    {
        this.dm=new DBManipulations();
    }
    AdminDBA adb=new AdminDBA();
	public String updateStockDBA(int proid, int quantity) {
		try {
			String query = "UPDATE stock SET Quantity = Quantity + " + quantity + " WHERE product_id = " + proid;
			Statement statement = dm.con.createStatement();
			int rowsAffected = statement.executeUpdate(query);
			statement.close();
			if (rowsAffected > 0) {
				return "Updated";
			} else {
				System.out.println("No rows updated");
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}
	// public String updateStockDBA(int proid, int quantity) {
	// 	System.out.println();
	// 	System.out.println(proid);
	// 	System.out.println(quantity);
	// 	try {
	// 		String query = "UPDATE stock SET Quantity = Quantity + ? WHERE product_id = ?";
	// 		PreparedStatement preparedStatement = dm.con.prepareStatement(query);
	// 		preparedStatement.setInt(1, quantity);
	// 		preparedStatement.setInt(2, proid);
	// 		preparedStatement.executeUpdate();

	// 		return "Updated";
	// 	} catch(Exception e) {
	// 		System.out.println(e.toString());
	// 	}
	// 	return null;
	// }
	
    
    public void prodisplay()
    {
      	try {
			ResultSet rs = dm.con.createStatement().executeQuery("SELECT product_id,product_name FROM products");
			ResultSetMetaData rsmd = rs.getMetaData();
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
					int len = adb.getMaxLength(data.get(j)) + 4;
					totcnt += len;
					int cur = data.get(j).get(i).length();
					int diff = len - cur;
					for (int k = 0; k < diff / 2; k++) System.out.print(" ");
					System.out.print("\u001B[2m" + data.get(j).get(i) + "\u001B[0m"); // Set font size smaller
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
    public void categorydisplay()
    {
      	try {
			ResultSet rs = dm.con.createStatement().executeQuery("SELECT * FROM category");
			ResultSetMetaData rsmd = rs.getMetaData();
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
					int len = adb.getMaxLength(data.get(j)) + 4;
					totcnt += len;
					int cur = data.get(j).get(i).length();
					int diff = len - cur;
					for (int k = 0; k < diff / 2; k++) System.out.print(" ");
					System.out.print("\u001B[2m" + data.get(j).get(i) + "\u001B[0m"); // Set font size smaller
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
