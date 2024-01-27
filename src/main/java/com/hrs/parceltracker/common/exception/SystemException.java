package com.hrs.parceltracker.common.exception;


import com.hrs.parceltracker.common.api.ApiCode;

public class SystemException extends ApplicationException {
    private static final long serialVersionUID = 8038977558715022067L;

    public SystemException(String message) {
        super(message);
        setErrorCode(ApiCode.SYSTEM_EXCEPTION.getCode());
    }

    public SystemException() {
        super();
        setErrorCode(ApiCode.SYSTEM_EXCEPTION.getCode());
        setMessage(ApiCode.SYSTEM_EXCEPTION.getMessage());
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
        setErrorCode(ApiCode.SYSTEM_EXCEPTION.getCode());
    }

    public SystemException(Throwable cause) {
        super(cause);
        setErrorCode(ApiCode.SYSTEM_EXCEPTION.getCode());
        setMessage(ApiCode.SYSTEM_EXCEPTION.getMessage());
    }

}
