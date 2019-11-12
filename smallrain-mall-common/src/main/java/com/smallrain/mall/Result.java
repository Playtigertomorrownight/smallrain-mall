package com.smallrain.mall;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用返回对象 
 */
@Data
@AllArgsConstructor
public class Result<T> {
	
	private long status;
	
	private String message;
	
	private T data;

	protected Result() {
	}

	/**
	 * 成功返回结果
	 *
	 * @param data
	 *            获取的数据
	 */
	public static <T> Result<T> success(T data) {
		return new Result<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
	}

	/**
	 * 成功返回结果
	 *
	 * @param data
	 *            获取的数据
	 * @param message
	 *            提示信息
	 */
	public static <T> Result<T> success(T data, String message) {
		return new Result<T>(ResultCode.SUCCESS.getCode(), message, data);
	}

	/**
	 * 失败返回结果
	 * 
	 * @param errorCode
	 *            错误码
	 */
	public static <T> Result<T> failed(ErrorCode errorCode) {
		return new Result<T>(errorCode.getCode(), errorCode.getMessage(), null);
	}

	/**
	 * 失败返回结果
	 * 
	 * @param message
	 *            提示信息
	 */
	public static <T> Result<T> failed(String message) {
		return new Result<T>(ResultCode.FAILED.getCode(), message, null);
	}

	/**
	 * 失败返回结果
	 */
	public static <T> Result<T> failed() {
		return failed(ResultCode.FAILED);
	}

	/**
	 * 参数验证失败返回结果
	 */
	public static <T> Result<T> validateFailed() {
		return failed(ResultCode.VALIDATE_FAILED);
	}

	/**
	 * 参数验证失败返回结果
	 * 
	 * @param message
	 *            提示信息
	 */
	public static <T> Result<T> validateFailed(String message) {
		return new Result<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
	}

	/**
	 * 未登录返回结果
	 */
	public static <T> Result<T> unauthorized(T data) {
		return new Result<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
	}

	/**
	 * 未授权返回结果
	 */
	public static <T> Result<T> forbidden(T data) {
		return new Result<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
	}

}
