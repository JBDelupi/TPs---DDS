
import Controller.*;

import Models.FormasDeContribucion.EntregaDeTarjeta;
import Models.FormasDeContribucion.FormaDeContribucion;
import Models.FormasDeContribucion.TipoDonacion;
import Models.Heladera;
import Models.Sensores.SensorTemperatura;
import Models.Personas.Administrador;
import Models.Personas.Colaborador;
import Models.Personas.Humano;
import Models.Personas.Usuario;
import Models.Tarjeta.RegistroDeUso;
import Models.Tarjeta.Tarjeta;
import Models.Vianda;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws InterruptedException {
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
       Heladera heladera = new Heladera();
       Vianda vianda = new Vianda();
       heladera.setCapacidadDeViandas(3);
       heladera.setTemperaturaMax(100.0);
       heladera.setTemperaturaMin(30.0);
       heladera.setTemperaturaActual(50.00);
       Thread.sleep(10000);
       heladera.setTemperaturaActual(110.00);

      // heladera.agregarVianda(vianda);
      // heladera.setAbierto(true);

      // Vianda vianda1 = heladera.obtenerVianda();

    }

}
