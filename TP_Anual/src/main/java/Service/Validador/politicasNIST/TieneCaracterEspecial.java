package Service.Validador.politicasNIST;

import Service.Validador.CredencialDeAcceso;
import Service.Validador.Validacion;

import java.util.List;

public class TieneCaracterEspecial implements Validacion {
    @Override
    public boolean validar(CredencialDeAcceso credencialDeAcceso) {
        String contrasenia = credencialDeAcceso.getContrasenia();
        List<Character> chars = contrasenia.chars().mapToObj(e -> (char)e).toList();
        return chars.stream().anyMatch(caracter -> !Character.isLetterOrDigit(caracter));
    }
}
