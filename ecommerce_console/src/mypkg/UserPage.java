package mypkg;

import java.util.*;
public class UserPage {
    static void gotoHomePage(String user_mail)
	{
        UserHomepage uhp=new UserHomepage(user_mail);
        uhp.ViewHomePage();
    }
        UserController uc = new UserController();
    public void Userpages()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("=============================================");
        System.out.println("[1]-Register");
        System.out.println("[2]-Login");
        System.out.print("->");
        int op=in.nextInt();
        if(op==1)
        {
            System.out.println("Enter your Full Name");
            System.out.print("->");
            String name=in.next();
            in.nextLine();
            System.out.println("Enter your Gender ['M','F','T']");
            System.out.print("->");
            char gender=in.next().charAt(0);
            System.out.println("Enter your Age");
            System.out.print("->");
            int age=in.nextInt();
            in.nextLine();
            System.out.println("Enter your Phone Number");
            System.out.print("->");
            String phonenumber=in.nextLine();
            System.out.println("Enter your Mail");
            System.out.print("->");
            String mail=in.nextLine();
            System.out.println("Enter your password");
            System.out.print("->");
            String password=in.nextLine();
            System.out.println("Enter your Address");
            System.out.print("->");
            String address=in.nextLine();
            System.out.println("Enter your City");
            System.out.print("->");
            String city=in.nextLine();
            List<Object> par = new ArrayList<>();
            par.add(name);
            par.add(gender);
            par.add(age);
            par.add(phonenumber);
            par.add(mail);
            par.add(address);
            par.add(city);
            par.add(password);
            String response=uc.addnewuser(par);
            System.out.println(response);
            this.Userpages(); 
        }
        else if(op==2)
        {
            userpages();
        }
        in.close();
    }
    public void userpages()
    {
        Scanner in =new Scanner(System.in);
            System.out.println("=========================================");
            System.out.println("Enter your mail.....");
            System.out.print("->");
            String mail=in.nextLine();
            System.out.println("Enter your password.");
            System.out.print("->");
            String pass=in.nextLine();
            if(uc.isuserthere(mail, pass))
            {
                System.out.println();
                System.out.println("logged in");
                gotoHomePage(mail);
            }
            else
            {
                System.out.println("Invalid credentials");
            }
            in.close();
        }    
    }