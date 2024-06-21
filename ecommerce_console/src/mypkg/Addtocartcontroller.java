package mypkg;

public class Addtocartcontroller {
    Addtocartdba acd=new Addtocartdba();
    public String addtocartcontrol(int proid,int userid)
    {
        return acd.addtocart(proid, userid);
    }
    public void viewcartcontrol(int u_id)
    {
        acd.viewcart(u_id);
    }
}
