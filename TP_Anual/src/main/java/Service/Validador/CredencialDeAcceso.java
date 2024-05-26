package Service.Validador;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class CredencialDeAcceso {
    private String nombreUsuario;
    private String contrasenia;

    public CredencialDeAcceso(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


}

