package com.javacan.common.module.logging.impl;

import com.javacan.common.module.logging.Handler;
import com.javacan.common.module.logging.Level;

/**
 * �ֿܼ� �α� ������ ����ϴ� Handler
 * @author �ֹ���
 */
public class ConsoleHandler implements Handler {
    public void logging(Level level, String msg, String source, Throwable e) {
        System.out.print("[");
        System.out.print(level.getName());
        System.out.print("]");
        if (source != null) {
            System.out.print("[source=");
            System.out.print(source);
            System.out.print("]");
        }
        System.out.println(msg);
        if (e != null) {
            e.printStackTrace(System.out);
        }
    }
}
