package Service.SSO;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

public class GoogleAdaptado implements Sso{
    private final OAuth20Service service;

    public GoogleAdaptado(String clientId, String clientSecret, String redirectUri) {
        this.service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .callback(redirectUri)
                .defaultScope("profile email")
                .build(GoogleApi20.instance());
    }


    @Override
    public String getAuthorizationUrl() {
        return service.getAuthorizationUrl();
    }

    @Override
    public String getAccessToken(String authorizationCode) {
        try {
            OAuth2AccessToken token = service.getAccessToken(authorizationCode);
            return token.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonNode getUserInfo(String accessToken) {
        try {
            String userInfoEndpoint = "https://www.googleapis.com/oauth2/v1/userinfo";

            // Configurar una conexión HTTP
            java.net.URL url = new java.net.URL(userInfoEndpoint);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);

            // Leer la respuesta
            java.io.InputStream responseStream = connection.getInputStream();
            java.util.Scanner scanner = new java.util.Scanner(responseStream, "UTF-8").useDelimiter("\\A");
            String userInfo = scanner.hasNext() ? scanner.next() : "";

            ObjectMapper mapper = new ObjectMapper();

            JsonNode user = mapper.readTree(userInfo);

            // Cerrar recursos
            scanner.close();
            responseStream.close();

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
