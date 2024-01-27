package com.hrs.parceltracker.common.exception;


import com.hrs.parceltracker.common.api.ApiCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -2470461654663264392L;

	private Integer errorCode; // NOSONAR
	private String message; // NOSONAR

	public ApplicationException() {
		super();
		this.errorCode = ApiCode.APPLICATION_EXCEPTION.getCode();
		this.message = ApiCode.APPLICATION_EXCEPTION.getMessage();
	}

	public ApplicationException(String message) {
		super(message);
		this.errorCode = ApiCode.APPLICATION_EXCEPTION.getCode();
		this.message = message;
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		this.errorCode = ApiCode.APPLICATION_EXCEPTION.getCode();
		this.message = message;
	}

	public ApplicationException(Throwable cause) {
		super(cause);
		this.errorCode = ApiCode.APPLICATION_EXCEPTION.getCode();
		this.message = ApiCode.APPLICATION_EXCEPTION.getMessage();
	}

}
