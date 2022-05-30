package com.razielez.gitee.cli.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public final class JsonUtils {

  public static String map2Json(Map<String, Object> params) throws Exception {
    ObjectMapper objectMapper = ObjectMapperPool.get();
    return objectMapper.writeValueAsString(params);
  }


}
