package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;
import java.util.*;

public interface CategoryHome extends javax.ejb.EJBLocalHome {
  public Category create(String name, String text) throws CreateException;
  public Category findByPrimaryKey(String name) throws FinderException;
}