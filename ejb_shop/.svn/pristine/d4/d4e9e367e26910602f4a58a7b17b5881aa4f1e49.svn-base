package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;
import java.util.*;

public interface UsersHome extends javax.ejb.EJBLocalHome {
  public Users create(String id, String name, String passwd, int userlevel) throws CreateException;
  public Users findByPrimaryKey(String id) throws FinderException;
}