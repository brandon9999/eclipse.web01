package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;

abstract public class ItemBean implements EntityBean {
  EntityContext entityContext;
  public void ejbRemove() throws RemoveException {
    /**@todo Complete this method*/
  }
  public abstract void setId(java.lang.String id);
  public abstract void setCategoryname(java.lang.String categoryname);
  public abstract void setName(java.lang.String name);
  public abstract void setPrice(int price);
  public abstract void setText(java.lang.String text);
  public abstract void setRegdate(java.sql.Timestamp regdate);
  public abstract java.lang.String getId();
  public abstract java.lang.String getCategoryname();
  public abstract java.lang.String getName();
  public abstract int getPrice();
  public abstract java.lang.String getText();
  public abstract java.sql.Timestamp getRegdate();
  public void ejbLoad() {
    /**@todo Complete this method*/
  }
  public void ejbStore() {
    /**@todo Complete this method*/
  }
  public void ejbActivate() {
    /**@todo Complete this method*/
  }
  public void ejbPassivate() {
    /**@todo Complete this method*/
  }
  public void unsetEntityContext() {
    this.entityContext = null;
  }
  public void setEntityContext(EntityContext entityContext) {
    this.entityContext = entityContext;
  }
  public java.lang.String ejbCreate(String id, String categoryname, String name, int price, String text, java.sql.Timestamp regdate) throws CreateException {
    setId(id);
    setCategoryname(categoryname);
    setName(name);
    setPrice(price);
    setText(text);
    setRegdate(regdate);
    return null;
  }
  public void ejbPostCreate(String id, String categoryname, String name, int price, String text, java.sql.Timestamp regdate) throws CreateException {
    /**@todo Complete this method*/
  }
}