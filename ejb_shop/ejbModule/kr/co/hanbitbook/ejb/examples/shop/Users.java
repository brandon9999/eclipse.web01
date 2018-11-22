package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;
import java.util.*;
import java.math.*;

public interface Users extends javax.ejb.EJBLocalObject {
  public String getId();
  public void setName(String name);
  public String getName();
  public void setPasswd(String passwd);
  public String getPasswd();
  public void setUserlevel(int userlevel);
  public int getUserlevel();
}