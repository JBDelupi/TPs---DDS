package Service.DeccoSaludAPI.DTO;

import lombok.Getter;

import java.util.List;

@Getter


public class RespuestaDTO {
    private String barrio;
    private int cantidadPersonas;
    private List<String> personas;
}
