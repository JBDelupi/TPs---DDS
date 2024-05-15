package Service;

import DTO.PokemonDTO;
import Entity.Movimiento;
import Entity.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServicioPokeAPIAdapter {


    @GET("pokemon/{name}")
    Call<Pokemon> pokemon(@Path("name") String name);

    @GET("move/{name}")
    Call<Movimiento> movimento(@Path("name") String name);

}
