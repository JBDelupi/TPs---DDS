package Service.Validador;

import java.util.ArrayList;
import java.util.List;

public class EsDebil implements Validacion{
    private List<String> topPeoresContrasenias;

    public EsDebil(){
        topPeoresContrasenias = new ArrayList<>();
    }

    public void setContrasenias(List<String> contrasenias){
        topPeoresContrasenias = contrasenias;
    }

    @Override
    public boolean validar(CredencialDeAcceso credencialDeAcceso){
        return topPeoresContrasenias.stream().noneMatch(s -> s.equals(credencialDeAcceso.getContrasenia()));
    }
}
