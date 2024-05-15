package Service;

import Entity.Movimiento;
import Entity.Pokemon;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class ServicioPokeAPI {
    private static ServicioPokeAPI instacia = null;
    private static final String urlAPI = "https://pokeapi.co/api/v2/";

    private Retrofit retrofit;

    private ServicioPokeAPI() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create()) // Lo transforma de json
                .build();
    }

    public static ServicioPokeAPI getInstance(){
        if(instacia==null){
            instacia = new ServicioPokeAPI();
        }
        return instacia;
    }


    public Pokemon pokemon ( String name ) throws IOException {
        ServicioPokeAPIAdapter servicioPokeAPIAdapter = this.retrofit.create(ServicioPokeAPIAdapter.class);
        Call<Pokemon> requestPokemon = servicioPokeAPIAdapter.pokemon(name);
        return  requestPokemon.execute().body();
    }

    public Movimiento movimiento (String name ) throws IOException {
        ServicioPokeAPIAdapter servicioPokeAPIAdapter = this.retrofit.create(ServicioPokeAPIAdapter.class);
        Call<Movimiento> requestPokemon = servicioPokeAPIAdapter.movimento(name);
        return  requestPokemon.execute().body();
    }

}
