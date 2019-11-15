package com.smallrain.mall.common.beans;

import com.smallrain.mall.common.enums.ResultCode;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用返回对象
 */
@Data
@AllArgsConstructor
public class Result {

  private long status;

  private String message;

  private Object data;

  protected Result() {
  }

  /**
   * 成功返回结果
   *
   * @param data
   *          获取的数据
   */
  public static Result success(Object data) {
    return new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
  }

  /**
   * 成功返回结果
   *
   * @param data
   *          获取的数据
   * @param message
   *          提示信息
   */
  public static Result success(Object data, String message) {
    return new Result(ResultCode.SUCCESS.getCode(), message, data);
  }

  /**
   * 失败返回结果
   * 
   * @param errorCode
   *          错误码
   */
  public static Result failed(ErrorCode errorCode) {
    return new Result(errorCode.getCode(), errorCode.getMessage(), null);
  }

  /**
   * 失败返回结果
   * 
   * @param message
   *          提示信息
   */
  public static Result failed(String message) {
    return new Result(ResultCode.FAILED.getCode(), message, null);
  }

  /**
   * 失败返回结果
   */
  public static <T> Result failed() {
    return failed(ResultCode.FAILED);
  }

  /**
   * 参数验证失败返回结果
   */
  public static Result validateFailed() {
    return failed(ResultCode.VALIDATE_FAILED);
  }

  /**
   * 参数验证失败返回结果
   * 
   * @param message
   *          提示信息
   */
  public static Result validateFailed(String message) {
    return new Result(ResultCode.VALIDATE_FAILED.getCode(), message, null);
  }

  /**
   * 未登录返回结果
   */
  public static Result unauthorized(Object data) {
    return new Result(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
  }

  /**
   * 未授权返回结果
   */
  public static Result forbidden(Object data) {
    return new Result(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
  }

}
