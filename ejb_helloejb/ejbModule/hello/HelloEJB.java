package hello;

import java.lang.*;
import java.rmi.RemoteException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class HelloEJB implements SessionBean
{
   public HelloEJB()
   {
   }

   public String sayHello() throws RemoteException
   {
		  // ���ܸ� ���Ƿ� �߻� ��Ŵ
		  //Exception e = new Exception("test exception");
		  //throw e;
		  // or
		  // throw new Exception("���Ƿ� �߻���Ŵ");		  
	  
	   
      return "Hello World!!!!!(===This page is EJB Client+++++)";
   }

   public String sayHello2() throws RemoteException
   {
      return "Hello World nnnnnnnnn";
   }
   
   public void ejbCreate()
   {
   }

   public void ejbRemove() throws RemoteException
   {
   }

   public void setSessionContext(SessionContext sc)
   {
   }

   public void ejbActivate()
   {
   }

   public void ejbPassivate()
   {
   }
} /* end class HelloEJB */
