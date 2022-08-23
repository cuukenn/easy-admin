package com.cuukenn.openstudysource.e_admin.common.exception;

/**
 * @author changgg
 */
public class BaseException extends RuntimeException {
  private static final long serialVersionUID = -7189755852658723803L;

  protected BaseException() {
  }

  public BaseException(String message) {
    super(message);
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
  }
}
