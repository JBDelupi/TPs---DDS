package Models.Domain.Personas.Actores;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Personas.Utilidades.TipoRolNegocio;
import Models.Domain.Personas.Utilidades.TipoJuridico;
import Service.Notificacion.Correo.CorreoAdapter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter
public class Juridico extends Persona {
    private String razonSocial;
    private TipoJuridico tipoJuridico;
    private List<OfrecerProducto> productosOfrecidos;

    public Juridico(){
        this.productosOfrecidos = new ArrayList<>();
        this.setMedioDeNotificacion(new CorreoAdapter());
    }


}
