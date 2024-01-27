package com.hrs.parceltracker.common.exception;


import com.hrs.parceltracker.common.api.ApiCode;

/**
 * Dao Exception
 *
 * @author Jimmy
 * @date 2018-11-08
 */
public class DaoException extends ApplicationException {
    private static final long serialVersionUID = -6912618737345878854L;

    public DaoException() {
        super();
        setErrorCode(ApiCode.DAO_EXCEPTION.getCode());
        setMessage(ApiCode.DAO_EXCEPTION.getMessage());
    }

    public DaoException(String message) {
        super(message);
        setErrorCode(ApiCode.DAO_EXCEPTION.getCode());
    }

}
