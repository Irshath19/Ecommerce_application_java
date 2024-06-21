package mypkg;

public class Stockcontroller {
    StockDBA sb=new StockDBA();
    public void Stockcontrol()
    {
        sb.prodisplay();
    }
    public String updatestock(int proid,int quantity)
    {
            return sb.updateStockDBA(proid,quantity);
    }
    
}
