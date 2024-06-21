package mypkg;

public class Categorycontroller {
    CategoryDBA cd=new CategoryDBA();
    public String addcat(String cat)
    {
        return cd.AddCat(cat);
    }
    public String deletecategorycontrol(int cat)
    {
        return cd.deleteCategory(cat);
    }
}
