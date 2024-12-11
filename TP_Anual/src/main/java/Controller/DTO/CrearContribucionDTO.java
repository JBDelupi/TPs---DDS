package Controller.DTO;

import lombok.Getter;

import java.util.Map;

@Getter
public class CrearContribucionDTO {
    private String tipoDonacion;
    private Map<String, String> params;

    public CrearContribucionDTO(String tipoDonacion, Map<String, String> params) {
        this.tipoDonacion = tipoDonacion;
        this.params = params;
    }

}
