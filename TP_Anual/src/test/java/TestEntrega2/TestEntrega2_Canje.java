package TestEntrega2;

import Controller.*;
import Models.Domain.Excepciones.NoTienePuntosCanjeException;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Juridico;
import Models.Domain.Producto.Producto;
import Models.Domain.Producto.TipoRubro;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.Utilidades.TipoFrecuencia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
/*
public class TestEntrega2_Canje {

    Colaborador colaborador1;
    Colaborador colaborador2;
    ContribucionController controller;
    Producto notebook;
    Producto tv;
    Producto lampara;


    @BeforeEach
    public void init() {
        colaborador1 = new Juridico();
        colaborador2 = new Fisico();
        notebook = new Producto(TipoRubro.ELECTRONICA,"Laptop","Pic", "descripcionnnn");
        lampara = new Producto(TipoRubro.ELECTRONICA, "Lucid", "Pic", "descripcionnnn");
        tv = new Producto(TipoRubro.ELECTRONICA, "smartTV", "Pic", "descripcionnnn");
    }

    // Persona humana canjea un producto
    @Test
    public void laPersonaCanjeaSusPuntosPorLaptop() throws IOException {
        controller = new ContribucionController(colaborador1);

        controller.save(TipoDonacion.OFRECER_PRODUCTO, notebook,30.00,1);
        controller.save(TipoDonacion.OFRECER_PRODUCTO, tv,70.00,1);
        controller.save(TipoDonacion.OFRECER_PRODUCTO, lampara,10.00,1);


        controller.setUsuario(colaborador2);

        controller.save(TipoDonacion.DONACION_DINERO, 40.00, TipoFrecuencia.DIARIO);
        // 20 puntos
        controller.save(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");
        // 10 puntos

        List<OfrecerProducto> productos = colaborador1.getContribucion().stream()
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
        controller.save(TipoDonacion.OFRECER_PRODUCTO,notebook,30.00,1);

        List<OfrecerProducto> productos = colaborador1.getContribucion().stream()
                .filter(f -> f instanceof OfrecerProducto) // Filtrar objetos de tipo Producto
                .map(f -> (OfrecerProducto) f) // Convertir a tipo Producto
                .toList();
        OfrecerProducto producto = productos.get(0);

        Assertions.assertThrows(NoTienePuntosCanjeException.class, () -> {
            colaborador1.realizarCanje(producto, 1);
        });
    }


    // No se realiza el canje porque la cantidad del producto es menor a 0
    @Test
    public void noSeDisponeDeLaCantidadDeProductos() throws IOException {
        controller = new ContribucionController(colaborador1);

        controller.save(TipoDonacion.OFRECER_PRODUCTO, notebook,30.00,1);

        controller.setUsuario(colaborador2);

        controller.save(TipoDonacion.DONACION_DINERO, 40.00, TipoFrecuencia.DIARIO);
        // 20 puntos
        controller.save(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");
        // 10 puntos

        List<OfrecerProducto> productos = colaborador1.getContribucion().stream()
                .filter(f -> f instanceof OfrecerProducto) // Filtrar objetos de tipo Producto
                .map(f -> (OfrecerProducto) f) // Convertir a tipo Producto
                .toList();
        OfrecerProducto producto = productos.get(0);

        Assertions.assertThrows(NoTienePuntosCanjeException.class, () -> {
            colaborador1.realizarCanje(producto, 2);
        });
    }


    @Test
    public void laPersonaJuridicaCanjeSusPuntosPorLaptop2() throws IOException {

        controller = new ContribucionController(colaborador1);

        controller.save(TipoDonacion.OFRECER_PRODUCTO, tv,70.00,1);

        controller.save(TipoDonacion.DONACION_DINERO,140.00, TipoFrecuencia.DIARIO);

        List<OfrecerProducto> productos = colaborador1.getContribucion().stream()
                .filter(f -> f instanceof OfrecerProducto) // Filtrar objetos de tipo Producto
                .map(f -> (OfrecerProducto) f) // Convertir a tipo Producto
                .toList();
        OfrecerProducto producto = productos.get(0);

        colaborador1.realizarCanje(producto,1);

        Assertions.assertEquals("smartTV", colaborador1.getHistorialCanje().get(0).getOfrecerProducto().getProducto().getNombre());

    }



}

 */
