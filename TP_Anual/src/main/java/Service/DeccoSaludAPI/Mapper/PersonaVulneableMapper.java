package Service.DeccoSaludAPI.Mapper;

import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.PersonaVulnerable;
import Models.Domain.Tarjetas.Tarjeta;
import Service.DeccoSaludAPI.DTO.PersonaVulnerableDTO;

import java.util.List;
import java.util.stream.Collectors;

public  class PersonaVulneableMapper {


    public static PersonaVulnerableDTO toDto(Tarjeta t) {

        Fisico f = (Fisico) t.getTitular();
        List<String> ubicaciones = t.getUsos().stream().map(l->l.getHeladera().getDireccion().getLocalidad()).toList();
        PersonaVulnerableDTO dto = new PersonaVulnerableDTO();
        dto.setNombre(f.getNombre() + f.getApellido());
        dto.setBarrios(ubicaciones);

        return dto;
    }


}
