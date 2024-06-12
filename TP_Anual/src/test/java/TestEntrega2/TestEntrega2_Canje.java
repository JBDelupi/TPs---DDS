package TestEntrega2;

import Controller.*;
import Models.Domain.FormasDeContribucion.*;
import Models.Domain.Personas.Colaborador;
import Models.Domain.Personas.Humano;
import Models.Domain.TipoFrecuencia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestEntrega2_Canje {
    Colaborador colaborador1;
    Colaborador colaborador2;
    Producto laptop;

    @BeforeEach
    public void init() {
        colaborador1 = new Humano();
        colaborador2 = new Humano();
        laptop = new Producto(TipoRubro.ELECTRONICA,"Laptop",30.0,1, "Descripcion");

    }
    /*
    // Persona humana canjea un producto
    @Test
    public void laPersonaCanjeaSusPuntosPorLaptop() throws IOException {
        colaborador1.sumarPuntaje(new DonacionDeDinero(40.0, TipoFrecuencia.DIARIO));
        // 20 puntos
        colaborador1.sumarPuntaje(new DistribucionDeViandas(null,null,10,""));
        // 10 puntos
        colaborador1.realizarCanje(laptop,1);
        Assertions.assertEquals("Laptop", colaborador1.getHistorialCanje().get(0).getProducto().getNombre());
    }

    // La persona no dispone de los puntos necesarios
    @Test
    public void laPersonaNoTienePuntosSuficientes() throws IOException {
        colaborador1.sumarPuntaje(new DonacionDeDinero(38.0, TipoFrecuencia.DIARIO));
        // 19 puntos
        colaborador1.sumarPuntaje(new DistribucionDeViandas(null,null,10,""));
        // 10 puntos
        colaborador1.realizarCanje(laptop,1);
        Assertions.assertEquals(0, colaborador1.getHistorialCanje().size());
    }

    // No se realiza el canje porque la cantidad del producto es menor a 0
    @Test
    public void noSeDisponeDeLaCantidadDeProductos() throws IOException {
        colaborador1.sumarPuntaje(new DonacionDeDinero(40.0, TipoFrecuencia.DIARIO));
        // 20 puntos
        colaborador1.sumarPuntaje(new DistribucionDeViandas(null,null,10,""));
        // 10 puntos
        colaborador1.realizarCanje(laptop,1);

        // SEGUNDO COLABORADOR
        colaborador2.sumarPuntaje(new DonacionDeDinero(40.0, TipoFrecuencia.DIARIO));
        // 20 puntos
        colaborador2.sumarPuntaje(new DistribucionDeViandas(null,null,10,""));
        // 10 puntos
        colaborador1.realizarCanje(laptop,1);
        Assertions.assertEquals(0, colaborador2.getHistorialCanje().size());
    }

    @Test
    public void laPersonaCanjeSusPuntosPorLaptop2() throws IOException {

        Controller controller = new ContribucionController(colaborador1);

        System.out.println("Puntaje actual " + colaborador1.getPuntaje());
        controller.create(TipoDonacion.DONACION_DINERO,60.00, TipoFrecuencia.DIARIO);

        System.out.println("Puntaje actual " + colaborador1.getPuntaje());

        colaborador1.realizarCanje(laptop,1);

        Assertions.assertEquals("Laptop", colaborador1.getHistorialCanje().get(0).getProducto().getNombre());

    }

    */
}
