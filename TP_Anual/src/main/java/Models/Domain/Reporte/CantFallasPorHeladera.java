package Models.Domain.Reporte;

import Models.Domain.Heladera.Heladera;
import Models.Repository.RepoHeladera;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Arrays;

@Entity
@DiscriminatorValue("CantFallasPorHeladera")
@NoArgsConstructor
public class CantFallasPorHeladera extends TemplateReporte{

    public  void obtenerListado() {
        RepoHeladera repoHeladera = new RepoHeladera(Heladera.class);
        List<Heladera> heladeras = repoHeladera.buscarTodos();

        for (Heladera heladera : heladeras) {
            // Concatenar la dirección
            String direccion = heladera.getDireccion().getCalle() + " " +
                    heladera.getDireccion().getNumero() + ", " +
                    heladera.getDireccion().getLocalidad();

            // Obtener cantidad de fallas
            String cantidadDeFallas = String.valueOf(heladera.getCantidadDeFallas());

            // Restablecer las fallas para la siguiente ejecución
            heladera.reestablecerFallas();

            // Agregar a la lista de items en el formato adecuado
            getItems().add(Arrays.asList(direccion, cantidadDeFallas));
        }
        repoHeladera.agregar(this);
    }
}
