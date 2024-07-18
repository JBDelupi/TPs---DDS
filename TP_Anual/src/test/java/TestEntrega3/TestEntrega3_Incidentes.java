package TestEntrega3;

import Models.Domain.Builder.UsuariosBuilder.TecnicoBuilder;
import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Incidentes.FallaTecnica;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Personas.Actores.Juridico;
import Models.Domain.Personas.Actores.Tecnico;
import Service.APIPuntos.AreaCobertura;
import Service.APIPuntos.Punto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestEntrega3_Incidentes {

    Colaborador colaboradorHumano;
    Colaborador colboradorJuridico;
    FallaTecnica fallaTecnicaHumano;
    FallaTecnica fallaTecnicaJuridico;
    Heladera heladera;
    TecnicoBuilder tecnicoBuilder1;
    TecnicoBuilder tecnicoBuilder2;
    TecnicoBuilder tecnicoBuilder3;
    Tecnico tecnico1;
    Tecnico tecnico2;
    Tecnico tecnico3;
    List<Tecnico> listaTecnicos;

    AreaCobertura area1;
    AreaCobertura area2;
    AreaCobertura area3;
    Punto punto1;
    Punto puntoHeladera;


    @BeforeEach
    public void init(){
        //Para establecer el area de cobertura del tecnico
        punto1 = new Punto();
        punto1.setLongitud("0");
        punto1.setLatitud("0");

        puntoHeladera = new Punto();
        puntoHeladera.setLatitud("0");
        puntoHeladera.setLongitud("2");

        area1 = new AreaCobertura();
        area1.setCentro(punto1);
        area1.setRadio("1");
        area2 = new AreaCobertura();
        area2.setCentro(punto1);
        area2.setRadio("3");
        area3 = new AreaCobertura();
        area3.setCentro(punto1);
        area3.setRadio("6");


        colaboradorHumano = new Humano();
        colboradorJuridico = new Juridico();
        heladera = new Heladera();
        tecnicoBuilder1 = new TecnicoBuilder();
        tecnicoBuilder1.area(area1);
        tecnico1 = tecnicoBuilder1.construir();

        tecnicoBuilder2 = new TecnicoBuilder();
        tecnicoBuilder2.area(area2);
        tecnico2 = tecnicoBuilder2.construir();

        tecnicoBuilder3 = new TecnicoBuilder();
        tecnicoBuilder3.area(area3);
        tecnico3 = tecnicoBuilder3.construir();

        listaTecnicos = new ArrayList<Tecnico>();
        listaTecnicos.add(tecnico1);
        listaTecnicos.add(tecnico2);
        listaTecnicos.add(tecnico3);

        heladera.setCoordenadas(puntoHeladera);
        heladera.setEstadoActual(EstadoHeladera.DISPONIBLE);
    }

    // Un colaborador humano reporta una falla tecnica
    @Test
    public void humanoReportaIncidente() throws IOException {
        fallaTecnicaHumano = new FallaTecnica(heladera, colaboradorHumano);

        fallaTecnicaHumano.crearRegistroDeVisita(tecnico1, LocalDateTime.now(), false);
        Assertions.assertEquals(heladera.getEstadoActual(), EstadoHeladera.NO_DISPONIBLE);
        fallaTecnicaHumano.crearRegistroDeVisita(tecnico1, LocalDateTime.now(), true);
        Assertions.assertEquals(heladera.getEstadoActual(), EstadoHeladera.DISPONIBLE);
        Assertions.assertEquals(2, fallaTecnicaHumano.getVisitasTecnicas().size());
        Assertions.assertTrue(fallaTecnicaHumano.getSolucionado());
    }

    // Un colaborador juridico reporta una falla tecnica
    @Test
    public void JuridicoReportaIncidente() throws IOException {
        fallaTecnicaJuridico = new FallaTecnica(heladera, colboradorJuridico);

        fallaTecnicaJuridico.crearRegistroDeVisita(tecnico1, LocalDateTime.now(), false);
        Assertions.assertEquals(heladera.getEstadoActual(), EstadoHeladera.NO_DISPONIBLE);
        fallaTecnicaJuridico.crearRegistroDeVisita(tecnico1, LocalDateTime.now(), true);
        Assertions.assertEquals(heladera.getEstadoActual(), EstadoHeladera.DISPONIBLE);
        Assertions.assertEquals(2, fallaTecnicaJuridico.getVisitasTecnicas().size());
        Assertions.assertTrue(fallaTecnicaJuridico.getSolucionado());
    }

    @Test
    //Se debe permitir que los técnicos sean avisados cuando corresponde y registren las visitas.
    public void TecnicoRecibeAvisoFallaTecnica() throws IOException {
        fallaTecnicaHumano = new FallaTecnica(heladera, colaboradorHumano);
        Assertions.assertEquals(2,fallaTecnicaHumano.avisarATecnico(listaTecnicos).size());
    }
}

