package com.razielez.gitee.cli.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.Map;

public final class ApiClient {

  final String GITEE_URL = "https://gitee.com/api/";
  final int TIMEOUT_SEC = 60;
  private HttpClient httpClient;
  private ObjectMapper objectMapper;


  public ApiClient() {
    createDefaultClient();
  }

  private void createDefaultClient() {
    httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(TIMEOUT_SEC))
        .build();
    objectMapper = ObjectMapperPool.get();
  }

  public <T> Result<T> get(String path, Map<String, Object> params, Class<T> clz) {
    try {
      URI uri = new URI(join(GITEE_URL + path, params));
      HttpRequest request = HttpRequest.newBuilder()
          .GET()
          .uri(uri)
          .timeout(Duration.ofSeconds(TIMEOUT_SEC))
          .build();
      return send(request, clz);
    } catch (Exception e) {
      return Result.failed();
    }
  }

  public <T> Result<T> post(String path, Map<String, Object> params, Class<T> clz) {
    return request(HttpMethod.POST, path, params, clz);
  }

  public <T> Result<T> patch(String path, Map<String, Object> params, Class<T> clz) {
    return request(HttpMethod.PATCH, path, params, clz);
  }

  public <T> Result<T> request(HttpMethod method, String path, Map<String, Object> params, Class<T> clz) {
    try {
      String body = JsonUtils.map2Json(params);
      URI uri = new URI(GITEE_URL + path);
      HttpRequest request = HttpRequest.newBuilder()
          .method(method.name(), BodyPublishers.ofString(body))
          .uri(uri)
          .header("Content-type", "application/json")
          .build();
      return send(request, clz);
    } catch (Exception e) {
      System.out.println(e);
      return Result.failed();
    }
  }

  private <T> Result<T> send(HttpRequest request, Class<T> clz) throws IOException, InterruptedException {
    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    if (response.statusCode() != 200) {
      String body = response.body();
      System.out.println("Request: " + request);
      System.out.println("Response: " + response);
      return parseErrorResult(body);
    }
    return Result.ok(objectMapper.readValue(response.body(), clz));
  }

  private <T> Result<T> parseErrorResult(String body) throws JsonProcessingException {
    if (StringUtils.isEmpty(body)) {
      return Result.failed();
    }
    JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);
    int code = jsonNode.get("code") == null ? -1 : jsonNode.get("code").intValue();
    String message = null;
    if (jsonNode.get("message") != null) {
      message = jsonNode.get("message").asText();
    } else if (jsonNode.get("messages") != null) {
      StringBuilder sb = new StringBuilder();
      for (JsonNode messages : jsonNode.get("messages")) {
        sb.append(messages.asText()).append("\t");
      }
      message = sb.toString();
    }
    return Result.failed(code, message);
  }

  private String join(String url, Map<String, Object> queryParams) {
    String queryStr = queryParams.entrySet()
        .stream()
        .map(p -> String.format("%s=%s", p.getKey(), p.getValue()))
        .reduce((x, y) -> urlEncode(x) + "&" + urlEncode(y)).orElse("");
    return url + "?" + queryStr;
  }

  private String urlEncode(String s) {
    return URLEncoder.encode(s, Charset.defaultCharset());
  }

  public enum HttpMethod {
    GET, POST, PATCH, DELETE
  }

}
