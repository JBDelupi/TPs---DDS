package Service.DeccoSaludAPI;

import Models.Domain.Personas.Actores.Persona;
import Service.APIPuntos.Punto;
import Service.DeccoSaludAPI.DTO.PersonaVulnerableDTO;
import Service.DeccoSaludAPI.DTO.RespuestaDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;

public interface ServicioDeccoSaludAPIAdapter {
    @POST("/pedidos")
    Call<List<RespuestaDTO>> personas(@Body List<PersonaVulnerableDTO> personaVulnerableDTO);
}
