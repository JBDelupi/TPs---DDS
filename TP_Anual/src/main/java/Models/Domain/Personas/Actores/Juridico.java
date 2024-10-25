package Models.Domain.Personas.Actores;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Personas.Utilidades.TipoRolNegocio;
import Models.Domain.Personas.Utilidades.TipoJuridico;
import Service.Notificacion.Correo.CorreoAdapter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter

@Entity
@Table(name = "Juridico")
@DiscriminatorValue("Juridico")
public class Juridico extends Persona {
    @Column(name = "razon_social")
    private String razonSocial;

    @Enumerated(EnumType.STRING)
    private TipoJuridico tipoJuridico;

    public Juridico(){
        this.setMedioDeNotificacion(new CorreoAdapter());
    }


}
