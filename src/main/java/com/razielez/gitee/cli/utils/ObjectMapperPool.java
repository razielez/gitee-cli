package com.razielez.gitee.cli.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ObjectMapperPool {

  public static final ObjectMapper DEFAULT = new ObjectMapper();

  static {
    DEFAULT.setSerializationInclusion(Include.NON_NULL);
  }

  public static ObjectMapper get() {
    return DEFAULT;
  }

}
