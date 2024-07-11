package Models.Domain.Personas.Actores;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Personas.Utilidades.TipoRolNegocio;
import Models.Domain.Personas.Utilidades.TipoJuridico;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class Juridico extends Colaborador {
    private String razonSocial;
    private TipoJuridico tipoJuridico;
    private List<OfrecerProducto> productosOfrecidos;

    public Juridico(){
        this.setRolNegocio(TipoRolNegocio.JURIDICO);
        this.productosOfrecidos = new ArrayList<>();
    }


}
