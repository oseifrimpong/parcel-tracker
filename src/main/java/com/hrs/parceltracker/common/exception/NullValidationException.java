package com.hrs.parceltracker.common.exception;

import com.hrs.parceltracker.common.api.ApiCode;

/**
 * Business Exception
 */
public class NullValidationException extends ApplicationException {
    private static final long serialVersionUID = 4732838936722311360L;

    public NullValidationException(String message) {
        super();
        setErrorCode(ApiCode.NULL_VALIDATION_EXCEPTION.getCode());
        setMessage(String.format(ApiCode.NULL_VALIDATION_EXCEPTION.getMessage(), message));
    }
}
