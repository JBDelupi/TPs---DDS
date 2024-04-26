package Service.Validador;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class CredencialDeAcceso {
    private String nombreUsuario;
    private String contrasenia;
    private LocalDate fechaUltimoCambio;

    public CredencialDeAcceso(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public boolean esTiempoDeRotarContrasenia() {
      //  return ChronoUnit.MONTHS.between((this.fechaUltimoCambio), LocalDate.now()) >= 6;
        return false;
    }
}

