package Service.validaciones.politicasNIST;

import Service.validaciones.CredencialDeAcceso;
import Service.validaciones.Validacion;

public class Rotacion implements Validacion {

    public boolean validar(CredencialDeAcceso credencialDeAcceso){
        return !credencialDeAcceso.esTiempoDeRotarContrasenia();
    }
}
