package mypkg;
import java.util.*;
public class AdminHomePage {
    String username;
    Scanner in = new Scanner(System.in);
        AdminHomePage(String user) {
        super();
        username = user;
        System.out.println("Welcome "+username);
        AddCateogory();
    }
    AdminController ac=new AdminController();
    Category ct=new Category();
    Product p=new Product();
    public void AddCateogory()
    {
        System.out.println("Choose operation");
        System.out.println("[1]- Add Category");
        System.out.println("[2]-Add Products");
        System.out.println("[3]-Add Stock");
        System.out.println("[4]-View User orders");
        System.out.println("[5]-Delete Categoriers");
        System.out.println("[6]-Delete Products");
        int choice =in.nextInt();
        switch (choice) {
            case 1:
                ct.AddCateogory();
                break;
            case 2:
                p.AddProducts();
                break;
            case 3:
            Stock stk=new Stock();
            stk.Stockupdate();
            break;
            case 4:
            ac.vieworder("orders");
            break;
            case 5:
            ct.deletecategories();
            break;
            case 6:
            p.DeleteProduct();
            break;
            default:
            System.out.println("Invalid operation");
                break;
        }
    }
}
