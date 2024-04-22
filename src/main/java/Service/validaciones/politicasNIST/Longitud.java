package Service.validaciones.politicasNIST;

import Service.validaciones.CredencialDeAcceso;
import Service.validaciones.Validacion;

public class Longitud implements Validacion {

    public boolean validar(CredencialDeAcceso credencialDeAcceso){
        String contrasenia = credencialDeAcceso.getContrasenia();
        return contrasenia.length() >= 8 && contrasenia.length() <= 64;
    }
}
