package mypkg;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminDBA {
    private DBManipulations dm;
    public AdminDBA()
    {
        this.dm=new DBManipulations();
    }
    public int getMaxLength(ArrayList<String> arr) {
		int len = 0;
		for (String i : arr)
			len = Math.max(len, i.length());
		return len;
	}
    public boolean isAdminThere(String username,String password)
    {
         try {
            String query = "SELECT * FROM admin WHERE username='" + username + "' and password = '" + password + "';";
            ResultSet res = dm.stmt.executeQuery(query);
            return res.next();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return false;
    }
    public void viewTables(String tableName){
        System.out.println("======================================================");
        new printTable().printResult(tableName);
        System.out.println("======================================================");
    }    
}
