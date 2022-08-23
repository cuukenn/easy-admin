package com.cuukenn.openstudysource.e_admin.common.dto;

/**
 * @author changgg
 */
public interface IResult {
  /**
   * 状态码
   *
   * @return code
   */
  Integer getCode();

  /**
   * 消息
   *
   * @return message
   */
  String getMessage();
}
