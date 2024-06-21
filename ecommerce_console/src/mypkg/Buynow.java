package mypkg;

public class Buynow {
    Buynowcontroller bnc=new Buynowcontroller();
    public String buynow(int proid,int quant,int userid)
    {
        int quantity=bnc.getquant(proid);
        if(quantity>0)
        {
            if(quantity>=quant)
            {

                double price=bnc.getpricecontrol(proid);
                double p=price *  quant;
                bnc.decreasequant(proid,quant);
                return bnc.buynowcontrol(proid,quant,userid,p);
            }
            else
            {
                return "Sorry,We did not have these number of quantities...";
            }
        }
        return "Out of Stock";
    }
    
}
