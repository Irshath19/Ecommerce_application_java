package mypkg;

public class Addtocart {
    Addtocartcontroller act=new Addtocartcontroller();
    public void addtocart(int proid,int user_id)
    {
        String response=act.addtocartcontrol(proid, user_id);
        System.out.println(response);
    }
    public void viewcart(int userid)
    {
        act.viewcartcontrol(userid);
    }
}
