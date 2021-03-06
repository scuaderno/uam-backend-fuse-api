package com.backend.fuseapi.utils;

public enum ResultCode implements IResultCode {

	SUCCESS(200, "SUCCESS"),
    FAILURE(400, "FAILURE"),
    UN_AUTHORIZED(401, "UN_AUTHORIZED"),
    CLIENT_UN_AUTHORIZED(401, "CLIENT_UN_AUTHORIZED"),
    NOT_FOUND(404, "404 NOT_FOUND"),
    MSG_NOT_READABLE(400, "MSG_NOT_READABLE"),
    METHOD_NOT_SUPPORTED(405, "METHOD_NOT_SUPPORTED"),
    MEDIA_TYPE_NOT_SUPPORTED(415, "MEDIA_TYPE_NOT_SUPPORTED"),
    REQ_REJECT(403, "REQ_REJECT"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"),
    PARAM_MISS(400, "PARAM_MISS"),
    PARAM_TYPE_ERROR(400, "PARAM_TYPE_ERROR"),
    PARAM_BIND_ERROR(400, "PARAM_BIND_ERROR"),
    PARAM_VALID_ERROR(400, "PARAM_VALID_ERROR");

    final int code;
    final String message;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    private ResultCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }
}
