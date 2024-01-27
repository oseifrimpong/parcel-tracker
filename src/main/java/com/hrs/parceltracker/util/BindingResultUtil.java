package com.hrs.parceltracker.util;

import org.springframework.validation.BindingResult;

public class BindingResultUtil {

    private BindingResultUtil(){ throw new IllegalStateException("Utility class"); }

    public static String getBindingErrorMessage(BindingResult bindingResult) {

        StringBuilder builder = new StringBuilder();
        bindingResult.getFieldErrors().forEach(error -> builder.append(error.getField()).append(" - " ).append(error.getDefaultMessage()).append(" "));

        return builder.toString();
    }
}
