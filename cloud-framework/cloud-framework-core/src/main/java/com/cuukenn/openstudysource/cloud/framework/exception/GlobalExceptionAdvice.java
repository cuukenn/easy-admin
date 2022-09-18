package com.cuukenn.openstudysource.cloud.framework.exception;

import cn.dev33.satoken.exception.NotLoginException;
import com.cuukenn.openstudysource.cloud.framework.dto.Result;
import com.cuukenn.openstudysource.cloud.framework.dto.ResultCode;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public final class GlobalExceptionAdvice {
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
     * sa-token鉴权失败相关错误
     *
     * @param exception exception
     * @return result
     */
    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<Void> handlerNotLoginException(NotLoginException exception) {
        log.error("auth error happened,msg:{}", exception.getMessage());
        // 判断场景值，定制化异常信息
        String message = "";
        if (exception.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token，请先登录";
        } else if (exception.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (exception.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if (exception.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if (exception.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }
        // 返回给前端
        return Result.build(new ResultCode.ResultCodeWrapper(401, message));
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     *
     * @param exception 异常
     * @return result
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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
