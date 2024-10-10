package Service.DeccoSaludAPI;

import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.PersonaVulnerable;
import Service.APIPuntos.Punto;
import Service.APIPuntos.ServicioPuntosAPI;
import Service.APIPuntos.ServicioPuntosAPIAdapter;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioDeccoSaludAPI {
    private static ServicioDeccoSaludAPI instacia = null;
    private static final String urlAPI = "https://deccosalud.onrender.com/"; // ES ESTA LA URL??

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

//    public List<Persona> obtenerPersonasVulnerables(Punto punto, String radio ) throws IOException {
//        ServicioDeccoSaludAPIAdapter servicioPokeAPIAdapter = this.retrofit.create(ServicioDeccoSaludAPIAdapter.class);
//        Call<List<Persona>> request = servicioPokeAPIAdapter.puntos(punto.getLatitud(), punto.getLongitud(),radio);
//        return  request.execute().body();
//    }
}
