package TestEntrega3;

import Controller.Controller;
import Controller.ContribucionController;
import Models.Domain.Excepciones.NoHaySolicitudExepction;
import Models.Domain.Excepciones.SinViandasException;
import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Tarjetas.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestEntrega3_Tarjeta {

    Humano h;
    TarjetaAccesosAHeladera tarjeta;
    ContribucionController contralador;
    @BeforeEach
    public void init(){
        h = new Humano();
        tarjeta = new TarjetaAccesosAHeladera(h);
        contralador = new ContribucionController(h);
        h.setTarjeta(tarjeta);
    }

    @Test
    public void tarjetaAsignarAColaborador() throws IOException {
        Assertions.assertEquals(tarjeta.getTitular(), h);
    }

    @Test
    public void ColaboradorSolicitaUnaAperturaYCumple() throws IOException {

        SolicitudDeApertura solicitudDeApertura = new SolicitudDeApertura(TipoDonacion.DISTRIBUCION_VIANDAS, new Heladera());
        tarjeta.agregarNuevaSolicitud(solicitudDeApertura);

        contralador.save(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");


        Assertions.assertEquals(1,tarjeta.getUsos().size());

    }

    @Test
    public void ColaboradorSolicitaUnaAperturaYPeroHaceDosContribuciones() throws IOException {

        SolicitudDeApertura solicitudDeApertura = new SolicitudDeApertura(TipoDonacion.DISTRIBUCION_VIANDAS, new Heladera());
        tarjeta.agregarNuevaSolicitud(solicitudDeApertura);

        contralador.save(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");

        Assertions.assertThrows(NoHaySolicitudExepction.class, () -> {
            contralador.save(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");
        });
    }

    @Test
    public void ColaboradorSolicitaUnaAperturaDistintaContribucion() throws IOException {

        SolicitudDeApertura solicitudDeApertura = new SolicitudDeApertura(TipoDonacion.DONACION_DE_VIANDA, new Heladera());
        tarjeta.agregarNuevaSolicitud(solicitudDeApertura);

        Assertions.assertThrows(NoHaySolicitudExepction.class, () -> {
            contralador.save(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");
        });
    }

    @Test
    public void ColaboradorQuiereUsarUnaSolicitudYaUsadaAntes() throws IOException {

        SolicitudDeApertura solicitudDeApertura = new SolicitudDeApertura(TipoDonacion.DISTRIBUCION_VIANDAS, new Heladera());
        solicitudDeApertura.setRealizada(true);
        tarjeta.agregarNuevaSolicitud(solicitudDeApertura);

        Assertions.assertThrows(NoHaySolicitudExepction.class, () -> {
            contralador.save(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");
        });
    }
}
