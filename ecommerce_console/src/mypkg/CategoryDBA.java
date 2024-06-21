package mypkg;

import java.sql.PreparedStatement;

public class CategoryDBA {

    private DBManipulations dm;
    public CategoryDBA()
    {
        this.dm=new DBManipulations();
    }

    public String AddCat(String cat)
    {
        try{
            String query = "INSERT INTO category(category_name) VALUES(\"" + cat + "\")";
            dm.stmt.execute(query);
            return "New category added";
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }
        return null;
    }

    public String deleteCategory(int cat) {
    try {
        String query = "DELETE FROM orders WHERE product_id IN (SELECT product_id FROM products WHERE category_id=?)";
        // String query = "UPDATE orders SET product_id= -1 WHERE product_id IN (SELECT product_id FROM products WHERE category_id=?)";
        PreparedStatement ordersStatement = dm.con.prepareStatement(query);
        ordersStatement.setInt(1, cat);
        ordersStatement.executeUpdate();
        ordersStatement.close();

        query = "DELETE FROM stock WHERE product_id IN (SELECT product_id FROM products WHERE category_id=?)";
        // String query = "UPDATE orders SET product_id= -1 WHERE product_id IN (SELECT product_id FROM products WHERE category_id=?)";
        PreparedStatement stockStatement = dm.con.prepareStatement(query);
        stockStatement.setInt(1, cat);
        stockStatement.executeUpdate();
        stockStatement.close();


        
        query = "DELETE FROM addtocart WHERE category_id=?";
        PreparedStatement addtocartStatement = dm.con.prepareStatement(query);
        addtocartStatement.setInt(1, cat);
        addtocartStatement.executeUpdate();
        addtocartStatement.close();

        query = "DELETE FROM products WHERE category_id=?";
        PreparedStatement productStatement = dm.con.prepareStatement(query);
        productStatement.setInt(1, cat);
        productStatement.executeUpdate();
        productStatement.close();

        query = "DELETE FROM category WHERE category_id=?";
        PreparedStatement catStatement = dm.con.prepareStatement(query);
        catStatement.setInt(1, cat);
        catStatement.executeUpdate();
        catStatement.close();

        return "Category Deleted";
    } catch (Exception e) {
        System.out.println(e.toString());
    }
    return null;
}





}
