package mypkg;

import java.util.*;
public class App {
    public static void main(String args[])
    {   
        App app=new App();
        app.Login();
    }
    public void Login()
    {
        System.out.println();
        System.out.println("AMAZON CLONE....");
        System.out.println("Are you.....");
        System.out.println("[1]-User");
        System.out.println("[2]-Admin");
        System.out.print("->");
        Scanner in = new Scanner(System.in);
        UserPage up=new UserPage();
        AdminPage ap=new AdminPage();
        int choice = in.nextInt();
        if(choice==1)
        {
            up.Userpages();
        }
        else if(choice==2)
        {
            ap.adminpages();
        }
        else
        {
            System.out.println("Invalid choice");
        }
        in.close();
    }
    
}
