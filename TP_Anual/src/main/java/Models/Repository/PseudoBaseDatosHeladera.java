package Models.Repository;

import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Heladera.Heladera;
import Service.APIPuntos.Punto;

import javax.swing.text.html.parser.Parser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PseudoBaseDatosHeladera {
    private Heladera heladera1;
    private Heladera heladera2;
    private Heladera heladera3;
    private Heladera heladera4;
    private Heladera heladera5;
    private static PseudoBaseDatosHeladera instacia = null;


    public List<Heladera> baseHeladeras;


    public  static PseudoBaseDatosHeladera getInstance() {
        if(instacia==null){
            instacia = new PseudoBaseDatosHeladera();
        }
        return instacia;
    }


    public PseudoBaseDatosHeladera() {
        heladera1 = new Heladera();
        heladera2 = new Heladera();
        heladera3 = new Heladera();
        heladera4 = new Heladera();
        heladera5 = new Heladera();

        baseHeladeras = new ArrayList<>();

        agregar(heladera1, heladera2, heladera3, heladera4, heladera5);

        ejecutar();
    }

    public void agregar(Heladera... h) {
        Collections.addAll(this.baseHeladeras, h);
    }

    public void ejecutar() {
        for (int i = 0; i < baseHeladeras.size(); i++) {
            Punto punto = new Punto();
            punto.setLongitud(Integer.toString(i));
            punto.setLatitud(Integer.toString(i));
            Direccion direccion = new Direccion();
            direccion.setCalle(Integer.toString(i) + 1);
            direccion.setCentro(punto);
            baseHeladeras.set(i, init(baseHeladeras.get(i), direccion));
            baseHeladeras.get(i).setId(i+1);
            baseHeladeras.get(i).setTemperaturaMin(5.00);
            baseHeladeras.get(i).setTemperaturaMax(10.00);
        }
    }

    public Heladera init(Heladera heladera, Direccion direccion) {
        HeladeraBuilder heladeraBuilder = new HeladeraBuilder();
        return heladeraBuilder
                .capacidadMaxima(10)
                .abierto(true)
                .Direccion(direccion)
                .construir();
    }


    public Heladera getId(String id){
        return baseHeladeras.stream().filter(f-> f.getId() == Integer.parseInt(id)).findFirst().get();
    }

}


