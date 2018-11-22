package com.javacan.kilos.member;

/**
 * �ߺ��� ���̵� ����� ��쿡 �߻��ϴ� ����
 * @author �ֹ���
 */
public class DuplicateMemberIdException extends Exception {
    
    private String memberId;
    
    public DuplicateMemberIdException(String memberId) {
        this.memberId = memberId;
    }
    
    public String getMemberId() {
        return memberId;
    }
}
