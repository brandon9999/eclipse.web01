package com.javacan.mvc.ana;

public interface Service {
	/** 인증 여부에 상관없이 누구나 사용가능 */
	public int GRANTED_ALL = 0x01;
	/** 인증을 거처야 하며 모든 Role이 사용 가능 */
    public int GRANTED_ALL_ROLE = 0x02;
    /** 인증을 거처야 하며 제한된 Role이 사용 가능 */
    public int GRANTED_LIMITED_ROLE = 0x04;
    
    public String getCode();
    public void setCode(String code);
    
    public String getName();
    public void setName(String name);
    
    public String getUri();
    public void setUri(String uri);
    
    public String getDescription();
    public void setDescription(String description);
    
    public int getGrantedType();
    public void setGrantedType(int type);
    
    public boolean isStopped();
    public void setStopped(boolean b);
}
