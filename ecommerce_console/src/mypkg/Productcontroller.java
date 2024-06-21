package mypkg;

import java.util.*;
public class Productcontroller {
    ProductDBA pd=new ProductDBA();
    public String addprocontrol(List<Object> pro)
    {
        String pro_name=(String)pro.get(0);
        String pro_description=(String)pro.get(1);
        String pro_price=(String)pro.get(2);
        String pro_offer=(String)pro.get(3);
        int color_id=(int)pro.get(4);
        int cat_id=(int)pro.get(5);
        return pd.AddProductsDBA(pro_name,pro_description,pro_price,pro_offer,color_id,cat_id);
    }
    public int nullstock(String pro_name)
    {
            return pd.getProductId(pro_name);
    }
    public String nullstockupdate(int proid)
    {
        return pd.nullStockUpdateDBA(proid);
    }
    public void deleteprodisplay()
    {
        pd.displayprodetailfordelete();
    }
    public String deleteproduct(int pro)
    {
        return pd.deleteProduct(pro);
    }
    public String deletecategorycontrol(int cat)
    {
        return pd.deleteProduct(cat);
    }
}
