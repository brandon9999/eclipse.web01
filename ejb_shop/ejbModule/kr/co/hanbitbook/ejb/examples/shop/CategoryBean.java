package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;

abstract public class CategoryBean implements EntityBean {
  EntityContext entityContext;
  public void ejbRemove() throws RemoveException {
    /**@todo Complete this method*/
  }
  public abstract void setName(java.lang.String name);
  public abstract void setText(java.lang.String text);
  public abstract java.lang.String getName();
  public abstract java.lang.String getText();
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
  public java.lang.String ejbCreate(java.lang.String name, java.lang.String text) throws CreateException {
    setName(name);
    setText(text);
    return null;
  }
  public void ejbPostCreate(java.lang.String name, java.lang.String text) throws CreateException {
    /**@todo Complete this method*/
  }
}