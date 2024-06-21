package mypkg;

import java.util.*;
public class UserController {
    UserDBA ud=new UserDBA();
    public String addnewuser(List <Object> par)
    {
        String name=(String)par.get(0);
        char gender=(char)par.get(1);
        int age=(int) par.get(2);
        String phone=(String) par.get(3);
        String mail=(String) par.get(4);
        String address=(String) par.get(5);
        String city=(String) par.get(6);
        String password=(String) par.get(7);
        if(!ud.checkuserduplicate(mail))
        return ud.addnewuserdba(name,gender,age,phone,mail,address,city,password);
        
        return "User Already Exists";
    }
    public boolean isuserthere(String username,String password)
    {
        return ud.isusertheredba(username, password);
    }
    public void displaypro(int category)
    {
        ud.displayprolist(category);
    }
    public void displayprodetail(String productname)
    {
        ud.displayprodetaildba(productname);
    }
    public int getproid(String proname,String tablename,String columnname,String wherecolname )
    {
        return ud.getproductid(proname,tablename,columnname,wherecolname);
    }
       public void viewmyorder(String username)
   {
        Buynowcontroller bc=new Buynowcontroller();
        bc.updatestatus();
        ud.viewmyorderdba(username);
    }    
}