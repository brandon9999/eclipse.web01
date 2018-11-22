package com.javacan.common.module.logging;

/**
 * Logger가 전송한 로그 메시지를 기록해주는 핸들러가 구현해주어야 하는 인터페이스
 * @author 최범균
 */
public interface Handler {
    public void logging(Level level, String msg, String source, Throwable e);
}
