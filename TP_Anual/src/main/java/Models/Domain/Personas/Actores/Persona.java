package Models.Domain.Personas.Actores;


import Controller.Actores.RolUsuario;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Repository.Converter.MedioDeNotificacionAtributeConverter;
import Service.Notificacion.Mensaje.Mensaje;
import Service.Notificacion.Mensaje.MensajeBienvenida;
import Service.Notificacion.Notificacion;
import Service.Validador.CredencialDeAcceso;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "Persona")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")

public abstract class Persona  {
    @Embedded
    private CredencialDeAcceso credencialDeAcceso;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Direccion direccion;

    @Convert(converter = MedioDeNotificacionAtributeConverter.class)
    private Notificacion medioDeNotificacion;

    @Column(name = "codigo_de_notificacion")
    private String codigoDeNotificacion; // DONDE SE VA A NOTIFICAR, MISMO CORREO, EL MISMO NUMERO TELEFONO, O USUARIO DE TELEGRAM

    @Column(name="correo_electronico")
    private String correElectronico;


    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona") // CLAVE FORANEA
    private List<Rol> roles;

    @Enumerated(EnumType.STRING)
    private RolUsuario tipoUsuario;


    public Persona() {
        this.roles = new ArrayList<>();
    }

    public void notify(Mensaje publicacion){
        medioDeNotificacion.Notificar(publicacion);
    }

    public boolean checkRol(TipoRol rol){
       return roles.stream().anyMatch(unRol->unRol.getTipo().equals(rol));
    }

    public Rol getRol(TipoRol rol){
        return roles.stream().filter(unRol->unRol.getTipo().equals(rol)).findFirst().orElse(null);
    }

    public void agregarRol(Rol rol){
        roles.add(rol);
    }

}
