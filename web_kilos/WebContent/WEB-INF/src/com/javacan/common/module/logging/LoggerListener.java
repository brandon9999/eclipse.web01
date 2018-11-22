package com.javacan.common.module.logging;

/**
 * Handler에서 발생한 로그 기록 이벤트에 대한 처리를 해 주는 리스너.
 * @author 최범균
 */
public interface LoggerListener {
    public void process(Level level, String msg, String source, Throwable e);
}
