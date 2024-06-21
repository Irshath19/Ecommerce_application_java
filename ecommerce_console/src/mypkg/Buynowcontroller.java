package mypkg;

import java.time.LocalDate;

public class Buynowcontroller {
    BuyNowdba bnd=new BuyNowdba();
    public String buynowcontrol(int pro,int quant,int user,double price)
    {
        LocalDate today = LocalDate.now();
        LocalDate two = today.plusDays(2);
        String order_status="On process";
        return bnd.buynowdba(pro, quant,user,two,price,order_status);
    }
    public double getpricecontrol(int proid)
    {
        return bnd.Getprice(proid);
    }
    public void updatestatus()
    {
        bnd.updatestatusdba();
    }
    public int getquant(int proid)
    {
        return bnd.getQuantityFromStock(proid);
    }
    public void decreasequant(int proid,int quant)
    {
        bnd.decreaseStockQuantity(proid, quant);
    }
}
