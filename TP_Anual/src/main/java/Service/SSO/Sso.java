package Service.SSO;

import com.fasterxml.jackson.databind.JsonNode;

public interface Sso {
    String getAuthorizationUrl();
    String getAccessToken(String authorizationCode);
    JsonNode getUserInfo(String accessToken);
}
