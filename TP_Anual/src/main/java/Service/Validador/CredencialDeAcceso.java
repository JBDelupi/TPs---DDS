package Service.Validador;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Embeddable

public class CredencialDeAcceso {
    private String nombreUsuario;
    private String contrasenia;

    public CredencialDeAcceso(String nombreUsuario, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }


}

