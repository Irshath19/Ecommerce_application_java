package mypkg;
import java.util.*;
public class UserHomepage {
    String username;
    UserHomepage(String user) {
        super();
        username = user;
        // username="azeem@gmail.com";
        System.out.println();
        System.out.println("Welcome "+username);
        System.out.println("===========================");
    }
    Scanner in = new Scanner(System.in);
    Addtocart atc=new Addtocart();
    AdminDBA ad=new AdminDBA();
    UserController uc=new UserController();
    Buynow bn=new Buynow();
    public void ViewHomePage()
    {
        System.out.println("[1]-purchase");
        System.out.println("[2]-View My Cart");
        System.out.println("[3]-My Orders");
        System.out.println("[4]-Exit");
        System.out.print("->");
        int cho=in.nextInt();
        if(cho==1)
        {
            System.out.println();
            System.out.println("Enter the category that you looking for.....");
            ad.viewTables("category");
            System.out.print("->");
        int category=in.nextInt();
        System.out.println("Enter the product that you want.......");
        System.out.println("==========================");
        uc.displaypro(category);
        System.out.println("==========================");
        in.nextLine();
        System.out.print("->");
        String pro=in.nextLine();
        System.out.println("=============================================================================");
        uc.displayprodetail(pro);
        System.out.println("=============================================================================");
        int proid=uc.getproid(pro,"products","product_id","product_name");
        int userid=uc.getproid(username,"user","user_id","user_mail");
        System.out.println(proid);
        System.out.println(userid);
        // int user_id=uc.getproid(username,);
        System.out.println("[1]-Buy Now");
        System.out.println("[2]-Add to Cart");
        System.out.println("[3]-Add to Wishlist");
        int choice=in.nextInt();
        switch (choice) {
            case 1:
            System.out.println("Enter the quantity that you want.....");
            int quantity=in.nextInt();
            String res=bn.buynow(proid,quantity,userid);
            System.out.println(res);
            ViewHomePage();
            break;
            case 2:
                // String response=uc.addtocart(userid,proid);
                // System.out.println(response);
                atc.addtocart(proid, userid);
                ViewHomePage();                
                break;
            default:
                break;
        }
        }
        else if(cho==2)
        {
            int u_id=uc.getproid(username, "user", "user_id", "user_mail");
            atc.viewcart(u_id);
            System.out.println("[1]-Buy Products in the cart");
            System.out.println("[2]-Remove products from the cart");
            int not=in.nextInt();
            if(not==1)
            {
                System.out.println("Enter the product name that you want to buy now");
                String probuy=in.next();
                System.out.println(probuy);
                // in.nextLine();
                System.out.println("Enter the quantity that you want.....");
                int q=10;
                int pro_id=uc.getproid(probuy,"products","product_id","product_name");
                // int usr_id=uc.getproid(username,"user","user_id","user_mail");
                System.err.println(probuy);
                System.out.println(pro_id);
                System.out.println(username);
                System.out.println(u_id);
                System.out.println(q);
                String res=bn.buynow(pro_id,q,u_id);
                System.out.println(res);
            }
            else if(not==2)
            {
                System.out.println("Enter the cart_id that you want to delete....");

            }
            ViewHomePage();
        }
        else if(cho==3)
        {
            uc.viewmyorder(username);
            ViewHomePage();
        }
        else if(cho==4)
        {
            App app=new App();
            app.Login();
        }

    }
    
}
