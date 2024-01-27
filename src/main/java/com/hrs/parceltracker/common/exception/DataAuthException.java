package com.hrs.parceltracker.common.exception;


import com.hrs.parceltracker.common.api.ApiCode;

public class DataAuthException extends ApplicationException {
    private static final long serialVersionUID = -3565846783396185177L;

    public DataAuthException() {
        super();
        setErrorCode(ApiCode.DATA_AUTH_EXCEPTION.getCode());
        setMessage(ApiCode.DATA_AUTH_EXCEPTION.getMessage());
    }
}
