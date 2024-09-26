package TestEntrega1;

import Controller.ContribucionController;
import Models.Domain.Builder.UsuariosBuilder.ColaboradorBuilder;
import Models.Domain.Excepciones.Permisos;
import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Juridico;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.Utilidades.TipoFrecuencia;
import Models.Domain.Heladera.Vianda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
/*
public class TestEntrega1 {

    // TEST UNA PERSONA HUMANA HACE DOS DONACIONES
    // TEST UNA PERSONA HUMANA INTENTA HACER UNA DONACION JURIDICA Y TIRA 403
    // TEST UNA PERSONA JURIDICA HACE UNA DONACION
    // TEST UNA PERSONA JURIDICA TRATANDO DE HACER UNA DONACION HUMANA Y TIRA 403

    Fisico nahu ;
    Juridico unicef;
    ContribucionController controllerNahu;
    ContribucionController controllerUnicef;
    @BeforeEach
    public void init(){
        nahu = new Fisico();

        unicef = new Juridico();


        controllerNahu = new ContribucionController(nahu);
        controllerUnicef = new ContribucionController(unicef);


    }

    @Test
    public void unaPersonaHumanaHaceDosDonaciones() throws IOException{
        controllerNahu.save(TipoDonacion.DONACION_DINERO, 15.00, TipoFrecuencia.ANUAL);
        Heladera heladera = new Heladera();
        Vianda vianda = new Vianda();
        controllerNahu.save(TipoDonacion.DONACION_DE_VIANDA, vianda, heladera);

        Assertions.assertEquals( 2 ,nahu.getRol().getContribuciones().size());
    }

    @Test
    public void unaPersonaJuridicaHaceUnaDonacion() throws IOException{
        controllerUnicef.save(TipoDonacion.DONACION_DINERO, 15.00, TipoFrecuencia.ANUAL);

        Assertions.assertEquals( 1 ,unicef.getRol().getContribuciones().size());
    }

    @Test
    public void unaPersonaHumanaNoPuedeHacerUnaDonacionJuridica() {

       Assertions.assertThrows(Permisos.UnauthorizedAccessException.class, () -> {
            controllerNahu.save(TipoDonacion.HACERSE_CARGO_DE_HELADERA, null, null, null); // Aquí invocas el método dentro de la lambda
        });

    }


    @Test
    public void unaPersonaJuridicaNoPuedeHacerUnaDonacionHumana() throws IOException{

        Assertions.assertThrows(Permisos.UnauthorizedAccessException.class, () -> {
            controllerUnicef.save(TipoDonacion.DONACION_DE_VIANDA, null, null ,null ,null); // Aquí invocas el método dentro de la lambda
        });

    }

}
*/