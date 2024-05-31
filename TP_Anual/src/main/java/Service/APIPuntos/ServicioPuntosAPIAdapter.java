package Service.APIPuntos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ServicioPuntosAPIAdapter {


    @GET("PuntoEstrategico/{id}")
    Call<List<Punto>> punto(@Path("id") String id );




}
