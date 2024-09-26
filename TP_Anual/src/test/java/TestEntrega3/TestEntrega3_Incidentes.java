//package TestEntrega3;
//
//import Models.Domain.Builder.UsuariosBuilder.TecnicoBuilder;
//import Models.Domain.Heladera.EstadoHeladera;
//import Models.Domain.Heladera.Heladera;
//import Models.Domain.Heladera.Incidentes.Alerta;
//import Models.Domain.Heladera.Incidentes.FallaTecnica;
//import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;
//import Models.Domain.Personas.Actores.Colaborador;
//import Models.Domain.Personas.Actores.Fisico;
//import Models.Domain.Personas.Actores.Juridico;
//import Models.Domain.Personas.Actores.Tecnico;
//import Service.APIPuntos.AreaCobertura;
//import Service.APIPuntos.Punto;
//import Service.Notificacion.Correo.CorreoAdapter;
//import Service.Notificacion.Telegram.TelegramAdapter;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestEntrega3_Incidentes {
//
//    Colaborador colaboradorHumano;
//    Colaborador colaboradorJuridico;
//    FallaTecnica fallaTecnicaHumano;
//    Alerta alertaHumano;
//    FallaTecnica fallaTecnicaJuridico;
//    Heladera heladera;
//    TecnicoBuilder tecnicoBuilder1;
//    TecnicoBuilder tecnicoBuilder2;
//    TecnicoBuilder tecnicoBuilder3;
//    Tecnico tecnico1;
//    Tecnico tecnico2;
//    Tecnico tecnico3;
//    List<Tecnico> listaTecnicos;
//
//    AreaCobertura area1;
//    AreaCobertura area2;
//    AreaCobertura area3;
//    Punto punto1;
//    Punto puntoHeladera;
//
//
//    @BeforeEach
//    public void init(){
//        //Para establecer el area de cobertura del tecnico
//        punto1 = new Punto();
//        punto1.setLongitud("0");
//        punto1.setLatitud("0");
//
//        puntoHeladera = new Punto();
//        puntoHeladera.setLatitud("0");
//        puntoHeladera.setLongitud("2");
//
//        area1 = new AreaCobertura();
//        area1.setCentro(punto1);
//        area1.setRadio("1");
//        area2 = new AreaCobertura();
//        area2.setCentro(punto1);
//        area2.setRadio("3");
//        area3 = new AreaCobertura();
//        area3.setCentro(punto1);
//        area3.setRadio("6");
//
//
//        colaboradorHumano = new Fisico();
//        colaboradorJuridico = new Juridico();
//        heladera = new Heladera();
//
//        tecnicoBuilder1 = new TecnicoBuilder();
//        tecnico1 = tecnicoBuilder1
//                .area(area1)
//                .nombre("Tecnico 1")
//                .medioDeNotificacion(new CorreoAdapter())
//                .construir();
//
//        tecnicoBuilder2 = new TecnicoBuilder();
//        tecnico2 = tecnicoBuilder2
//                .area(area2)
//                .nombre("Tecnico 2")
//                .correoElectronico("1738213090")
//                .medioDeNotificacion(new TelegramAdapter())
//                .construir();
//
//        tecnicoBuilder3 = new TecnicoBuilder();
//        tecnico3 = tecnicoBuilder3
//                .area(area3)
//                .nombre("Tecnico 3")
//                .medioDeNotificacion(new CorreoAdapter())
//                .construir();
//
//        listaTecnicos = new ArrayList<Tecnico>();
//        listaTecnicos.add(tecnico1);
//        listaTecnicos.add(tecnico2);
//        listaTecnicos.add(tecnico3);
//
//        heladera.setCoordenadas(puntoHeladera);
//        heladera.setEstadoActual(EstadoHeladera.DISPONIBLE);
//    }
//
//    // Un colaborador humano reporta una falla tecnica
//    @Test
//    public void humanoReportaIncidente() throws IOException {
//        fallaTecnicaHumano = new FallaTecnica();
//        fallaTecnicaHumano.setHeladera(heladera);
//        this.heladera.setEstadoActual(EstadoHeladera.NO_DISPONIBLE);
//        fallaTecnicaHumano.setColaborador(colaboradorHumano);
//        fallaTecnicaHumano.setVisitasTecnicas(new ArrayList<>());
//
//        fallaTecnicaHumano.crearRegistroDeVisita(tecnico1, LocalDateTime.now(), false);
//        Assertions.assertEquals(heladera.getEstadoActual(), EstadoHeladera.NO_DISPONIBLE);
//        fallaTecnicaHumano.crearRegistroDeVisita(tecnico1, LocalDateTime.now(), true);
//        Assertions.assertEquals(heladera.getEstadoActual(), EstadoHeladera.DISPONIBLE);
//        Assertions.assertEquals(2, fallaTecnicaHumano.getVisitasTecnicas().size());
//        Assertions.assertTrue(fallaTecnicaHumano.getSolucionado());
//    }
//
//    // Un colaborador juridico reporta una falla tecnica
//    @Test
//    public void JuridicoReportaIncidente() throws IOException {
//        fallaTecnicaJuridico = new FallaTecnica();
//        fallaTecnicaJuridico.setHeladera(heladera);
//        fallaTecnicaJuridico.setColaborador(colaboradorJuridico);
//        fallaTecnicaJuridico.setVisitasTecnicas(new ArrayList<>());
//        this.heladera.setEstadoActual(EstadoHeladera.NO_DISPONIBLE);
//        fallaTecnicaJuridico.crearRegistroDeVisita(tecnico1, LocalDateTime.now(), false);
//        Assertions.assertEquals(heladera.getEstadoActual(), EstadoHeladera.NO_DISPONIBLE);
//        fallaTecnicaJuridico.crearRegistroDeVisita(tecnico1, LocalDateTime.now(), true);
//        Assertions.assertEquals(heladera.getEstadoActual(), EstadoHeladera.DISPONIBLE);
//        Assertions.assertEquals(2, fallaTecnicaJuridico.getVisitasTecnicas().size());
//        Assertions.assertTrue(fallaTecnicaJuridico.getSolucionado());
//    }
//
//    @Test
//    public void TecnicoRecibeAvisoFallaTecnica() throws IOException {
//        fallaTecnicaHumano = new FallaTecnica();
//        fallaTecnicaHumano.setHeladera(heladera);
//        fallaTecnicaHumano.setColaborador(colaboradorHumano);
//        Tecnico tecnicoANotificar = fallaTecnicaHumano.avisarATecnico(listaTecnicos);
//        Assertions.assertEquals("Tecnico 2",tecnicoANotificar.getNombre());
//    }
//
//    @Test
//    public void TecnicoRecibeAvisoAlerta() throws IOException {
//        alertaHumano = new Alerta(TipoAlerta.FALLA_EN_CONEXION, heladera);
//        Tecnico tecnicoANotificar = alertaHumano.avisarATecnico(listaTecnicos);
//        Assertions.assertEquals("Tecnico 2",tecnicoANotificar.getNombre());
//    }
//}
//
