package TestEntrega2;

import Service.APIPuntos.Punto;
import Service.APIPuntos.ServicioPuntosAPI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TestEntrega2_ApiRest {

    @Test
    public void unPuntoRecomendado() throws IOException {
        Punto punto = new Punto();
        punto.setLatitud("1");
        punto.setLongitud("2");
        String radio = "100";

        List<Punto> puntos = ServicioPuntosAPI.getInstance().obtenerPuntosEstrategicos(punto,radio);

        for (Punto unPunto : puntos) {
            System.out.println("Latitud: " + unPunto.getLatitud());
            System.out.println("Longitud: " + unPunto.getLongitud());
            System.out.println("----------------------------------");
        }

        Assertions.assertFalse(puntos.isEmpty());

    }

    @Test
    public void otroPuntoRecomendado() throws IOException {
        Punto punto = new Punto();
        punto.setLatitud("2");
        punto.setLongitud("1");
        String radio = "100";

        List<Punto> puntos = ServicioPuntosAPI.getInstance().obtenerPuntosEstrategicos(punto,radio);

        for (Punto unPunto : puntos) {
            System.out.println("Latitud: " + unPunto.getLatitud());
            System.out.println("Longitud: " + unPunto.getLongitud());
            System.out.println("----------------------------------");
        }

        Assertions.assertFalse(puntos.isEmpty());

    }


}
