package Service.APIPuntos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ServicioPuntosAPIAdapter {

    // ENDPOINT
    @GET("PuntoEstrategico")
    Call<List<Punto>> puntos(@Query("latitud") String latitud,
                             @Query("longitud") String longitud,
                             @Query("radio") String radio );




}
