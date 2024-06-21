package mypkg;

import java.util.*;
public class Category {
    Scanner in = new Scanner(System.in);
    Categorycontroller cc=new Categorycontroller();
    public void AddCateogory()
    {
        System.out.println("Enter the category to add");
        String category=in.nextLine();
        String response=cc.addcat(category);
        System.out.println(response);
        return;

    }
    public void deletecategories()
    {
        System.out.println("Enter the category_id of category that you want to be delete....");
        int cat=in.nextInt();
        String resp=cc.deletecategorycontrol(cat);
        System.out.println(resp);
    }
}
