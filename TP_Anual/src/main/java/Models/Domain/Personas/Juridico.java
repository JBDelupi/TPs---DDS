package Models.Domain.Personas;

import Controller.Actores.Rol;
import Models.Domain.Direccion;
import Models.Domain.FormasDeContribucion.Producto;
import Models.Domain.MedioDeNotificacion;
import Models.Domain.TipoJuridico;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class Juridico extends Colaborador {
    private String razonSocial;
    private TipoJuridico tipoJuridico;
    private List<Producto> productosOfrecidos;

    public Juridico(){
        this.setTipoRol(Rol.JURIDICO);
        this.productosOfrecidos = new ArrayList<>();
    }


}
