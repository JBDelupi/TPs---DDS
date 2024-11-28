package Service.SSO;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;

public class AdapterGoogleSSO {
    private Sso adaptado;

    public AdapterGoogleSSO(Sso adaptado) {
        this.adaptado = adaptado;
    }

    public String verAutorizacion(){
        return adaptado.getAuthorizationUrl();
    }
    public String generarToken(String codigoAutenticacion){
        return adaptado.getAccessToken(codigoAutenticacion);
    }

    public JsonNode infoUsuario(String codigoAutenticacion){
        return adaptado.getUserInfo(codigoAutenticacion);
    }


}
