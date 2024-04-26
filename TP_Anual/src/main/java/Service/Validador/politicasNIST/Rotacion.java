package Service.Validador.politicasNIST;

import Service.Validador.CredencialDeAcceso;
import Service.Validador.Validacion;

public class Rotacion implements Validacion {

    public boolean validar(CredencialDeAcceso credencialDeAcceso){
        return !credencialDeAcceso.esTiempoDeRotarContrasenia();
    }
}
