package Service.DeccoSaludAPI;

import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.PersonaVulnerable;
import Models.Domain.Tarjetas.Tarjeta;
import Service.APIPuntos.Punto;
import Service.APIPuntos.ServicioPuntosAPI;
import Service.APIPuntos.ServicioPuntosAPIAdapter;
import Service.DeccoSaludAPI.DTO.PersonaVulnerableDTO;
import Service.DeccoSaludAPI.DTO.RespuestaDTO;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioDeccoSaludAPI {
    private static ServicioDeccoSaludAPI instacia = null;
    private static final String urlAPI = "https://deccosalud.onrender.com/";

    private Retrofit retrofit;

    private ServicioDeccoSaludAPI() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create()) // Lo transforma de json
                .build();
    }

    public static ServicioDeccoSaludAPI getInstance(){
        if(instacia==null){
            instacia = new ServicioDeccoSaludAPI();
        }
        return instacia;
    }

    public List<RespuestaDTO> obtenerPersonasVulnerables(List<PersonaVulnerableDTO> lista) throws IOException {
        ServicioDeccoSaludAPIAdapter servicioDeccoSaludAPIAdapter = this.retrofit.create(ServicioDeccoSaludAPIAdapter.class);
        Call<List<RespuestaDTO>> request = servicioDeccoSaludAPIAdapter.personas(lista);
        return  request.execute().body();
    }
}
