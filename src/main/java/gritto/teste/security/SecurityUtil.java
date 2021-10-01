package gritto.teste.security;

import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecurityUtil {

  public static JsonObject buildBody(int status, String error, String message, String path) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    JsonObject json = new JsonObject();
    json.addProperty("timestamp", df.format(new Date()));
    json.addProperty("status", status);
    json.addProperty("error", error);
    json.addProperty("message", message);
    json.addProperty("path", path);

    return json;
  }

}
