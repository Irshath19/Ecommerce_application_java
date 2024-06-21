package mypkg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Addtocartdba {
    private DBManipulations dm;

	public Addtocartdba() {
		this.dm = new DBManipulations();
	}
	UserDBA ud=new UserDBA();
    public String addtocart(int proid,int userid)
    {
        try {
			String query = "INSERT INTO addtocart (product_id, user_id) VALUES (?, ?)";
			PreparedStatement preparedStatement = dm.con.prepareStatement(query);

			preparedStatement.setInt(1, proid);
			preparedStatement.setInt(2, userid);
			
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			return "Item added to cart";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
	public void viewcart(int user_id) {
		try {
			System.out.println("=======================================================");
			// String query = "SELECT * FROM addtocart WHERE user_id = '" + user_id + "'";
			String query="SELECT addtocart.cart_id,products.product_name,products.product_description,products.product_price,products.products_offer FROM addtocart JOIN products ON addtocart.product_id=products.product_id";
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
					int len = ud.getMaxLength(data.get(j)) + 4;
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
			System.out.println("=======================================================");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
    
}
