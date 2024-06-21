package mypkg;

public class AdminController {
    AdminDBA ad= new AdminDBA();
    public boolean isadminthere(String username,String password)
    {
        return ad.isAdminThere(username, password);
    }
    public void vieworder(String tablename)
    {
        ad.viewTables(tablename);
    }
}
