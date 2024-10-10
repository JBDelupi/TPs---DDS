import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Suscripciones.Sugerencia.SistemaDeRedistribucion;
import Models.Domain.Heladera.Suscripciones.Sugerencia.Sugerencia;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.PersonaVulnerable;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Domain.Reporte.TemplateReporte;
import Models.Domain.Tarjetas.RegistroDeUso;
import Models.Domain.Tarjetas.Tarjeta;
import Models.Domain.Tarjetas.TarjetaAlimentar;
import Models.Repository.PseudoBaseDatosHeladera;
import Service.APIPuntos.Punto;
import Service.APIPuntos.ServicioPuntosAPI;
import Service.DeccoSaludAPI.DTO.PersonaVulnerableDTO;
import Service.DeccoSaludAPI.DTO.RespuestaDTO;
import Service.DeccoSaludAPI.Mapper.PersonaVulneableMapper;
import Service.DeccoSaludAPI.ServicioDeccoSaludAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {

        Fisico tobi = new Fisico();
        tobi.setNombre("Tobi");
        tobi.setApellido("Duren");
        Direccion dire = new Direccion();
        dire.setLocalidad("Pilar");
        tobi.setDireccion(dire);
        PersonaVulnerable rol = new PersonaVulnerable();
        rol.setTipo(TipoRol.VULNERABLE);
        rol.setMenoresACargo(0);
        tobi.agregarRol(rol);

        Tarjeta tarjeta = new TarjetaAlimentar(tobi);

        Heladera heladera = PseudoBaseDatosHeladera.getInstance().getId("1");

        RegistroDeUso registroDeUso = new RegistroDeUso(heladera,null, null);

        tarjeta.nuevoRegistro(registroDeUso);

        List<PersonaVulnerableDTO> personasVulnerables = new ArrayList<>();

        PersonaVulnerableDTO tobiDTO = PersonaVulneableMapper.toDto(tarjeta);

        personasVulnerables.add(tobiDTO);

        List<RespuestaDTO> respuestaDTOS = ServicioDeccoSaludAPI.getInstance().obtenerPersonasVulnerables(personasVulnerables);

        System.out.println(respuestaDTOS.get(0).getBarrio() + respuestaDTOS.get(0).getPersonas().get(0) + respuestaDTOS.get(0).getCantidadPersonas());

    }
}
