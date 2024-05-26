import Models.Heladera;
import Models.Vianda;
import Service.APIPuntos.Punto;
import Service.APIPuntos.ServicioPuntosAPI;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        /*
        Colaborador lucas = new Humano("lucas","iturrioz");

        ContribucionController usuario = new ContribucionController(lucas);

        usuario.create(TipoDonacion.ENTREGA_TARJETAS,"Jose",0);

        EntregaDeTarjeta formaDeContribucion = (EntregaDeTarjeta) lucas.getFormaDeContribucion().get(0);
        Tarjeta tarjeta = formaDeContribucion.getTarjeta();

        // Supongamos que usa la tarjeta "JOSE"

        RegistroDeUso nuevoUso = new RegistroDeUso(new Heladera());
        tarjeta.agregarNuevoUso(nuevoUso); // 1 VEZ
        tarjeta.agregarNuevoUso(nuevoUso); // 2 VEZ
        tarjeta.agregarNuevoUso(nuevoUso); // 3 VEZ
        tarjeta.agregarNuevoUso(nuevoUso); // 4 VEZ
        tarjeta.agregarNuevoUso(nuevoUso); // 5 VEZ -> no lo dejaria
        */
        /////////////////////////-------------- test/////////////////////////
        /*

        */
        /*
       Heladera heladera = new Heladera();
       Vianda vianda = new Vianda();
       heladera.setCapacidadDeViandas(3);
       heladera.setTemperaturaMax(100.0);
       heladera.setTemperaturaMin(30.0);
       heladera.setTemperaturaActual(50.00);
       Thread.sleep(10000);
       heladera.setTemperaturaActual(110.00);
        */
      // heladera.agregarVianda(vianda);
      // heladera.setAbierto(true);

      // Vianda vianda1 = heladera.obtenerVianda();

        Punto punto = ServicioPuntosAPI.getInstance().obtenerPunto("1");
        System.out.println("Latitud : " + punto.getLatitud());
        System.out.println("Longitud : " + punto.getLongitud());

    }

}
