package com.hrs.parceltracker.common.exception;

import com.hrs.parceltracker.common.api.ApiCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * application config exception
 *
 * @author Jimmy
 * @date 2020/3/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ApplicationConfigException extends ApplicationException {

    private static final long serialVersionUID = 8952028631871769425L;

    public ApplicationConfigException() {
        super();
        setErrorCode(ApiCode.APP_CONFIG_EXCEPTION.getCode());
        setMessage(ApiCode.APP_CONFIG_EXCEPTION.getMessage());
    }

    public ApplicationConfigException(String message) {
        super(message);
        setErrorCode(ApiCode.APP_CONFIG_EXCEPTION.getCode());
    }
}
