package kr.co.hanbitbook.ejb.examples.shop;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.io.*;
import javax.rmi.*;

public class CategoryManagerBean implements SessionBean {
  SessionContext sessionContext;
  Context initial;
  DataSource ds;
  public void ejbCreate() throws CreateException {
  }
  public void ejbRemove() {
  }
  public void ejbActivate() {
    try{
      initial = new InitialContext();
      Object obj = initial.lookup("ora9");
      ds = (DataSource)obj;
    }catch(Exception ex){
      System.out.println(ex.toString());
    }
  }
  public void ejbPassivate() {
    initial = null;
    ds = null;
  }
  public void setSessionContext(SessionContext sessionContext) {
    this.sessionContext = sessionContext;
    try{
      initial = new InitialContext();
      Object obj = initial.lookup("dbsource");
      ds = (DataSource)obj;
    }catch(Exception ex){
      System.out.println(ex.toString());
    }
  }
  public void createCategory(CategoryDataBean category) {
    try{
      Object obj = initial.lookup("Category");
      CategoryHome home = (CategoryHome)PortableRemoteObject.narrow(obj, CategoryHome.class);
      home.create(category.getName(), category.getText());
    }catch(Exception ex){
      System.out.println("createCategory exception :" + ex.toString());
    }
  }

  public void deleteCategory(java.lang.String categoryname) {
    try{
      Object obj = initial.lookup("Category");
      CategoryHome home = (CategoryHome)PortableRemoteObject.narrow(obj, CategoryHome.class);
      home.remove(categoryname);
    }catch(Exception ex){
      System.out.println("deleteCategory exception :" + ex.toString());
    }
  }

  public void addItem(java.lang.String categoryname, ItemDataBean item) {
    try{
      Object obj = initial.lookup("Item");
      ItemHome home = (ItemHome)PortableRemoteObject.narrow(obj, ItemHome.class);
      home.create(item.getId(), categoryname, item.getName(), item.getPrice(), item.getText(), item.getRegdate());
    }catch(Exception ex){
      System.out.println("addItem exception :" + ex.toString());
    }
  }

  public void deleteItem(java.lang.String itemid) {
    try{
      Object obj = initial.lookup("Item");
      ItemHome home = (ItemHome)PortableRemoteObject.narrow(obj, ItemHome.class);
      home.remove(itemid);
    }catch(Exception ex){
      System.out.println("deleteItem exception :" + ex.toString());
    }
  }

  public java.util.Collection getItems(java.lang.String categoryname) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    java.util.Collection list = null;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("select id, name, price, text, regdate from item where categoryname = ?");
      ps.setString(1, categoryname);
      rs = ps.executeQuery();

      list = new java.util.ArrayList();
      while(rs.next()){
        ItemDataBean temp = new ItemDataBean();
        temp.setId(rs.getString(1));
        temp.setName(rs.getString(2));
        temp.setPrice(rs.getInt(3));
        temp.setText(rs.getString(4));
        temp.setRegdate(rs.getTimestamp(5));
        temp.setCategoryname(categoryname);
        list.add(temp);
      }
    }catch(Exception ex){
      System.out.println("getItems exception :" + ex.toString());
    }finally{
      try{
        if(rs != null) rs.close();
      }catch(Exception ex){}
      try{
        if(ps != null) ps.close();
      }catch(Exception ex){}
      try{
        if(con != null) con.close();
      }catch(Exception ex){}
    }
    return list;
  }
  public java.util.Collection getCategory() {
     Connection con = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     java.util.Collection list = null;
     try{
       con = ds.getConnection();
       ps = con.prepareStatement("select name, text from category");
       rs = ps.executeQuery();

       list = new java.util.ArrayList();
       while(rs.next()){
         CategoryDataBean cbean = new CategoryDataBean();
         cbean.setName(rs.getString(1));
         cbean.setText(rs.getString(2));
         list.add(cbean);
       }
     }catch(Exception ex){
       System.out.println("getItems exception :" + ex.toString());
     }finally{
       try{
         if(rs != null) rs.close();
       }catch(Exception ex){}
       try{
         if(ps != null) ps.close();
       }catch(Exception ex){}
       try{
         if(con != null) con.close();
       }catch(Exception ex){}
     }
     return list;
  }
  public ItemDataBean getItem(java.lang.String itemid) {
    ItemDataBean ibean = null;
    try{
      ibean = new ItemDataBean();
      Object obj = initial.lookup("Item");
      ItemHome home = (ItemHome)PortableRemoteObject.narrow(obj, ItemHome.class);
      Item item = home.findByPrimaryKey(itemid);
      ibean.setId(item.getId());
      ibean.setName(item.getName());
      ibean.setCategoryname(item.getCategoryname());
      ibean.setPrice(item.getPrice());
      ibean.setRegdate(item.getRegdate());
      ibean.setText(item.getText());
    }catch(Exception ex){
      System.out.println("addItem exception :" + ex.toString());
    }
    return ibean;
  }
}