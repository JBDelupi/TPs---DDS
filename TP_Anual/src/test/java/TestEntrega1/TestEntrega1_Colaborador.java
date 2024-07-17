package TestEntrega1;

import Controller.HumanoController;
import Controller.JuridicoController;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.DatosPersonales.TipoDeDocumento;
import Models.Domain.Personas.Utilidades.TipoJuridico;
import Models.Repository.RepoColaboradores;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestEntrega1_Colaborador {
/*
    JuridicoController controllerUno;
    HumanoController controllerDos;
    Colaborador lucas;
    @BeforeEach
    public void init(){
        lucas = new Humano();
        controllerUno = new JuridicoController(lucas);
        controllerDos = new HumanoController(lucas);
    }

    @Test
    public void registrarPersonaJuridica(){
        controllerUno.save("Caritas", TipoJuridico.ONG,"Caritas@gmail.com");

        Assertions.assertEquals( 1 , RepoColaboradores.getInstance().getColaboradorlist().size() );

    }

    @Test
    public void registrarPersonaHumana(){
        controllerDos.save("Lucas", "Iturrioz", LocalDate.now(), "Lucas@gmail.com", "4107732", TipoDeDocumento.DNI);

        Assertions.assertTrue(! RepoColaboradores.getInstance().getColaboradorlist().isEmpty() );

    }

*/

}
