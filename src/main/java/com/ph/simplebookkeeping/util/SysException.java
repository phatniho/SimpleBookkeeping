package com.ph.simplebookkeeping.util;

/**
 * 异常处理类
 */
public class SysException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public static void throwException(Exception e) {
        throw new SysException(e.getMessage());
    }

    public static void throwException(String e) {
        throw new SysException(e);
    }
}
