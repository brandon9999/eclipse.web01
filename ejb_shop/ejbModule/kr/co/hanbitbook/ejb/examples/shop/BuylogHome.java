package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;
import java.util.*;
import java.math.*;

public interface BuylogHome extends javax.ejb.EJBLocalHome {
  public Buylog create(java.lang.String userid, java.lang.String itemid, int count, java.sql.Timestamp regdate) throws CreateException;
  public Buylog findByPrimaryKey(java.lang.Integer seq) throws FinderException;
}