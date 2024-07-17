package TestEntrega3;

import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Tarjetas.RegistroDeUso;
import Models.Domain.Tarjetas.Tarjeta;
import Models.Domain.Tarjetas.TarjetaAccesosAHeladera;
import Models.Domain.Tarjetas.TipoAccion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestEntrega3_Tarjeta {

    Humano h;
    Tarjeta tarjeta;

    @BeforeEach
    public void init(){
        h = new Humano();
        tarjeta = new TarjetaAccesosAHeladera(h);
    }

    @Test
    public void tarjetaAsignarAColaborador() throws IOException {
        //Se debe permitir asignar una tarjeta a los colaboradores

        Assertions.assertEquals(tarjeta.getTitular(), h);
    }

    @Test
    public void tarjetaRegisraUso() throws IOException {
        //La tarjeta lleva un registro de los usos (asegurar trazabilidad)
        RegistroDeUso unUso = new RegistroDeUso(null, null, TipoAccion.QUITAR);

        tarjeta.agregarNuevoUso(unUso);

        Assertions.assertTrue(tarjeta.getUsos().contains(unUso));

    }


}
