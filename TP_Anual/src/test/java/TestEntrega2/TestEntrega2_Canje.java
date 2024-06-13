package TestEntrega2;

import Controller.*;
import Models.Domain.FormasDeContribucion.*;
import Models.Domain.Personas.Colaborador;
import Models.Domain.Personas.Humano;
import Models.Domain.Personas.Juridico;
import Models.Domain.Producto;
import Models.Domain.TipoFrecuencia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TestEntrega2_Canje {
    Colaborador colaborador1;
    Colaborador colaborador2;
    Controller controller;
    Producto notebook;
    Producto tv;
    Producto lampara;


    @BeforeEach
    public void init() {
        colaborador1 = new Juridico();
        colaborador2 = new Humano();
        notebook = new Producto(TipoRubro.ELECTRONICA,"Laptop","Pic");
        lampara = new Producto(TipoRubro.ELECTRONICA, "Lucid", "Pic");
        tv = new Producto(TipoRubro.ELECTRONICA, "smartTV", "Pic");
    }

    // Persona humana canjea un producto
    @Test
    public void laPersonaCanjeaSusPuntosPorLaptop() throws IOException {
        controller = new ContribucionController(colaborador1);

        controller.create(TipoDonacion.OFRECER_PRODUCTO, notebook,30.00,1);
        controller.create(TipoDonacion.OFRECER_PRODUCTO, tv,70.00,1);
        controller.create(TipoDonacion.OFRECER_PRODUCTO, lampara,10.00,1);


        controller.setUsuario(colaborador2);

        controller.create(TipoDonacion.DONACION_DINERO, 40.00, TipoFrecuencia.DIARIO);
        // 20 puntos
        controller.create(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");
        // 10 puntos

        List<OfrecerProducto> productos = colaborador1.getFormaDeContribucion().stream()
                .filter(f -> f instanceof OfrecerProducto) // Filtrar objetos de tipo Producto
                .map(f -> (OfrecerProducto) f) // Convertir a tipo Producto
                .toList();
        OfrecerProducto producto = productos.get(0);

        colaborador2.realizarCanje(producto,1);

        Assertions.assertEquals("Laptop", colaborador2.getHistorialCanje().get(0).getOfrecerProducto().getProducto().getNombre());
    }


    @Test
    public void laPersonaNoTienePuntosSuficientes() throws IOException {
        controller = new ContribucionController(colaborador1);
        controller.create(TipoDonacion.OFRECER_PRODUCTO,notebook,30.00,1);

        List<OfrecerProducto> productos = colaborador1.getFormaDeContribucion().stream()
                .filter(f -> f instanceof OfrecerProducto) // Filtrar objetos de tipo Producto
                .map(f -> (OfrecerProducto) f) // Convertir a tipo Producto
                .toList();
        OfrecerProducto producto = productos.get(0);


        colaborador1.realizarCanje(producto,1);
        Assertions.assertEquals(0, colaborador1.getHistorialCanje().size());
    }


    // No se realiza el canje porque la cantidad del producto es menor a 0
    @Test
    public void noSeDisponeDeLaCantidadDeProductos() throws IOException {
        controller = new ContribucionController(colaborador1);

        controller.create(TipoDonacion.OFRECER_PRODUCTO, notebook,30.00,1);

        controller.setUsuario(colaborador2);

        controller.create(TipoDonacion.DONACION_DINERO, 40.00, TipoFrecuencia.DIARIO);
        // 20 puntos
        controller.create(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");
        // 10 puntos

        List<OfrecerProducto> productos = colaborador1.getFormaDeContribucion().stream()
                .filter(f -> f instanceof OfrecerProducto) // Filtrar objetos de tipo Producto
                .map(f -> (OfrecerProducto) f) // Convertir a tipo Producto
                .toList();
        OfrecerProducto producto = productos.get(0);

        colaborador2.realizarCanje(producto,2);

        Assertions.assertEquals(0, colaborador2.getHistorialCanje().size());
    }


    @Test
    public void laPersonaJuridicaCanjeSusPuntosPorLaptop2() throws IOException {

        controller = new ContribucionController(colaborador1);

        controller.create(TipoDonacion.OFRECER_PRODUCTO, tv,70.00,1);

        controller.create(TipoDonacion.DONACION_DINERO,140.00, TipoFrecuencia.DIARIO);

        List<OfrecerProducto> productos = colaborador1.getFormaDeContribucion().stream()
                .filter(f -> f instanceof OfrecerProducto) // Filtrar objetos de tipo Producto
                .map(f -> (OfrecerProducto) f) // Convertir a tipo Producto
                .toList();
        OfrecerProducto producto = productos.get(0);

        colaborador1.realizarCanje(producto,1);

        Assertions.assertEquals("smartTV", colaborador1.getHistorialCanje().get(0).getOfrecerProducto().getProducto().getNombre());

    }



}
