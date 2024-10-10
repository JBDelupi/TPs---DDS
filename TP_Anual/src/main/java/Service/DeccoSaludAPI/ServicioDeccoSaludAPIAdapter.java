package Service.DeccoSaludAPI;

import Service.APIPuntos.Punto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ServicioDeccoSaludAPIAdapter {
    // ENDPOINT
    @GET("PersonasVulnerables")
    Call<List<Punto>> puntos(@Query("latitud") String latitud,
                             @Query("longitud") String longitud,
                             @Query("radio") String radio );
}
