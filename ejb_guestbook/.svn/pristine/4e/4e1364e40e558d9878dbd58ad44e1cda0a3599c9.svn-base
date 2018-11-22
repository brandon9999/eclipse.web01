package kis.ejb.guestbook;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import java.util.*;


public interface Guestbook extends EJBObject{
	public void addGuestbookBean(GuestbookDataBean gb) throws RemoteException;
	public ArrayList getGuestbookBean() throws RemoteException;
	public void deleteGuestbookBean(int seq) throws RemoteException;
	String cntGuestbookBean() throws RemoteException;	
}