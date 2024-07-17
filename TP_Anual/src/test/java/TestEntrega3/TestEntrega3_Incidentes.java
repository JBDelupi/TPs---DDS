package TestEntrega3;

import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Incidentes.FallaTecnica;
import Models.Domain.Incidentes.Incidente;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Personas.Actores.Juridico;
import Models.Domain.Personas.Actores.Tecnico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestEntrega3_Incidentes {

    Colaborador colaboradorHumano;
    Colaborador colboradorJuridico;
    Incidente fallaTecnicaHumano;
    Incidente fallaTecnicaJuridico;
    Heladera heladera;
    Tecnico tecnico;

    //Se debe permitir que se puedan reportar incidentes
    @BeforeEach
    public void init(){
        colaboradorHumano = new Humano();
        colboradorJuridico = new Juridico();
        heladera.setActual(EstadoHeladera.DISPONIBLE);

    }

    @Test
    public void humanoReportaIncidente() throws IOException {
        fallaTecnicaHumano = new FallaTecnica(heladera, colaboradorHumano);
        Assertions.assertEquals(heladera.getActual(), EstadoHeladera.NO_DISPONIBLE);

    }

    @Test
    public void JuridicoReportaIncidente() throws IOException {
        fallaTecnicaJuridico = new FallaTecnica(heladera, colboradorJuridico);
        Assertions.assertEquals(heladera.getActual(), EstadoHeladera.NO_DISPONIBLE);
    }



    //Se debe permitir que los técnicos sean avisados cuando corresponde y registren las visitas.
}
