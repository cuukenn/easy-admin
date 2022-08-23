package com.cuukenn.openstudysource.e_admin.common.dto;

import lombok.Data;

/**
 * @author changgg
 */
@Data
public class Result<T> implements IResult {
  private Integer code;
  private String message;
  private T data;

  /**
   * 成功
   *
   * @return ok status
   */
  public static Result<Void> success() {
    return new Result<>(ResultCode.SUCCESS, null);
  }

  /**
   * 失败
   *
   * @return failed status
   */
  public static Result<Void> failed() {
    return new Result<>(ResultCode.ERROR, null);
  }

  public static Result<Void> build(IResult resultCode) {
    return new Result<>(resultCode, null);
  }

  public static <T> Result<T> build(IResult resultCode, T data) {
    return new Result<>(resultCode, data);
  }

  Result(IResult resultCode, T data) {
    this.data = data;
  }
}
