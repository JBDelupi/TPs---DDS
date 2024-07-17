package Models.Domain.Tarjetas;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.HacerseCargoDeHeladera;
import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.Personas.Actores.Humano;

import java.util.List;

public class TarjetaAccesosAHeladera extends Tarjeta{
    private List<SolicitudDeApertura> solicitudesDeApertura;
}
