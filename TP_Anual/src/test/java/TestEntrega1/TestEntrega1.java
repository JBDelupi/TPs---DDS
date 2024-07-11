package TestEntrega1;

import Models.Domain.FormasDeContribucion.FactoryContribucion;
import Models.Domain.FormasDeContribucion.TipoDonacion;
import Models.Domain.Heladera;
import Models.Domain.Personas.Colaborador;
import Models.Domain.Personas.Humano;
import Models.Domain.Personas.Juridico;
import Models.Domain.TipoFrecuencia;
import Models.Domain.Vianda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestEntrega1 {

    // TEST UNA PERSONA HUMANA HACE DOS DONACIONES
    // TEST UNA PERSONA HUMANA INTENTA HACER UNA DONACION JURIDICA Y TIRA 403
    // TEST UNA PERSONA JURIDICA HACE UNA DONACION
    // TEST UNA PERSONA JURIDICA TRATANDO DE HACER UNA DONACION HUMANA Y TIRA 403

    Colaborador nahu ;
    Colaborador unicef;
    FactoryContribucion controllerNahu;
    FactoryContribucion controllerUnicef;
    @BeforeEach
    public void init(){
        nahu = new Humano();
        unicef = new Juridico();
        controllerNahu = new FactoryContribucion(nahu);
        controllerUnicef = new FactoryContribucion(unicef);
    }

    @Test
    public void unaPersonaHumanaHaceDosDonaciones() throws IOException{
        controllerNahu.create(TipoDonacion.DONACION_DINERO, 15.00, TipoFrecuencia.ANUAL);
        Heladera heladera = new Heladera();
        Vianda vianda = new Vianda();
        controllerNahu.create(TipoDonacion.DONACION_DE_VIANDA, vianda, heladera);

        Assertions.assertEquals( 2 ,nahu.getFormaDeContribucion().size());
    }

    @Test
    public void unaPersonaJuridicaHaceUnaDonacion() throws IOException{
        controllerUnicef.create(TipoDonacion.DONACION_DINERO, 15.00, TipoFrecuencia.ANUAL);

        Assertions.assertEquals( 1 ,unicef.getFormaDeContribucion().size());
    }
    @Test
    public void unaPersonaHumanaNoPuedeHacerUnaDonacionJuridica() {
        Assertions.assertThrows(FactoryContribucion.UnauthorizedAccessException.class, () -> {
            controllerNahu.create(TipoDonacion.HACERSE_CARGO_DE_HELADERA, null, null, null); // Aquí invocas el método dentro de la lambda
        });

    }


    @Test
    public void unaPersonaJuridicaNoPuedeHacerUnaDonacionHumana() throws IOException{
        Assertions.assertThrows(FactoryContribucion.UnauthorizedAccessException.class, () -> {
            controllerUnicef.create(TipoDonacion.DONACION_DE_VIANDA, null, null ,null ,null); // Aquí invocas el método dentro de la lambda
        });

    }

}
