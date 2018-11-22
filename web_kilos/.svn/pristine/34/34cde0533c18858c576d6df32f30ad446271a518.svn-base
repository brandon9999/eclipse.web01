package com.javacan.kilos.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MemberManagerTest {
    public static void main(String[] args) throws Exception {
        MemberManager memberMgr = MemberManager.getInstance();
        if (args[0].equals("c")) {
            MemberBean mb = new MemberBean();
            mb.setId(args[1]);
            mb.setPassword(args[1]);
            mb.setName("최범균");
            mb.setEmail("madvirus@tpage.com");
            mb.setModel("킬로스 LK 고급형");
            mb.setAddress("서울시 서대문구 북아현 2동");
            mb.setPhone("011-414-XXXX");
            
            try {
                memberMgr.register(mb);
                System.out.println(args[1]+" 회원 가입 완료");
            } catch(DuplicateMemberIdException ex) {
                System.out.println("중복된 아이디가 이미 존재합니다:"+ex.getMemberId());
        	}
        } else if (args[0].equals("d")) {
        	try {
                memberMgr.secede(args[1]);
                System.out.println(args[1]+" 회원 탈퇴함");
            } catch(MemberNotFoundException ex) {
            	System.out.println("존재하지 않는 회원ID 입니다:"+ex.getMemberId());
        	}
        }
    }
}
