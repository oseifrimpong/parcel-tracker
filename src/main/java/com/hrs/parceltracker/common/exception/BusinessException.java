package com.hrs.parceltracker.common.exception;

import com.hrs.parceltracker.common.api.ApiCode;

/**
 * Business Exception
 */
public class BusinessException extends ApplicationException {
    private static final long serialVersionUID = -2303357122330162359L;

    public BusinessException() {
        super();
        setErrorCode(ApiCode.BUSINESS_EXCEPTION.getCode());
        setMessage(ApiCode.BUSINESS_EXCEPTION.getMessage());
    }

    public BusinessException(String message) {
        super(message);
        setErrorCode(ApiCode.BUSINESS_EXCEPTION.getCode());
    }

    public BusinessException(ApiCode apiCode) {
        super(apiCode.getMessage());
        setErrorCode(apiCode.getCode());
    }

    public BusinessException(ApiCode apiCode, String message) {
        super(message);
        setErrorCode(apiCode.getCode());
    }

    public BusinessException(int errorCode, String message) {
        super(message);
        setErrorCode(errorCode);
    }
}
