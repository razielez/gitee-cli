package com.razielez.gitee.cli.utils;

public record Result<T>(
    int code,
    String error,
    T data
) {

  private static final int OK_CODE = 0;
  private static final Result DEFAULT_FAILED = new Result<>(-1, "请求失败", null);

  public static <T> Result<T> ok(T t) {
    return new Result<>(
        0,
        null,
        t
    );
  }

  public static <T> Result<T> failed(int code, String error) {
    return new Result<>(
        code,
        error,
        null
    );
  }

  public static <T> Result<T> failed() {
    return DEFAULT_FAILED;
  }

  public boolean isOk() {
    return code == OK_CODE;
  }

}
