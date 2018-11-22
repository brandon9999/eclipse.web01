package kr.co.hanbitbook.ejb.examples.shop;
import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.io.*;
import javax.rmi.*;

public class BuylogBean implements EntityBean {
  EntityContext entityContext;
  java.lang.Integer seq;
  java.lang.String userid;
  java.lang.String itemid;
  int count;
  java.lang.String state;
  java.sql.Timestamp regdate;

  DataSource ds;
  Context initial;

  public java.lang.Integer ejbCreate(java.lang.String userid, java.lang.String itemid, int count, java.sql.Timestamp regdate) throws CreateException {
     Connection con = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     int iseq = 0;
     try{
       con = ds.getConnection();
       ps = con.prepareStatement("insert into buylog values ( buylog_seq.NEXTVAL, ?, ?, ?, '발송전' , ?)");
       ps.setString(1, userid);
       ps.setString(2, itemid);
       ps.setInt(3, count);
       ps.setTimestamp(4, regdate);
       ps.executeUpdate();
       ps.close();

       ps = con.prepareStatement("select buylog_seq.CURRVAL from dual");
       rs = ps.executeQuery();
       rs.next();
       iseq = rs.getInt(1);
     }catch(Exception ex){
       System.out.println("ejbCreate()메소드 호출시 오류 :" + ex.toString());
     }finally{
       if(rs != null){
         try{ rs.close(); }catch(Exception ex2){}
       }
       if(ps != null){
         try{ ps.close(); }catch(Exception ex2){}
       }
       if(con != null){
         try{ con.close(); }catch(Exception ex2){}
       }
     } // finally
     setSeq(new Integer(iseq));
     setCount(count);
     setUserid(userid);
     setItemid(itemid);
     setState("발송전");
     setRegdate(regdate);
     return new Integer(iseq);
  }
  public void ejbPostCreate(java.lang.String userid, java.lang.String itemid, int count, java.sql.Timestamp regdate) throws CreateException {
  }
  public void ejbRemove() throws RemoveException {
    Connection con = null;
    PreparedStatement ps = null;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("delete from buylog where seq = ?");
      ps.setInt(1, seq.intValue());
      ps.executeUpdate();
    }catch(Exception ex){
      System.out.println("ejbRemove()메소드 호출시 오류 :" + ex.toString());
    }finally{
      if(ps != null){
        try{ ps.close(); }catch(Exception ex2){}
      }
      if(con != null){
        try{ con.close(); }catch(Exception ex2){}
      }
    } // finally
  }
  public void setSeq(java.lang.Integer seq) {
    this.seq = seq;
  }
  public void setUserid(java.lang.String userid) {
    this.userid = userid;
  }
  public void setItemid(java.lang.String itemid) {
    this.itemid = itemid;
  }
  public void setCount(int count) {
    this.count = count;
  }
  public void setState(java.lang.String state) {
    this.state = state;
  }
  public void setRegdate(java.sql.Timestamp regdate) {
    this.regdate = regdate;
  }
  public java.lang.Integer getSeq() {
    return seq;
  }
  public java.lang.String getUserid() {
    return userid;
  }
  public java.lang.String getItemid() {
    return itemid;
  }
  public int getCount() {
    return count;
  }
  public java.lang.String getState() {
    return state;
  }
  public java.sql.Timestamp getRegdate() {
    return regdate;
  }
  public java.lang.Integer ejbFindByPrimaryKey(java.lang.Integer seq) throws FinderException {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int iseq = 0;
    try {
      con = ds.getConnection();
      ps = con.prepareStatement("select seq from buylog where seq = ?");
      ps.setInt(1, seq.intValue());
      rs = ps.executeQuery();
      rs.next();
      iseq = rs.getInt(1);
    }catch(Exception ex){
      System.out.println("ejbFindByPrimaryKey()메소드 호출시 오류 :" + ex.toString());
      return null;
    }finally{
      if(rs != null){
        try{ rs.close(); }catch(Exception ex2){}
      }
      if(ps != null){
        try{ ps.close(); }catch(Exception ex2){}
      }
      if(con != null){
        try{ con.close(); }catch(Exception ex2){}
      }
    } // finally
    return new Integer(iseq);
  }

  public void ejbLoad() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("select seq, userid, itemid, state, count, regdate from buylog where seq = ?");
      ps.setInt(1, seq.intValue());
      rs = ps.executeQuery();
      if(rs.next()){
        int seq = rs.getInt(1);
        String userid = rs.getString(2);
        String itemid = rs.getString(3);
        String state = rs.getString(4);
        int count = rs.getInt(5);
        java.sql.Timestamp regdate = rs.getTimestamp(6);

        this.seq = new Integer(seq);
        this.userid = userid;
        this.itemid = itemid;
        this.state = state;
        this.count = count;
        this.regdate = regdate;
      }
    }catch(Exception ex){
      System.out.println("ejbLoad()메소드 호출시 오류 :" + ex.toString());
    }finally{
      if(rs != null){
        try{ rs.close(); }catch(Exception ex2){}
      }
      if(ps != null){
        try{ ps.close(); }catch(Exception ex2){}
      }
      if(con != null){
        try{ con.close(); }catch(Exception ex2){}
      }
    } // finally
  }
  public void ejbStore() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("update buylog set userid = ?, itemid = ?, state = ?, count = ? where seq = ?");
      ps.setString(1, userid);
      ps.setString(2, itemid);
      ps.setString(3, state);
      ps.setInt(4, count);
      ps.setInt(5, seq.intValue());
      ps.executeUpdate();
      ps.close();
    }catch(Exception ex){
      System.out.println("ejbStore()메소드 호출시 오류 :" + ex.toString());
    }finally{
      if(rs != null){
        try{ rs.close(); }catch(Exception ex2){}
      }
      if(ps != null){
        try{ ps.close(); }catch(Exception ex2){}
      }
      if(con != null){
        try{ con.close(); }catch(Exception ex2){}
      }
    } // finally
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
  public void unsetEntityContext() {
    this.entityContext = null;
  }
  public void setEntityContext(EntityContext entityContext) {
    this.entityContext = entityContext;
    try{
      initial = new InitialContext();
      Object obj = initial.lookup("dbsource");
      ds = (DataSource)obj;
    }catch(Exception ex){
      System.out.println(ex.toString());
    }
  }
}