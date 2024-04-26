package Service.Validador.politicasNIST;

import Service.Validador.CredencialDeAcceso;
import Service.Validador.Validacion;

public class Longitud implements Validacion {

    public boolean validar(CredencialDeAcceso credencialDeAcceso){
        String contrasenia = credencialDeAcceso.getContrasenia();
        return contrasenia.length() >= 8 && contrasenia.length() <= 64;
    }
}
