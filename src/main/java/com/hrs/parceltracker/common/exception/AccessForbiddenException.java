package com.hrs.parceltracker.common.exception;

import lombok.Data;

/**
 * @author AndrewMiao
 * @since 11/1/2022
 */
@Data
public class AccessForbiddenException extends RuntimeException {

    public AccessForbiddenException(String message) {
        super(message);
    }
}
