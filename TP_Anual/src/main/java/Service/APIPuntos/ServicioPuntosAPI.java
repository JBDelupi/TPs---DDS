package Service.APIPuntos;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ServicioPuntosAPI {
        private static ServicioPuntosAPI instacia = null;
        private static final String urlAPI = "https://c2254ab6-5c27-415f-8943-b37a219c27e4.mock.pstmn.io/api/";

        private Retrofit retrofit;

        private ServicioPuntosAPI() {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(urlAPI)
                    .addConverterFactory(GsonConverterFactory.create()) // Lo transforma de json
                    .build();
        }

        public static ServicioPuntosAPI getInstance(){
            if(instacia==null){
                instacia = new ServicioPuntosAPI();
            }
            return instacia;
        }

    public List<Punto> obtenerPuntosEstrategicos(Punto punto,String radio ) throws IOException {
        ServicioPuntosAPIAdapter servicioPokeAPIAdapter = this.retrofit.create(ServicioPuntosAPIAdapter.class);
        Call<List<Punto>> requestPokemon = servicioPokeAPIAdapter.puntos(punto.getLatitud(), punto.getLongitud(),radio);
        return  requestPokemon.execute().body();
    }

}





