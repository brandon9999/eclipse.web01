package kr.co.hanbitbook.ejb.examples.shop;

import javax.ejb.*;

abstract public class UsersBean implements EntityBean {
  EntityContext entityContext;
  public void ejbRemove() throws RemoveException {
    /**@todo Complete this method*/
  }
  public abstract void setId(java.lang.String id);
  public abstract void setName(java.lang.String name);
  public abstract void setPasswd(java.lang.String passwd);
  public abstract void setUserlevel(int userlevel);
  public abstract java.lang.String getId();
  public abstract java.lang.String getName();
  public abstract java.lang.String getPasswd();
  public abstract int getUserlevel();
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
  public java.lang.String ejbCreate(String id, String name, String passwd, int userlevel) throws CreateException {
    setId(id);
    setName(name);
    setPasswd(passwd);
    setUserlevel(userlevel);
    return null;
  }
  public void ejbPostCreate(String id, String name, String passwd, int userlevel) throws CreateException {
    /**@todo Complete this method*/
  }
}