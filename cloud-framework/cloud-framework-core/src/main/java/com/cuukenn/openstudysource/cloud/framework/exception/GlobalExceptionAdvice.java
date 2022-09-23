package com.cuukenn.openstudysource.cloud.framework.exception;

import com.cuukenn.openstudysource.cloud.framework.dto.Result;
import com.cuukenn.openstudysource.cloud.framework.dto.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常捕获
 *
 * @author changgg
 */
@RestControllerAdvice
public final class GlobalExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    /**
     * 业务异常
     *
     * @param exception 异常
     * @return result
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> handler(BizException exception) {
        log.error("error happened,code:{},msg:{}", exception.getCode(), exception.getMessage());
        return Result.build(exception.getCodeResult());
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     *
     * @param exception 异常
     * @return result
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> handler(ConstraintViolationException exception) {
        log.error("params error happened", exception);
        return Result.build(ResultCode.PARAM_ERROR);
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常
     *
     * @param exception 异常
     * @return result
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> handler(MethodArgumentNotValidException exception) {
        log.error("params error happened", exception);
        return Result.build(ResultCode.PARAM_ERROR);
    }

    /**
     * 其他异常
     *
     * @param exception 异常
     * @return result
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> handler(Exception exception) {
        log.error("system error happened", exception);
        return Result.build(ResultCode.ERROR);
    }
}
