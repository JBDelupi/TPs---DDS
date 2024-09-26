//package TestEntrega3;
//
//import Controller.ContribucionController;
//import Models.Domain.Excepciones.NoHaySolicitudExepction;
//import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
//import Models.Domain.Heladera.Heladera;
//import Models.Domain.Heladera.Vianda;
//import Models.Domain.Personas.Actores.Fisico;
//import Models.Domain.Tarjetas.*;
//import Models.Repository.PseudoBaseDatosHeladera;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//
//public class TestEntrega3_Tarjeta {
//
//    Fisico h;
//    TarjetaAccesos tarjeta;
//    ContribucionController contralador;
//    PseudoBaseDatosHeladera base;
//    @BeforeEach
//    public void init(){
//        h = new Fisico();
//        tarjeta = new TarjetaAccesos(h);
//        contralador = new ContribucionController(h);
//        h.setTarjeta(tarjeta);
//        base = new PseudoBaseDatosHeladera();
//    }
//
//    @Test // REQUERIMIENTO 1
//    public void tarjetaAsignarAColaborador() throws IOException {
//        Assertions.assertEquals(tarjeta.getTitular(), h);
//    }
//
//    @Test
//    public void ColaboradorSolicitaUnaAperturaYCumple() throws IOException {
//
//        SolicitudDeApertura solicitudDeApertura = new SolicitudDeApertura(TipoDonacion.DISTRIBUCION_VIANDAS, new Heladera());
//        tarjeta.agregarNuevaSolicitud(solicitudDeApertura);
//
//        Heladera heladera1 = base.baseHeladeras.get(0);
//        heladera1.agregarVianda(new Vianda(), new Vianda(), new Vianda());
//        Heladera heladera2 = base.baseHeladeras.get(1);
//
//        contralador.save(TipoDonacion.DISTRIBUCION_VIANDAS,heladera1, heladera2, 2, "");
//
//        Assertions.assertEquals(1,tarjeta.getUsos().size());
//        Assertions.assertFalse(heladera2.getViandas().isEmpty());
//
//    }
//
//    @Test
//    public void ColaboradorSolicitaUnaAperturaPeroHaceDosContribuciones() throws IOException {
//
//        SolicitudDeApertura solicitudDeApertura = new SolicitudDeApertura(TipoDonacion.DONACION_DE_VIANDA, new Heladera());
//        tarjeta.agregarNuevaSolicitud(solicitudDeApertura);
//
//        contralador.save(TipoDonacion.DONACION_DE_VIANDA, new Vianda(), base.baseHeladeras.get(0));
//
//        Assertions.assertThrows(NoHaySolicitudExepction.class, () -> {
//            contralador.save(TipoDonacion.DONACION_DE_VIANDA, new Vianda(),  base.baseHeladeras.get(0));
//        });
//    }
//
//    @Test
//    public void ColaboradorSolicitaUnaAperturaDistintaContribucion() throws IOException {
//
//        SolicitudDeApertura solicitudDeApertura = new SolicitudDeApertura(TipoDonacion.DONACION_DE_VIANDA, new Heladera());
//        tarjeta.agregarNuevaSolicitud(solicitudDeApertura);
//
//        Assertions.assertThrows(NoHaySolicitudExepction.class, () -> {
//            contralador.save(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");
//        });
//    }
//
//    @Test
//    public void ColaboradorQuiereUsarUnaSolicitudYaUsadaAntes() throws IOException {
//
//        SolicitudDeApertura solicitudDeApertura = new SolicitudDeApertura(TipoDonacion.DISTRIBUCION_VIANDAS, new Heladera());
//        solicitudDeApertura.setRealizada(true);
//        tarjeta.agregarNuevaSolicitud(solicitudDeApertura);
//
//        Assertions.assertThrows(NoHaySolicitudExepction.class, () -> {
//            contralador.save(TipoDonacion.DISTRIBUCION_VIANDAS, null, null, 10, "");
//        });
//    }
//}
