package mypkg;

import java.util.Scanner;

public class Stock {
    Stockcontroller sc=new Stockcontroller();
    Scanner in =new Scanner(System.in);
    public void Stockupdate()
    {
        System.out.println("Enter the product that you want to add stock");
        sc.Stockcontrol();
        int pro_id=in.nextInt();
        System.out.println("Enter the quantity of the products");
        int q=in.nextInt();
        System.out.println(pro_id);
        System.out.println(q);
        System.out.println(sc.updatestock(pro_id, q));
    }
    
}
