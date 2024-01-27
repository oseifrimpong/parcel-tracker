package com.hrs.parceltracker.common.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hrs.parceltracker.util.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @author Nemo
 * @date 11/9/2021
 */

@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class ApiError {

    /**
     * response error (HTTP_STATUS Standard)
     */
    private String error;

    /**
     * response error code (Project Standard Defined By Developer)
     */
    private Integer error_code; // NOSONAR

    /**
     * response error description
     */
    private String error_description; // NOSONAR

    /**
     * response error link
     */
    private String error_link; // NOSONAR

    /**
     * response sub error code
     */
    private String sub_error_code; // NOSONAR

    /**
     * response date
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date time;


    public ApiError() {
        time = new Date();
    }

    public static ApiError result(HttpStatus httpStatus) {
        return result(httpStatus, null);
    }

    public static ApiError result(HttpStatus httpStatus, ApiCode apiCode) {
        return result(httpStatus, apiCode, null);
    }

    public static ApiError result(HttpStatus httpStatus, ApiCode apiCode, String message) {
        return result(httpStatus, apiCode, message, null);
    }

    public static ApiError result(HttpStatus httpStatus, ApiCode apiCode, String message, String link) {
        return result(httpStatus, apiCode, message, link, null);
    }

    public static ApiError result(HttpStatus httpStatus, ApiCode apiCode, String message, String link, String subErrorCode) {
        String apiMessage = httpStatus.getReasonPhrase();
        return ApiError.builder()
                .error(ValidationUtil.isStringNotEmpty(apiMessage) ? apiMessage : "")
                .error_code(null == apiCode ? httpStatus.value() : apiCode.getCode())
                .error_description(message)
                .error_link(link)
                .sub_error_code(subErrorCode)
                .time(new Date())
                .build();
    }

}
