package mypkg;

import java.util.*;

public class Product {
    Scanner in = new Scanner(System.in);
    Productcontroller pc = new Productcontroller();
    AdminDBA ad= new AdminDBA();
    public void AddProducts()
    {
        System.out.println("Enter the product name");
        String pro_name=in.nextLine();
        System.out.println("Enter the product description");
        String pro_description=in.nextLine();
        System.out.println("Enter the product price");
        String pro_price=in.nextLine();
        System.out.println("Enter the product offers");
        String pro_offer=in.nextLine();
        System.out.println("Enter the color id");
        ad.viewTables("color");
        int color_id=in.nextInt();
        System.out.println("Enter the Category type as ID");
        ad.viewTables("category");
        int category_id=in.nextInt();
        List <Object> pro =new ArrayList<>();
        pro.add(pro_name);
        pro.add(pro_description);
        pro.add(pro_price);
        pro.add(pro_offer);
        pro.add(color_id);
        pro.add(category_id);
        String response=pc.addprocontrol(pro);
        System.out.println(response);
        int pro_id=pc.nullstock(pro_name);
        String stocknullupdate=pc.nullstockupdate(pro_id);
        System.out.println(stocknullupdate);
    }
    public void DeleteProduct()
    {
        System.out.println("Enter the product_id of product that you want to be delete");
        pc.deleteprodisplay();
        int pro_id=in.nextInt();
        String res=pc.deleteproduct(pro_id);
        System.out.println(res);
    }
    
}
