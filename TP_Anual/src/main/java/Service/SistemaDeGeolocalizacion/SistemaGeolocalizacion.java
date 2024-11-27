package Service.SistemaDeGeolocalizacion;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.Tecnico;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Repository.RepoHeladera;
import Models.Repository.RepoPersona;
import Service.APIPuntos.AreaCobertura;
import Service.APIPuntos.Punto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SistemaGeolocalizacion {

    private static final SistemaGeolocalizacion INSTANCE = new SistemaGeolocalizacion();

    private final RepoPersona repo = new RepoPersona();

    // Constructor privado para Singleton
    private SistemaGeolocalizacion() {}

    public static SistemaGeolocalizacion getInstance() {
        return INSTANCE;
    }


    /**
     * Calcula la distancia entre dos puntos usando sus coordenadas de latitud y longitud.
     *
     * @param punto1 El primer punto
     * @param punto2 El segundo punto
     * @return La distancia entre los puntos
     */
    public double distanciaEntrePuntos(Punto punto1, Punto punto2) {
        try {
            double dy = Double.parseDouble(punto1.getLatitud()) - Double.parseDouble(punto2.getLatitud());
            double dx = Double.parseDouble(punto1.getLongitud()) - Double.parseDouble(punto2.getLongitud());
            return Math.sqrt(dx * dx + dy * dy);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Las coordenadas son inválidas", e);
        }
    }

    /**
     * Verifica si un punto está dentro de un área de cobertura.
     *
     * @param area  El área de cobertura
     * @param punto El punto a verificar
     * @return true si el punto está dentro del área, false si no
     */
    public boolean estaDentroDe(AreaCobertura area, Punto punto) {
        double radio = Double.parseDouble(area.getRadio());
        double distancia = distanciaEntrePuntos(punto, area.getCentro());
        return distancia <= radio;
    }

    /**
     * Encuentra la persona más cercana a un punto dado, filtrando solo a los técnicos.
     *
     * @param punto El punto de referencia
     * @return La persona más cercana
     */
    public Persona masCercanoAPunto(Punto punto) {
        List<Persona> tecnicos = repo.personasRol(TipoRol.TECNICO);
        tecnicos.stream()
                .filter(persona -> estaDentroDe(((Tecnico) persona.getRol(TipoRol.TECNICO)).getArea(), punto)) // Verifica si está dentro del área
                .toList();

        if (tecnicos.isEmpty()) {
            return null; // Si no hay técnicos disponibles, devolvemos null
        }

        Persona masCercano = tecnicos.get(0);
        double distanciaMinima = distanciaEntrePuntos(
                ((Tecnico) masCercano.getRol(TipoRol.TECNICO)).getArea().getCentro(), punto);

        for (Persona tecnico : tecnicos) {
            double distancia = distanciaEntrePuntos(
                    ((Tecnico) tecnico.getRol(TipoRol.TECNICO)).getArea().getCentro(), punto);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                masCercano = tecnico;
            }
        }

        return masCercano;
    }
}
