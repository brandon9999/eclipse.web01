package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;
import java.util.*;
import java.sql.*;

public interface ItemHome extends javax.ejb.EJBLocalHome {
  public Item create(String id, String categoryname, String name, int price, String text, Timestamp regdate) throws CreateException;
  public Item findByPrimaryKey(String id) throws FinderException;
}

