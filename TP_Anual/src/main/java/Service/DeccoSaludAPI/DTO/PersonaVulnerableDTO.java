package Service.DeccoSaludAPI.DTO;

import lombok.Setter;

import java.util.List;

@Setter
public class PersonaVulnerableDTO {
    private String nombre;
    private List<String> barrios;

}
