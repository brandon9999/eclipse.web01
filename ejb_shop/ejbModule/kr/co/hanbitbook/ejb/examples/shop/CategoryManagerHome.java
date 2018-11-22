package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;

public interface CategoryManagerHome extends javax.ejb.EJBHome {
  public CategoryManager create() throws CreateException, RemoteException;
}