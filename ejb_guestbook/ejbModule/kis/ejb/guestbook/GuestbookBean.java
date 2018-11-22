package kis.ejb.guestbook;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class GuestbookBean implements SessionBean{
	private Context ctx = null;
	private DataSource ds = null;

	public void addGuestbookBean(GuestbookDataBean gb){

		Connection con = null;
		PreparedStatement stmt = null;

		String sql = "insert into guestbook values(guestbook_seq.NEXTVAL, ?, ?)";

		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, gb.getName());
			stmt.setString(2, gb.getContent());
			stmt.executeUpdate();
			con.commit();
			//con.rollback();
		}catch(Exception e){
			System.out.println("방명록 저장시 오류 :" + e.toString());
		}finally{
			if(stmt != null){
				try{
					stmt.close();
				}catch(SQLException ex){}
			}
			if(con != null){
				try{
					con.close();
				}catch(SQLException ex){}
			}
		} // finally
	}

	public ArrayList<GuestbookDataBean> getGuestbookBean(){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<GuestbookDataBean> list = new ArrayList<GuestbookDataBean>();

		String sql = "select seq, name, content from guestbook order by seq desc";

		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				GuestbookDataBean gdb = new GuestbookDataBean();
				gdb.setSeq(rs.getInt(1));
				gdb.setName(rs.getString(2));
				gdb.setContent(rs.getString(3));
				list.add(gdb);
			}
		}catch(Exception e){
			System.out.println("리스트 생성시 오류 :" + e.toString());
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException ex){}
			}
			if(stmt != null){
				try{
					stmt.close();
				}catch(SQLException ex){}
			}
			if(con != null){
				try{
					con.close();
				}catch(SQLException ex){}
			}
		} // finally
		return list;
	}

	public void deleteGuestbookBean(int seq){
		Connection con = null;
		PreparedStatement stmt = null;

		String sql = "delete from guestbook where seq = ?";

		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, seq);
			stmt.executeUpdate();
			con.commit();
			
		}catch(Exception e){
			System.out.println("삭제시 오류:" + e.toString());
		}finally{
			if(stmt != null){
				try{
					stmt.close();
				}catch(SQLException ex){}
			}
			if(con != null){
				try{
					con.close();
				}catch(SQLException ex){}
			}
		} // finally
	}

	public String cntGuestbookBean(){

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String cnt = "";
		
		String sql = "select count(*) from guestbook";

		try
		{
			con = ds.getConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
	        rs.next();
			cnt = rs.getString(1).toString();
			System.out.println("==========");
			System.out.println(cnt);
			System.out.println("==========");	
		}
		catch(Exception e)
		{
			System.out.println("방명록 총  Count조회시 오류 :" + e.toString());
		}
		finally
		{
			if(rs != null)
			{
				try{
					rs.close();
				}catch(SQLException ex){}
			}			
			if(stmt != null)
			{
				try
				{
					stmt.close();
				}
				catch(SQLException ex){}
			}
			if(con != null)
			{
				try
				{
					con.close();
				}
				catch(SQLException ex){}
			}
		} // finally
		
		return cnt;
	}
	
	public void ejbCreate(){
		try{
			ctx = new InitialContext();
			ds = (javax.sql.DataSource)ctx.lookup("dbsource");
		}catch(Exception ex){}
	}

	public void setSessionContext(SessionContext c){}

	public void ejbRemove(){
		ctx = null;
		ds = null;
	}

	public void ejbActivate(){
		try{
			ctx = new InitialContext();
			ds = (javax.sql.DataSource)ctx.lookup("dbsource");
		}catch(Exception ex){}	
	}

	public void ejbPassivate(){	
		ctx = null;
		ds = null;
	}
}