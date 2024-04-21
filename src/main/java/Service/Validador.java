package domain.validaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validador {
    private List<Validacion> validaciones;

    public Validador() {
        validaciones = new ArrayList<>();
    }

    public void setValidaciones(Validacion ... validacion){
        validaciones = Arrays.asList(validacion);
    }

    public boolean validar(CredencialDeAcceso credencialDeAcceso) {
        return validaciones.stream().allMatch(v -> v.validar(credencialDeAcceso));
    }
}
