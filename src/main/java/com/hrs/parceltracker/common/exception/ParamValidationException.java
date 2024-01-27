package com.hrs.parceltracker.common.exception;


import com.hrs.parceltracker.common.api.ApiCode;


public class ParamValidationException extends ApplicationException {
    private static final long serialVersionUID = -6245530024176826581L;

    public ParamValidationException() {
        super();
        setErrorCode(ApiCode.PARAM_VALIDATION_EXCEPTION.getCode());
        setMessage(ApiCode.PARAM_VALIDATION_EXCEPTION.getMessage());
    }

    public ParamValidationException(String message) {
        super(message);
        setErrorCode(ApiCode.PARAM_VALIDATION_EXCEPTION.getCode());
    }
}
