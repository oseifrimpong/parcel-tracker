package com.hrs.parceltracker.common.exception;


import com.hrs.parceltracker.common.api.ApiCode;

public class HealthCheckFailureException extends ApplicationException {
	private static final long serialVersionUID = -6912618737345878854L;

	public HealthCheckFailureException() {
		super();
		setErrorCode(ApiCode.HEALTH_CHECK_FAILURE_EXCEPTION.getCode());
		setMessage(ApiCode.HEALTH_CHECK_FAILURE_EXCEPTION.getMessage());
	}

	public HealthCheckFailureException(String message) {
		super(message);
		setErrorCode(ApiCode.HEALTH_CHECK_FAILURE_EXCEPTION.getCode());
		setMessage(String.format(ApiCode.HEALTH_CHECK_FAILURE_EXCEPTION.getMessage(), message));
	}

}
