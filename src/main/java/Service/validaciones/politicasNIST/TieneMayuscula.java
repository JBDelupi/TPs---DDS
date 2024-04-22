package Service.validaciones.politicasNIST;

import Service.validaciones.CredencialDeAcceso;
import Service.validaciones.Validacion;

import java.util.List;

public class TieneMayuscula implements Validacion {
    @Override
    public boolean validar(CredencialDeAcceso credencialDeAcceso) {
        String contrasenia = credencialDeAcceso.getContrasenia();
        List<Character> chars = contrasenia.chars().mapToObj(e -> (char)e).toList();
        return chars.stream().anyMatch(Character::isUpperCase);
    }
}
