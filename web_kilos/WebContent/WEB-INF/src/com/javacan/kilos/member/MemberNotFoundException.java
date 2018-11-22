package com.javacan.kilos.member;

/**
 * ȸ�� ������ �߰����� ���� ��� �߻��Ѵ�.
 * @author �ֹ���
 */
public class MemberNotFoundException extends Exception {
    
    private String memberId;
    
    public MemberNotFoundException(String memberId) {
        this.memberId = memberId;
    }
    
    public String getMemberId() {
        return memberId;
    }
}
