package com.cuukenn.openstudysource.cloud.framework.exception;

import com.cuukenn.openstudysource.cloud.framework.dto.IResult;
import com.cuukenn.openstudysource.cloud.framework.dto.ResultCode;

/**
 * 业务异常
 *
 * @author changgg
 */
public class BizException extends BaseException implements IResult {
    private static final long serialVersionUID = 6382548445433108669L;
    private final IResult codeResult;

    public BizException(IResult code) {
        super(code.getMessage());
        this.codeResult = code;
    }

    public BizException(Integer code, String message) {
        super(message);
        this.codeResult = ResultCode.buildResultCode(code, message);
    }

    @Override
    public Integer getCode() {
        return codeResult.getCode();
    }

    public IResult getCodeResult() {
        return codeResult;
    }
}
