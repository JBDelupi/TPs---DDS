package Service.SistemaDeGeolocalizacion;

import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.DatosPersonales.Direccion;
import Models.Domain.Heladera.Heladera;
import Service.APIPuntos.Punto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PseudoBaseDatosHeladera {
    private Heladera heladera1;
    private Heladera heladera2;
    private Heladera heladera3;
    private Heladera heladera4;
    private Heladera heladera5;

    public List<Heladera> baseHeladeras;

    public PseudoBaseDatosHeladera() {
        heladera1 = new Heladera();
        heladera2 = new Heladera();
        heladera3 = new Heladera();
        heladera4 = new Heladera();
        heladera5 = new Heladera();

        baseHeladeras = new ArrayList<Heladera>();

        agregar(heladera1,heladera2,heladera3,heladera4,heladera5);

        ejecutar();
    }

    public void agregar(Heladera ... h) {
        Collections.addAll(this.baseHeladeras, h);
    }

    public void ejecutar(){

        for (Integer i = 0; i < baseHeladeras.size(); i++) {
            Punto punto = new Punto();
            punto.setLongitud(i.toString());
            punto.setLatitud(i.toString());
            Direccion direccion = new Direccion();
            direccion.setCalle(i.toString() + 1);
            direccion.setCentro(punto);
            init(baseHeladeras.get(i),direccion);
        }

    }

    public Heladera init(Heladera heladera, Direccion direccion){
        HeladeraBuilder heladeraBuilder = new HeladeraBuilder();
        return heladera = heladeraBuilder
                .capacidadMaxima(10)
                .abierto(true)
                .Direccion(direccion)
                .construir();

    }



}
