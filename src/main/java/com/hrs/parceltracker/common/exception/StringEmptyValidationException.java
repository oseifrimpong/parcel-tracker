package com.hrs.parceltracker.common.exception;


import com.hrs.parceltracker.common.api.ApiCode;

/**
 * Business Exception
 */
public class StringEmptyValidationException extends ApplicationException {
    private static final long serialVersionUID = 4732838936722311360L;

    public StringEmptyValidationException(String message) {
        super();
        setErrorCode(ApiCode.STRING_EMPTY_VALIDATION_EXCEPTION.getCode());
        setMessage(String.format(ApiCode.STRING_EMPTY_VALIDATION_EXCEPTION.getMessage(), message));
    }
}
