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
		  // 예외를 고의로 발생 시킴
		  //Exception e = new Exception("test exception");
		  //throw e;
		  // or
		  // throw new Exception("고의로 발생시킴");		  
	  
	   
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
