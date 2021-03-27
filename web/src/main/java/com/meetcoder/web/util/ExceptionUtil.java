package com.meetcoder.web.util;

import org.springframework.validation.BindException;

public class ExceptionUtil {

    public static String createValidExceptionMessage(BindException exception) {
        StringBuffer sb = new StringBuffer();
        exception.getFieldErrors().stream().forEach(e -> {
            sb.append("[");
            sb.append(e.getField());
            sb.append("]");
            sb.append(e.getRejectedValue());
            sb.append("::");
            sb.append(e.getDefaultMessage());
            sb.append("\t");
        });
        return sb.toString();
    }
}
