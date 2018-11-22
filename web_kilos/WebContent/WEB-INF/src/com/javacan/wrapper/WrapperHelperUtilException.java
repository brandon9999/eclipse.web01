package com.javacan.wrapper;

/**
 * WrapperHelperUtil 클래스가 TableMappingInfo 객체를 생성하는데
 * 문제가 발생했을 때 던지는 예외
 * @author 최범균
 */
public class WrapperHelperUtilException extends Exception {
    public WrapperHelperUtilException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
