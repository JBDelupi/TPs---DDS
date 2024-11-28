package Service.SSO;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;

public interface Sso {
    String getAuthorizationUrl();
    String getAccessToken(String authorizationCode);
    JsonNode getUserInfo(String accessToken);
}
