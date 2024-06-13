package Models.Domain.Personas;

import Controller.Actores.Rol;
import Models.Domain.FormasDeContribucion.OfrecerProducto;
import Models.Domain.TipoJuridico;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class Juridico extends Colaborador {
    private String razonSocial;
    private TipoJuridico tipoJuridico;
    private List<OfrecerProducto> productosOfrecidos;

    public Juridico(){
        this.setTipoRol(Rol.JURIDICO);
        this.productosOfrecidos = new ArrayList<>();
    }


}
