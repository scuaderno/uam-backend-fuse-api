package com.backend.fuseapi;

import java.io.Serializable;
import java.util.Optional;

import org.apache.camel.Exchange;
import org.springframework.util.ObjectUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        description = "Response Body"
)
public class ApplicationResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(
            value = "HTTP Status Cpde",
            required = true
    )
    private int code;

    @ApiModelProperty(
            value = "Request Success?",
            required = true
    )
    private boolean success;

    @ApiModelProperty("Respond Content")
    private T data;

    @ApiModelProperty(
            value = "Respond Message",
            required = true
    )
    private String msg;

    private ApplicationResponse(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMessage());
    }

    private ApplicationResponse(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private ApplicationResponse(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private ApplicationResponse(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private ApplicationResponse(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ResultCode.SUCCESS.code == code;
    }

    public static boolean isSuccess(ApplicationResponse<?> result) {
        return (Boolean)Optional.ofNullable(result).map((x) -> {
            return ObjectUtils.nullSafeEquals(ResultCode.SUCCESS.code, x.code);
        }).orElse(Boolean.FALSE);
    }

    public static boolean isNotSuccess(ApplicationResponse<?> result) {
        return !isSuccess(result);
    }

    public static <T> ApplicationResponse<T> data(T data) {
        return data(data, "SUCCESS");
    }

    public static <T> ApplicationResponse<T> data(T data, String msg) {
        return data(200, data, msg);
    }

    public static <T> ApplicationResponse<T> data(int code, T data, String msg) {
        return new ApplicationResponse(code, data, data == null ? "NULL DATA" : msg);
    }

    public static <T> ApplicationResponse<T> data(Exchange result, T data, String msg) {
        int code = Integer.parseInt(result.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE).toString());
        return new ApplicationResponse(code, data, data == null ? "NULL DATA" : msg);
    }

    public static <T> ApplicationResponse<T> success(String msg) {
        return new ApplicationResponse(ResultCode.SUCCESS, msg);
    }

    public static <T> ApplicationResponse<T> success(IResultCode resultCode) {
        return new ApplicationResponse(resultCode);
    }

    public static <T> ApplicationResponse<T> success(IResultCode resultCode, String msg) {
        return new ApplicationResponse(resultCode, msg);
    }

    public static <T> ApplicationResponse<T> fail(String msg) {
        return new ApplicationResponse(ResultCode.FAILURE, msg);
    }

    public static <T> ApplicationResponse<T> fail(int code, String msg) {
        return new ApplicationResponse(code, (Object)null, msg);
    }

    public static <T> ApplicationResponse<T> fail(IResultCode resultCode) {
        return new ApplicationResponse(resultCode);
    }

    public static <T> ApplicationResponse<T> fail(IResultCode resultCode, String msg) {
        return new ApplicationResponse(resultCode, msg);
    }

    public static <T> ApplicationResponse<T> status(boolean flag) {
        return flag ? success("Success") : fail("Fail");
    }

    public int getCode() {
        return this.code;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public String toString() {
        return "R(code=" + this.getCode() + ", success=" + this.isSuccess() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ")";
    }

    public ApplicationResponse() {
    }
}
