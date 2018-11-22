package kis.ejb.guestbook;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;


public interface GuestbookHome extends EJBHome{
	public Guestbook create() throws CreateException, RemoteException;
}