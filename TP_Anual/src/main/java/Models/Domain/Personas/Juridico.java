package Models.Domain.Personas;

import Controller.Actores.Rol;
import Models.Domain.Direccion;
import Models.Domain.FormasDeContribucion.Producto;
import Models.Domain.MedioDeNotificacion;
import Models.Domain.TipoJuridico;

import java.util.ArrayList;
import java.util.List;

public class Juridico extends Colaborador {
    private String razonSocial;
    private TipoJuridico tipoJuridico;
    private List<Producto> productosOfrecidos;

    public Juridico(String razonSocial, TipoJuridico tipoJuridico, Direccion direccion, MedioDeNotificacion medioDeNotificacion){
        this.tipoJuridico = tipoJuridico;
        this.razonSocial = razonSocial;
        this.setDireccion(direccion);
        this.setMedioDeNotificacion(medioDeNotificacion);
        this.setTipoRol(Rol.JURIDICO);
        this.productosOfrecidos = new ArrayList<>();
    }
}
