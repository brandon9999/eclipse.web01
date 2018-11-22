package com.javacan.kilos.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MemberWrapperTest {
    public static void main(String[] args) throws Exception {
    	Class.forName("org.gjt.mm.mysql.Driver");
    	Connection conn = null;
    	
    	MemberWrapper memberWrapper = null;
    	
    	try {
    		conn = java.sql.DriverManager.getConnection(
    	        "jdbc:mysql://localhost/kilos?useUnicode=true&characterEncoding=euc-kr",
    	        "kilos",
    	        "kilos");
    	    
    	    memberWrapper = new MemberWrapper(conn);
    	    
    	    if (args[0].equals("c")) {
    	    	MemberBean mb = new MemberBean();
    	    	mb.setId(args[1]);
    	    	mb.setPassword(args[1]);
    	    	mb.setName("�ֹ���");
    	    	mb.setEmail("madvirus@tpage.com");
    	    	mb.setModel("ų�ν� LK �����");
    	    	mb.setAddress("����� ���빮�� �Ͼ��� 2��");
    	    	mb.setPhone("011-414-XXXX");
                
                memberWrapper.insert(mb);
                
                System.out.println(args[1]+" ������");
	    	} else if (args[0].equals("r")) {
	    		MemberBean mb = memberWrapper.select(args[1]);
                if (mb == null) {
                	System.out.println(args[1]+" ���̵�� �������� ����");
            	} else {
	    	    	System.out.println(mb.getId());
	    	    	System.out.println(mb.getPassword());
	    	    	System.out.println(mb.getName());
	    	    	System.out.println(mb.getEmail());
	    	    	System.out.println(mb.getModel());
	    	    	System.out.println(mb.getAddress());
	    	    	System.out.println(mb.getPhone());
	    		}
    		} else if (args[0].equals("u")) {
    			MemberBean mb = memberWrapper.select(args[1]);
    			mb.setPhone(args[2]);
    			memberWrapper.update(mb);
    			
    			System.out.println(args[1]+" ������");
			} else if (args[0].equals("d")) {
				memberWrapper.delete(args[1]);
				
				System.out.println(args[1]+" ������");
			}
    	} finally {
    		if (memberWrapper != null) memberWrapper.close();
    		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
}
