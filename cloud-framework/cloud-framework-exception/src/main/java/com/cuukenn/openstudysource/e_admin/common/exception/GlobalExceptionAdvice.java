package com.cuukenn.openstudysource.e_admin.common.exception;

import com.cuukenn.openstudysource.e_admin.common.dto.Result;
import com.cuukenn.openstudysource.e_admin.common.dto.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常捕获
 *
 * @author changgg
 */
@ControllerAdvice
@Slf4j
public final class GlobalExceptionAdvice {
  /**
   * 业务异常
   *
   * @param exception 异常
   * @return result
   */
  @ExceptionHandler(BizException.class)
  public Result<Void> handler(BizException exception) {
    log.error("error happened", exception);
    return Result.build(exception.getCodeResult());
  }

  /**
   * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
   *
   * @param exception 异常
   * @return result
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public Result<Void> handler(ConstraintViolationException exception) {
    log.error("error happened", exception);
    return Result.build(ResultCode.PARAM_ERROR);
  }

  /**
   * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常
   *
   * @param exception 异常
   * @return result
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Result<Void> handler(MethodArgumentNotValidException exception) {
    log.error("error happened", exception);
    return Result.build(ResultCode.PARAM_ERROR);
  }

  /**
   * 其他异常
   *
   * @param exception 异常
   * @return result
   */
  @ExceptionHandler(Exception.class)
  public Result<Void> handler(Exception exception) {
    log.error("error happened", exception);
    return Result.build(ResultCode.ERROR);
  }
}
