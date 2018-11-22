package com.javacan.kilos.member;

import java.sql.Connection;
import java.sql.SQLException;
import com.javacan.kilos.db.DBUtil;
import com.javacan.wrapper.WrapperHelperException;

/**
 * 회원 가입 및 탈퇴를 처리해주는 매니저 클래스
 * @author 최범균
 */
public class MemberManager {
    private static MemberManager instance = new MemberManager();
    
    public static MemberManager getInstance() {
        return instance;
    }
    
    private MemberManager() {
    }
    
    public synchronized void register(MemberBean member)
    throws MemberManagerException, DuplicateMemberIdException {
        Connection conn = null;
        MemberWrapper memberWrap = null;
        
        /*-----------------------------------------------------------*/
        System.out.println("[Debug] register gogo~");
        /*-----------------------------------------------------------*/
        
        
        try {
            conn = getConnection();
            
            /*-----------------------------------------------------------*/
            System.out.println("[Debug] getConnection Ok~ ");
            /*-----------------------------------------------------------*/
            
            memberWrap = new MemberWrapper(conn);
            if (memberWrap.exists(member.getId())) {
                throw new DuplicateMemberIdException(member.getId());
            }
            
            /*-----------------------------------------------------------*/
            System.out.println("[Debug] member info insert ready? ");
            /*-----------------------------------------------------------*/
            
            memberWrap.insert(member);
            
            
        } catch(SQLException ex) {
            throw new MemberManagerException(ex.getMessage(), ex);
        } catch(WrapperHelperException ex) {
            throw new MemberManagerException(ex.getMessage(), ex);
        } finally {
            if (conn != null)
                try { releaseConnection(conn); } catch(SQLException ex) {}
        }
    }
    
    public synchronized void secede(String memberId) 
    throws MemberNotFoundException, MemberManagerException {
        Connection conn = null;
        MemberWrapper memberWrap = null;
        try {
            conn = getConnection();
            memberWrap = new MemberWrapper(conn);
            if (memberWrap.delete(memberId) == 0) {
                throw new MemberNotFoundException(memberId);
            }
        } catch(SQLException ex) {
            throw new MemberManagerException(ex.getMessage(), ex);
        } catch(WrapperHelperException ex) {
            throw new MemberManagerException(ex.getMessage(), ex);
        } finally {
            if (conn != null)
                try { releaseConnection(conn); } catch(SQLException ex) {}
        }
    }
    
    public MemberBean getMemberBean(String memberId)
    throws MemberNotFoundException, MemberManagerException {
        Connection conn = null;
        MemberWrapper memberWrap = null;
        try {
            conn = getConnection();
            memberWrap = new MemberWrapper(conn);
            MemberBean mb = memberWrap.select(memberId);
            if (mb == null) {
                throw new MemberNotFoundException(memberId);
            }
            return mb;
        } catch(SQLException ex) {
            throw new MemberManagerException(ex.getMessage(), ex);
        } catch(WrapperHelperException ex) {
            throw new MemberManagerException(ex.getMessage(), ex);
        } finally {
            if (conn != null)
                try { releaseConnection(conn); } catch(SQLException ex) {}
        }
	}
	
	public void updateInfo(MemberBean mb)
	throws MemberNotFoundException, MemberManagerException {
        Connection conn = null;
        MemberWrapper memberWrap = null;
        try {
            conn = getConnection();
            memberWrap = new MemberWrapper(conn);
            if (memberWrap.update(mb) == 0) {
                throw new MemberNotFoundException(mb.getId());
            }
        } catch(SQLException ex) {
            throw new MemberManagerException(ex.getMessage(), ex);
        } catch(WrapperHelperException ex) {
            throw new MemberManagerException(ex.getMessage(), ex);
        } finally {
            if (conn != null)
                try { releaseConnection(conn); } catch(SQLException ex) {}
        }
	}
	
    private Connection getConnection() throws SQLException {
        return DBUtil.getConnection("kilos");
    }
    private void releaseConnection(Connection conn) throws SQLException {
        DBUtil.releaseConnection(conn);
    }
}
