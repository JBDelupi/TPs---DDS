package Service.APIPuntos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServicioPuntosAPIAdapter {


    @GET("PuntoEstrategico/{id}")
    Call<Punto> punto(@Path("id") String id );




}
