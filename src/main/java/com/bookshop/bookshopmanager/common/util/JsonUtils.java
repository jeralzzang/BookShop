package com.bookshop.bookshopmanager.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class JsonUtils {

  //api호출하여 json가져오는 메소드
  public String getJsonToApi(String urlPath){
    try {
      URL url = new URL(urlPath);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      int responseCode = connection.getResponseCode();
      BufferedReader br;
      if (responseCode == 200) {
        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      } else {
        br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
      }

      String inputLine;
      StringBuilder response;
      response = new StringBuilder();
      while ((inputLine = br.readLine()) != null) {
        response.append(inputLine);
      }
      br.close();

      return response.toString();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  //url호출시 파라미터에 한글을 넣기위한 인코딩함수
  public static String utf8Encording(String parameter){
    return URLEncoder.encode(parameter, StandardCharsets.UTF_8);
  }

}
