package com.hrs.parceltracker.common.api;

public enum ApiCode {

    SUCCESS(200, "Operation success"), UNAUTHORIZED(401, "Illegal access"), NOT_PERMISSION(403, "No permission"),
    NOT_FOUND(404, "Your requested resource doesn't exist"), REDIRECT(302, "Redirect to another url"),
    FAIL(500, "Operation failed"),
    BAD_REQUEST(400, "Your request is malformed"),

    LOGIN_EXCEPTION(4000, "Login failure"), SYSTEM_EXCEPTION(5000, "System exception"),
    PARAMETER_EXCEPTION(5001, "Request params validation failure"),
    PARAMETER_PARSE_EXCEPTION(5002, "Request params parse failure"),
    HTTP_MEDIA_TYPE_EXCEPTION(5003, "HTTP media type exception"), APPLICATION_EXCEPTION(5100, "Application exception"),
    BUSINESS_EXCEPTION(5101, "Business exception"), DAO_EXCEPTION(5102, "Database access exception"),
    VERIFICATION_CODE_EXCEPTION(5103, "Verification code exception"),
    AUTHENTICATION_EXCEPTION(5104, "Authentication exception"),
    UNAUTHENTICATED_EXCEPTION(5105, "Unauthenticated exception"),
    UNAUTHORIZED_EXCEPTION(5106, "Unauthorized exception"), JWT_DECODE_EXCEPTION(5107, "JWT Token parse failure"),
    DATA_AUTH_EXCEPTION(5108, "Data auth validation failure"), APP_CONFIG_EXCEPTION(5109, "Application config failure"),
    PARAM_VALIDATION_EXCEPTION(5110, "Parameter validation failure"),
    NULL_VALIDATION_EXCEPTION(5111, "%s can't be null"), STRING_EMPTY_VALIDATION_EXCEPTION(5112, "%s can't be empty"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(5113, "METHOD NOT SUPPORTED"),
    HEALTH_CHECK_FAILURE_EXCEPTION(5114, "%s is not available"), OVER_LIMIT_EXCEPTION(5115, "%s can't over %s"),
    SUPPLIER_NOT_BIND_EXCEPTION(5116, "Supplier not bind"),
    // for BusinessException
    EMAIL_NOT_EXIST_EXCEPTION(5117, "Email not exists"), SUPPLIER_NOT_EXIST_EXCEPTION(5118, "Supplier not exists"),
    USER_TYPE_EXCEPTION(5119, "User type is not correct"), RECORD_NOT_EXISTS_EXCEPTION(5120, "Record not exists"),
    RECORD_EXISTS_EXCEPTION(5121, "Record already exists"), DUPLICATE_BINDING_EXCEPTION(5122, "User duplicate binding"),
    EMAIL_DUPLICATED_EXCEPTION(5123, "Email duplicated"), EMAIL_FORMAT_INVALID(5124, "Email's format is invalid"),
    UPLOAD_FILE_INVALID_TYPE(5200, "please upload the file in %s format"), UPLOAD_IN_PROGRESS_EXCEPTION(5210, "Uploading in progress, please try later."),
    ;

    private final int code;
    private final String message;

    ApiCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] ecs = ApiCode.values();
        for (ApiCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
