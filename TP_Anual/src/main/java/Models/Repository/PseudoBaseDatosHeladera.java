package Models.Repository;

import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Vianda;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Heladera.Heladera;
import Service.APIPuntos.Punto;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class PseudoBaseDatosHeladera {
    private Heladera heladera1;
    private Heladera heladera2;
    private Heladera heladera3;
    private Heladera heladera4;
    private Heladera heladera5;
    private static PseudoBaseDatosHeladera instancia = null;

    public List<Heladera> baseHeladeras;

    public static PseudoBaseDatosHeladera getInstance() {
        if (instancia == null) {
            instancia = new PseudoBaseDatosHeladera();
        }
        return instancia;
    }

    public PseudoBaseDatosHeladera() {
        heladera1 = new Heladera();
        heladera2 = new Heladera();
        heladera3 = new Heladera();
        heladera4 = new Heladera();
        heladera5 = new Heladera();

        baseHeladeras = new ArrayList<>();
        ubicaciones();
        agregar(heladera1, heladera2, heladera3, heladera4, heladera5);
        ejecutar();


    }

    public void agregar(Heladera... h) {
        Collections.addAll(this.baseHeladeras, h);
    }

    public void ejecutar() {
        for (int i = 0; i < baseHeladeras.size(); i++) {
            Direccion direccion = new Direccion();
            direccion.setCalle("Av. Medrano");
            direccion.setLocalidad("Almagro");
            direccion.setNumero("951");
            Heladera heladera = baseHeladeras.get(i);
            heladera.setDireccion(direccion);
            heladera.setId(i + 1);
            heladera.setCantidadDeFallas(20);
            heladera.setCantidadDeviandasRetiradas(3);
            heladera.setTemperaturaMin(5.00);
            heladera.setTemperaturaMax(100.00);
            heladera.setAbierto(Math.random() < 0.5);
            heladera.setCapacidadDeViandas((int)(Math.random()*100)+20);
            heladera.setEstadoActual(EstadoHeladera.DISPONIBLE);
            // Generar una fecha random dentro de los últimos 365 días
            LocalDate startDate = LocalDate.now().minus(1, ChronoUnit.YEARS);
            LocalDate endDate = LocalDate.now();
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
            LocalDate randomDate = startDate.plusDays((long) (Math.random() * daysBetween));

            heladera.setFechaDePuestaEnMarcha(randomDate);

            Vianda vianda1 = new Vianda();
            Vianda vianda2 = new Vianda();
            Vianda vianda3 = new Vianda();

            heladera.agregarVianda(vianda1);
            heladera.agregarVianda(vianda2);
            heladera.agregarVianda(vianda3);
        }
    }

    public Heladera init(Heladera heladera, Direccion direccion) {
        HeladeraBuilder heladeraBuilder = new HeladeraBuilder();
        Heladera nuevaHeladera = heladeraBuilder
                .capacidadMaxima(10)
                .abierto(true)
                .Direccion(direccion)
                .construir();
        nuevaHeladera.getDireccion().setCentro(heladera.getDireccion().getCentro()); // Preserva las coordenadas
        return nuevaHeladera;
    }

    public Heladera getId(String id) {
        return baseHeladeras.stream().filter(f -> f.getId() == Integer.parseInt(id)).findFirst().orElse(null);
    }

    public void ubicaciones() {
        Punto plazaDeMayo = new Punto();
        plazaDeMayo.setLatitud("-34.6083");
        plazaDeMayo.setLongitud("-58.3712");

        Punto obelisco = new Punto();
        obelisco.setLatitud("-34.6037");
        obelisco.setLongitud("-58.3816");

        Punto congreso = new Punto();
        congreso.setLatitud("-34.6096");
        congreso.setLongitud("-58.3929");

        Punto puertoMadero = new Punto();
        puertoMadero.setLatitud("-34.6114");
        puertoMadero.setLongitud("-58.3625");

        Punto estacionRetiro = new Punto();
        estacionRetiro.setLatitud("-34.5957");
        estacionRetiro.setLongitud("-58.3745");

        Punto teatroColon = new Punto();
        teatroColon.setLatitud("-34.6011");
        teatroColon.setLongitud("-58.3835");

        Punto palermo = new Punto();
        palermo.setLatitud("-34.5801");
        palermo.setLongitud("-58.4253");

        Punto laBoca = new Punto();
        laBoca.setLatitud("-34.6345");
        laBoca.setLongitud("-58.3632");

        Punto recoleta = new Punto();
        recoleta.setLatitud("-34.5889");
        recoleta.setLongitud("-58.3935");

        Punto sanTelmo = new Punto();
        sanTelmo.setLatitud("-34.6215");
        sanTelmo.setLongitud("-58.3730");

        heladera1.getDireccion().setCentro(palermo);
        heladera2.getDireccion().setCentro(sanTelmo);
        heladera3.getDireccion().setCentro(recoleta);
        heladera4.getDireccion().setCentro(teatroColon);
        heladera5.getDireccion().setCentro(estacionRetiro);

    }
}
