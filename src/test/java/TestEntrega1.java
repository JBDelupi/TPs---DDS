import Controller.DonacionController;
import Models.Heladera;
import Models.Personas.Colaborador;
import Models.Personas.Humano;
import Models.Personas.Juridico;
import Models.TipoFrecuencia;
import Models.TipoJuridico;
import Models.Vianda;
import Service.validaciones.CredencialDeAcceso;
import Service.validaciones.Validador;
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
    DonacionController controllerNahu;
    DonacionController controllerUnicef;
    @BeforeEach
    public void init(){
        nahu = new Humano("Nahuel","Gimenez");
        unicef = new Juridico("Unicef", TipoJuridico.ONG, null, null);
        controllerNahu = new DonacionController(nahu);
        controllerUnicef = new DonacionController(unicef);
    }

    @Test
    public void unaPersonaHumanaHaceDosDonaciones() throws IOException{
        controllerNahu.create("1", 15.00, TipoFrecuencia.ANUAL);
        Heladera heladera = new Heladera();
        Vianda vianda = new Vianda();
        controllerNahu.create("2", vianda, heladera);

        Assertions.assertEquals( 2 ,nahu.getFormaDeContribucion().size());
    }

    @Test
    public void unaPersonaJuridicaHaceUnaDonacion() throws IOException{
        controllerUnicef.create("1", 15.00, TipoFrecuencia.ANUAL);

        Assertions.assertEquals( 1 ,unicef.getFormaDeContribucion().size());
    }
    @Test
    public void unaPersonaHumanaNoPuedeHacerUnaDonacionJuridica() {
        Assertions.assertThrows(DonacionController.UnauthorizedAccessException.class, () -> {
            controllerNahu.create("3", null, null, null); // Aquí invocas el método dentro de la lambda
        });

    }


    @Test
    public void unaPersonaJuridicaNoPuedeHacerUnaDonacionHumana() throws IOException{
        Assertions.assertThrows(DonacionController.UnauthorizedAccessException.class, () -> {
            controllerUnicef.create("2", null, null ,null ,null); // Aquí invocas el método dentro de la lambda
        });

    }

}
