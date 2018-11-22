package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.io.*;
import javax.rmi.*;


public class UserManagerBean implements SessionBean {
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
      Object obj = initial.lookup("dbsource");
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
  public void addUser(UserDataBean user) {
    try{
      Object obj = initial.lookup("Users");
      UsersHome home = (UsersHome)PortableRemoteObject.narrow(obj, UsersHome.class);
      Users Users = home.create(user.getId(), user.getName(), user.getPasswd(), user.getUserlevel());
    }catch(Exception ex){
      System.out.println("addUser exception :" + ex.toString());
    }
  }
  public void delUser(java.lang.String id) {
    try{
      Object obj = initial.lookup("Users");
      UsersHome home = (UsersHome)PortableRemoteObject.narrow(obj, UsersHome.class);
      home.remove(id);
    }catch(Exception ex){
      System.out.println("delUser exception :" + ex.toString());
    }
  }
  public java.util.Collection getBuyItems(java.lang.String userid) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    java.util.Collection list = null;
    try{
      con = ds.getConnection();

      ps = con.prepareStatement("select itemid, count, state, regdate from buylog where userid = ?");
      ps.setString(1, userid);
      rs = ps.executeQuery();

      list = new java.util.ArrayList();
      while(rs.next()){
        ItemDataBean temp = new ItemDataBean();
        temp.setId(rs.getString(1));
        temp.setCount(rs.getInt(2));
        temp.setText(rs.getString(3));
        temp.setRegdate(rs.getTimestamp(4));
        list.add(temp);
      }
    }catch(Exception ex){
      System.out.println("getBuyItems exception :" + ex.toString());
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

  public void addBuyItems(java.lang.String userid, java.util.Collection items) {
    java.util.Iterator iter = items.iterator();
    while(iter.hasNext()){
      ItemDataBean ibean = (ItemDataBean)iter.next();
      try{
        Object obj = initial.lookup("Buylog");
        BuylogHome home = (BuylogHome)PortableRemoteObject.narrow(obj, BuylogHome.class);
        //java.lang.String userid, java.lang.String itemid, int count, java.sql.Timestamp regdate
        home.create(userid, ibean.getId(), ibean.getCount(), ibean.getRegdate());
      }catch(Exception ex){
        System.out.println("addBuyItems exception :" + ex.toString());
      }
    }
  }
  public UserDataBean getUser(java.lang.String userid) {
    UserDataBean userd = null;
    try{
      userd = new UserDataBean();
      Object obj = initial.lookup("Users");
      UsersHome home = (UsersHome)PortableRemoteObject.narrow(obj, UsersHome.class);
      Users userr = home.findByPrimaryKey(userid);
      userd.setId(userr.getId());
      userd.setName(userr.getName());
      userd.setPasswd(userr.getPasswd());
      userd.setUserlevel(userr.getUserlevel());
    }catch(Exception ex){
      System.out.println("addBuyItems exception (getUser) :" + ex.toString());
    }
    return userd;
  } // addBuyItems
}