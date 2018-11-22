package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;
import java.util.*;
import java.math.*;
import java.sql.*;

public interface Item extends javax.ejb.EJBLocalObject {
  public String getId();
  public void setCategoryname(String categoryname);
  public String getCategoryname();
  public void setName(String name);
  public String getName();
  public void setPrice(int price);
  public int getPrice();
  public void setText(String text);
  public String getText();
  public void setRegdate(Timestamp regdate);
  public Timestamp getRegdate();
}