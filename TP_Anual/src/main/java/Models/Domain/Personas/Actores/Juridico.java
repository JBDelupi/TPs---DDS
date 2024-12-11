package Models.Domain.Personas.Actores;


import Models.Domain.Personas.Utilidades.TipoJuridico;
import Service.Notificacion.Correo.CorreoAdapter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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
