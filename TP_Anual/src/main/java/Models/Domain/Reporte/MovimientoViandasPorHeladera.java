package Models.Domain.Reporte;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Repository.RepoHeladera;
import Models.Repository.RepoPersona;
import Service.TareaDiferida.ChromeTask;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Entity
@DiscriminatorValue("MovimientoViandasPorHeladera")
@NoArgsConstructor
public class MovimientoViandasPorHeladera  extends TemplateReporte{

    public  void obtenerListado() {
        RepoHeladera repoHeladera = new RepoHeladera(Heladera.class);
        List<Heladera> heladeras = repoHeladera.buscarTodos();

        for (Heladera heladera : heladeras) {
            String direccion = heladera.getDireccion().getCalle() + " " +
                    heladera.getDireccion().getNumero() + ", " +
                    heladera.getDireccion().getLocalidad();
            String cantidadDeViandasDepositadas = String.valueOf(heladera.getCantidadDeviandasDepositadas());
            String cantidadDeViandasRetiradas = String.valueOf(heladera.getCantidadDeviandasRetiradas());
            heladera.reestablecerViandasRetiradas();
            heladera.reestablecerViandasDepositadas();

            // Agrega cada fila como una lista de strings
            getItems().add(Arrays.asList(direccion, cantidadDeViandasDepositadas, cantidadDeViandasRetiradas));
        }
        repoHeladera.agregar(this);

    }


}


