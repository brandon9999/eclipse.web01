package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;

public interface CategoryManager extends javax.ejb.EJBObject {
  public void createCategory(CategoryDataBean category) throws RemoteException;
  public void deleteCategory(String categoryname) throws RemoteException;
  public void addItem(String categoryname, ItemDataBean item) throws RemoteException;
  public void deleteItem(String itemid) throws RemoteException;
  public java.util.Collection getItems(String categoryname) throws RemoteException;
  public java.util.Collection getCategory() throws RemoteException;
  public ItemDataBean getItem(String itemid) throws RemoteException;
}