package com.hrs.parceltracker.common.exception;

import lombok.Data;

/**
 * @author AndrewMiao
 * @since 5/17/2022
 */
@Data
public class BadRequestParamException extends RuntimeException {

    private String paramName; // NOSONAR

    public BadRequestParamException(String message) {
        super(message);
    }

    public BadRequestParamException(String paramName, String message) {
        super(message);
        this.paramName = paramName;
    }
}
