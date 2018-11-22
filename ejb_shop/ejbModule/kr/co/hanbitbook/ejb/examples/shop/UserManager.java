package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;

public interface UserManager extends javax.ejb.EJBObject {
  public void addUser(UserDataBean user) throws RemoteException;
  public void delUser(String id) throws RemoteException;
  public java.util.Collection getBuyItems(String userid) throws RemoteException;
  public void addBuyItems(java.lang.String userid, Collection items) throws RemoteException;
  public UserDataBean getUser(String userid) throws RemoteException;
}