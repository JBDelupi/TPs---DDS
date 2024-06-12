package TestEntrega1;

import Controller.HumanoController;
import Controller.JuridicoController;
import Controller.Controller;
import Models.Domain.TipoDeDocumento;
import Models.Domain.TipoJuridico;
import Models.Repository.RepoColaboradores;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestEntrega1_Colaborador {

    Controller controllerUno;
    Controller controllerDos;
    @BeforeEach
    public void init(){
        controllerUno = new JuridicoController();
        controllerDos = new HumanoController();
    }

    @Test
    public void registrarPersonaJuridica(){
        controllerUno.create("Caritas", TipoJuridico.ONG,"Caritas@gmail.com");

        Assertions.assertEquals( 1 , RepoColaboradores.getInstance().getColaboradorlist().size() );

    }

    @Test
    public void registrarPersonaHumana(){
        controllerDos.create("Lucas", "Iturrioz", LocalDate.now(), "Lucas@gmail.com", "4107732", TipoDeDocumento.DNI);

        Assertions.assertTrue(! RepoColaboradores.getInstance().getColaboradorlist().isEmpty() );

    }



}
