package mypkg;

import java.util.*;
public class AdminPage {
    static void gotoHomePage(String mail)
    {
        AdminHomePage ahp = new AdminHomePage(mail);
    }
    Scanner in =new Scanner(System.in);
    AdminController ac= new AdminController();
    public void adminpages()
    {
        System.out.println("=========================================");
        System.out.println("Enter your mail.....");
        String mail=in.nextLine();
        System.out.println("Enter your password......");
        String pass=in.nextLine();
        if(ac.isadminthere(mail, pass))
        {
            System.out.println("logged in");
            gotoHomePage(mail);
        }
        else
        {
            System.out.println("Invalid credentials");
        }



    }    
}
