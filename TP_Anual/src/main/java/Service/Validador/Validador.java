package Service.Validador;

import Service.Server.exceptions.UserAlreadyExistsException;
import Service.Validador.politicasNIST.Longitud;
import Service.Validador.politicasNIST.TieneCaracterEspecial;
import Service.Validador.politicasNIST.TieneMayuscula;
import Service.Validador.politicasNIST.TieneNumero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validador {
    private static Validador instancia;
    private final List<Validacion> validaciones;

    // Constructor privado: inicializa las validaciones una sola vez
    private Validador() {
        validaciones = Arrays.asList(
                new EsDebil(),
                new Longitud(),
                new TieneCaracterEspecial(),
                new TieneNumero(),
                new TieneMayuscula()
        );
    }

    public static Validador getInstancia() {
        if (instancia == null) {
            synchronized (Validador.class) {
                if (instancia == null) {
                    instancia = new Validador();
                }
            }
        }
        return instancia;
    }

    public void validar(CredencialDeAcceso credencialDeAcceso) {
        if (!validaciones.stream().allMatch(v -> v.validar(credencialDeAcceso))) {
            throw new UserAlreadyExistsException();
        }
    }
}

