package mypkg;

import java.sql.*;
import java.util.ArrayList;

public class UserDBA {
	private DBManipulations dm;

	public UserDBA() {
		this.dm = new DBManipulations();
	}
	public boolean checkuserduplicate(String usermail)
	{
		try{
			String query="SELECT * FROM user WHERE user_mail=?";
			PreparedStatement preparedStatement = dm.con.prepareStatement(query);
            preparedStatement.setString(1, usermail);
            ResultSet res = preparedStatement.executeQuery();
            return res.next();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return true;
	}
	public String addnewuserdba(String name, char gender, int age, String phonenumber, String mail, String address,
			String city, String password) {
		try {
			String query = "INSERT INTO user (user_fullname,user_gender,user_age,user_phonenumber,user_mail,user_address,user_city,user_password) VALUES(\""
					+ name + "\",\"" + gender + "\",\"" + age + "\",\"" + phonenumber + "\",\"" + mail + "\",\""
					+ address + "\",\"" + city + "\",\"" + password + "\")";
			dm.stmt.execute(query);
			return "Registered Successfully";
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public boolean isusertheredba(String username,String password)
    {
		try {
            String query = "SELECT * FROM user WHERE user_mail = ? AND user_password = ?";
            PreparedStatement preparedStatement = dm.con.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
        	if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return false;
    }

	public int getMaxLength(ArrayList<String> arr) {
		int len = 0;
		for (String i : arr)
			len = Math.max(len, i.length());
		return len;
	}

	public void displayprolist(int category) {
		try {

			ResultSet rs = dm.con.createStatement()
					.executeQuery("select product_name from products where category_id=" + category);
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

	public void displayprodetaildba(String pro) {
		try {
			String query = "SELECT * FROM products WHERE product_name = '" + pro + "'";
			ResultSet rs = dm.con.createStatement().executeQuery(query);
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

	public int getproductid(String product_name, String tablename, String columnname, String wherecolname) {
		int productId = -1;
		try {
			String sqlQuery = "SELECT " + columnname + " FROM " + tablename + " WHERE " + wherecolname + " = ?";
			PreparedStatement preparedStatement = dm.con.prepareStatement(sqlQuery);
			
			// Set the value of the parameter (product_name)
			preparedStatement.setString(1, product_name);
			
			// Execute the query
			ResultSet rs = preparedStatement.executeQuery();
			
			// Check if a row was returned
			if (rs.next()) {
				// Retrieve the product ID from the result set
				productId = rs.getInt(columnname);
			}
			
			// Close the result set and prepared statement
			rs.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return productId;
	}
	public void viewmyorderdba(String username)
	{
		try {
			// ResultSet rs = dm.con.createStatement().executeQuery("SELECT o.order_id,o.quantity,o.order_status, p.product_id,p.product_description,p.product_price,p.products_offer,o.Total_amount,o.Delivery_date FROM orders o INNER JOIN products p ON o.product_id = p.product_id WHERE o.user_id="+username);
			// ResultSetMetaData rsmd = rs.getMetaData();
			String query = "SELECT o.order_id, o.quantity, o.order_status, " +
                            "p.product_id, p.product_description, p.product_price, " +
                            "p.products_offer, o.Total_amount, o.Delivery_date " +
                            "FROM orders o " +
                            "INNER JOIN products p ON o.product_id = p.product_id " +
                            "INNER JOIN user u ON o.user_id = u.user_id " +
                            "WHERE u.user_mail = ?";  // Using parameterized query

            PreparedStatement preparedStatement = dm.con.prepareStatement(query);
            preparedStatement.setString(1, username); // Set the email address parameter

            ResultSet rs = preparedStatement.executeQuery();

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
					int len = getMaxLength(data.get(j)) + 4;
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
		
		// try {
		// 	ResultSet rs = dm.con.createStatement().executeQuery("SELECT o.*, p.* FROM orders o INNER JOIN products p ON o.product_id = p.product_id");
		// 	ResultSetMetaData rsmd = rs.getMetaData();
		// 	ArrayList<ArrayList<String>> data = new ArrayList<>();
		// 	int columnCount = rsmd.getColumnCount();
		// 	data.add(new ArrayList<>());
		// 	for (int i = 1; i <= columnCount; i++) {
		// 		data.add(new ArrayList<>());
		// 		data.get(i).add(rsmd.getColumnName(i));
		// 	}
		// 	while (rs.next()) {
		// 		for (int i = 1; i <= columnCount; i++) {
		// 			data.get(i).add(rs.getString(i));
		// 		}
		// 	}
		
		// 	for (int i = 0; i < data.get(1).size(); i++) {
		// 		int totcnt = 0;
		// 		for (int j = 1; j <= columnCount; j++) {
		// 			int len = getMaxLength(data.get(j)) + 4;
		// 			totcnt += len;
		// 			int cur = data.get(j).get(i).length();
		// 			int diff = len - cur;
		// 			for (int k = 0; k < diff / 2; k++) System.out.print(" ");
		// 			System.out.print(data.get(j).get(i));
		// 			for (int k = 0; k < diff / 2 + diff % 2; k++)
		// 				System.out.print(" ");
		// 			if (j != columnCount)
		// 				System.out.print("|");
		// 		}
		// 		System.out.println();
		// 		if (i == 0) {
		// 			for (int j = 0; j < totcnt; j++)
		// 				System.out.print("-");
		// 			System.out.println();
		// 		}
		// 	}
		
		// } catch (Exception e) {
		// 	System.out.println(e.toString());
		// }
	}
}
