package com.cuukenn.openstudysource.cloud.common.exception;

import com.cuukenn.openstudysource.cloud.common.dto.IResult;
import com.cuukenn.openstudysource.cloud.common.dto.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 业务异常
 *
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
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
}
