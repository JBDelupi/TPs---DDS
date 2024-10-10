package Controller.DTO;

import java.util.Map;

public class CrearContribucionDTO {
    private String tipoDonacion;
    private Map<String, String> params;

    public CrearContribucionDTO(String tipoDonacion, Map<String, String> params) {
        this.tipoDonacion = tipoDonacion;
        this.params = params;
    }

    public String getTipoDonacion() {
        return tipoDonacion;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
