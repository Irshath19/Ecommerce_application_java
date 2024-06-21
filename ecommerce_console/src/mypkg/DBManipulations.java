package mypkg;
import java.sql.*;
import java.util.*;
class DBManipulations {
    public Connection con;
    public Statement stmt;
    Scanner in = new Scanner(System.in);

    DBManipulations() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amazondb1", "root", "");
            stmt = con.createStatement();
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public Connection getConnection() {
        return con;
    }
}