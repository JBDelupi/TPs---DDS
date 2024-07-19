package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;
import Service.APIPuntos.AreaCobertura;
import Service.SistemaDeGeolocalizacion.SistemaGeolocalizacion;

import java.util.List;
import java.util.stream.Collectors;

public class PublicacionSufrioDesperfecto extends Publicacion {

    public PublicacionSufrioDesperfecto(){
        tipoPublicacion = TipoPublicacion.SUFRIO_DESPERFECTO;
    }

    public void heladerasSugeridas(Heladera h){
        AreaCobertura areaCobertura  = new AreaCobertura();
        areaCobertura.setRadio("5");
        areaCobertura.setCentro(h.getDireccion().getCentro());
        List<Heladera> heladerasSugeridas = SistemaGeolocalizacion.getInstance().generarHeladerasDisponibles(areaCobertura,h.getViandas().size());

        StringBuilder sb = new StringBuilder();
        for (Heladera f : heladerasSugeridas) {
            String s = "Una posible heladera:  " + f.getDireccion().getCalle();
            sb.append(s);
        }
        System.out.println("Test -> " + sb.toString());
        this.descripcion = sb.toString();
    }



    @Override
    public Boolean verificarCondicion(Publicacion publicacion, Heladera heladera){
        this.heladerasSugeridas(heladera);
        return  publicacion.getTipoPublicacion() == tipoPublicacion && heladera.getEstadoActual() == EstadoHeladera.NO_DISPONIBLE;
    }




}

