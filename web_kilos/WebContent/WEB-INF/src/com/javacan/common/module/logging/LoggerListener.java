package com.javacan.common.module.logging;

/**
 * Handler���� �߻��� �α� ��� �̺�Ʈ�� ���� ó���� �� �ִ� ������.
 * @author �ֹ���
 */
public interface LoggerListener {
    public void process(Level level, String msg, String source, Throwable e);
}
