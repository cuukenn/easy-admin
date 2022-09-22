package com.cuukenn.openstudysource.cloud.framework.dto;

import com.cuukenn.openstudysource.cloud.framework.exception.BizException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author changgg
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
     * 成功
     *
     * @return ok status
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS, data);
    }

    /**
     * 失败
     *
     * @return failed status
     */
    public static Result<Void> failed() {
        return new Result<>(ResultCode.ERROR, null);
    }

    /**
     * 失败
     *
     * @return ok status
     */
    public static <T> Result<T> failed(T data) {
        return new Result<>(ResultCode.ERROR, data);
    }

    public static Result<Void> build(IResult resultCode) {
        return new Result<>(resultCode, null);
    }

    public static <T> Result<T> build(IResult resultCode, T data) {
        return new Result<>(resultCode, data);
    }

    Result(IResult resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 校验结果
     */
    public void check() {
        if (!getCode().equals(ResultCode.SUCCESS.getCode())) {
            throw new BizException(getCode(), getMessage());
        }
    }
}
