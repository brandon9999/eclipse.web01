package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;
import java.util.*;

public interface Category extends javax.ejb.EJBLocalObject {
  public String getName();
  public void setText(String text);
  public String getText();
}