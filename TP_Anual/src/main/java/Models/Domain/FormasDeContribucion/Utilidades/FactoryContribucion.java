package Models.Domain.FormasDeContribucion.Utilidades;

import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Builder.ContribucionBuilder.*;
import Models.Domain.Builder.UsuariosBuilder.FisicoBuilder;
import Models.Domain.Builder.UsuariosBuilder.VulnerableBuilder;
import Models.Domain.Excepciones.NoHaySolicitudExepction;
import Models.Domain.Excepciones.Permisos;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.EntregaDeTarjeta;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.Utilidades.TipoFrecuencia;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;
import Models.Domain.Personas.Actores.*;
import Models.Domain.Producto.Producto;
import Models.Domain.Tarjetas.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class FactoryContribucion {
    private Persona persona;

    public FactoryContribucion(Persona persona) {
        this.persona = persona;

    }

    // ------------------- LO HACEN TODOS ----------------------------------------//
    private Contribucion donacionDeDinero(Object ... Context){

        if(this.persona.checkRol(TipoRol.COLABORADOR)){
            throw new Permisos.UnauthorizedAccessException("No tienes Acceso");
        }
        Colaborador colaborador = (Colaborador) persona.getRol(TipoRol.COLABORADOR);


        Double monto = (Double) Context[1];
        TipoFrecuencia tipoFrecuencia = (TipoFrecuencia) Context[2];

        DonacionDeDineroBuilder builder = new DonacionDeDineroBuilder();

        Contribucion donacion = builder
                .monto(monto)
                .frecuencia(tipoFrecuencia)
                .construir();

        colaborador.agregarNuevaDonacion(donacion);

        return  donacion;
    }


    private Contribucion donacionDeVianda(Object ... Context){

        if(this.persona.checkRol(TipoRol.COLABORADOR) && persona instanceof Fisico){
            throw new Permisos.UnauthorizedAccessException("No tienes Acceso");
        }

        Colaborador colaborador = (Colaborador) persona.getRol(TipoRol.COLABORADOR);


        Vianda vianda = (Vianda) Context[1];
        Heladera heladera = (Heladera) Context[2];


        TarjetaAccesos tarjeta = colaborador.getTarjeta();

        SolicitudDeApertura solicitudDeApertura;
        solicitudDeApertura = this.procesarSolicitud(tarjeta.getSolicitudesDeApertura(), (TipoDonacion) Context[0]);

        if(solicitudDeApertura == null){
            throw new NoHaySolicitudExepction("No hay solicitud o expiro");
        }

        solicitudDeApertura.setRealizada(true);
        tarjeta.agregarNuevoUso(heladera, TipoAccion.AGREGAR);

        heladera.agregarVianda(vianda);
        DonacionDeViandaBuilder builder = new DonacionDeViandaBuilder();
        Contribucion donacion = builder
                .heladera(heladera)
                .vianda(vianda)
                .construir();


        colaborador.agregarNuevaDonacion(donacion);

        return donacion;

    }

    private Contribucion distribucionDeVianda(Object ... Context){

        if(this.persona.checkRol(TipoRol.COLABORADOR) && persona instanceof Fisico){
            throw new Permisos.UnauthorizedAccessException("No tienes Acceso");
        }

        Colaborador colaborador = (Colaborador) persona.getRol(TipoRol.COLABORADOR);


        Heladera heladeraOrigen = (Heladera) Context[1];
        Heladera heladeraDestino = (Heladera) Context[2];
        Integer cantidad = (Integer) Context[3];
        String motivo = (String) Context[4];


        TarjetaAccesos tarjeta = colaborador.getTarjeta();

        SolicitudDeApertura solicitudDeApertura;
        solicitudDeApertura = this.procesarSolicitud(tarjeta.getSolicitudesDeApertura(), (TipoDonacion) Context[0]);

        if(solicitudDeApertura == null){
            throw new NoHaySolicitudExepction("No hay solicitud o expiro");
        }

        solicitudDeApertura.setRealizada(true);
        tarjeta.agregarNuevoUso(heladeraDestino, TipoAccion.AGREGAR);


        for(int i = 0; i < cantidad; i++){
            Vianda vianda = heladeraOrigen.obtenerVianda();
            heladeraDestino.agregarVianda(vianda);
        }


        DistribucionDeViandasBuilder builder = new DistribucionDeViandasBuilder();

        Contribucion donacion = builder
                .heladeraOrigen(heladeraOrigen)
                .heladeraDestino(heladeraDestino)
                .cantidadDeViandasAMover(cantidad)
                .motivos(motivo)
                .construir();

        colaborador.agregarNuevaDonacion(donacion);

        return donacion;
    }

    private Contribucion registrarTarjeta(Object ... Context){

        if(this.persona.checkRol(TipoRol.COLABORADOR) && persona instanceof Fisico){
            throw new Permisos.UnauthorizedAccessException("No tienes Acceso");
        }

        Colaborador colaborador = (Colaborador) persona.getRol(TipoRol.COLABORADOR);


        String nombre = (String) Context[1];
        Integer menoresACargo = (Integer) Context[2];

        VulnerableBuilder vulnerableBuilder = new VulnerableBuilder();

        PersonaVulnerable vulnerable =
                vulnerableBuilder
                        .menoresACargo(menoresACargo)
                        .construir();

        FisicoBuilder personaVulnerable = new FisicoBuilder();
        Fisico persona = personaVulnerable.nombre(nombre).rol(vulnerable).construir();

        TarjetaAlimentar tarjeta = new TarjetaAlimentar(persona);

        Contribucion donacion = new EntregaDeTarjeta(tarjeta);

        colaborador.agregarNuevaDonacion(donacion);

        return donacion;
    }



    private Contribucion hacerceCargoDeHeladera(Object ... Context){

        if(this.persona.checkRol(TipoRol.COLABORADOR) && persona instanceof Juridico){
            throw new Permisos.UnauthorizedAccessException("No tienes Acceso");
        }

        Colaborador colaborador = (Colaborador) persona.getRol(TipoRol.COLABORADOR);

        String nombreCaracteristico = (String) Context[1];
        Heladera heladera = (Heladera) Context[3];

        HacerseCargoDeHeladeraBuilder builder = new HacerseCargoDeHeladeraBuilder();

        Contribucion donacion = builder
                .nombreCaracteristico(nombreCaracteristico)
                .heladera(heladera)
                .construir();

        colaborador.agregarNuevaDonacion(donacion);

        return donacion;
    }

    private Contribucion ofrecerProducto(Object ... Context){

        if(this.persona.checkRol(TipoRol.COLABORADOR) && persona instanceof Juridico){
            throw new Permisos.UnauthorizedAccessException("No tienes Acceso");
        }

        Colaborador colaborador = (Colaborador) persona.getRol(TipoRol.COLABORADOR);

        Producto producto = (Producto) Context[1];
        Double puntosNecesarios = (Double) Context[2];
        Integer stock = (Integer) Context[3];


        OfrecerProductoBuilder builder = new OfrecerProductoBuilder();

        Contribucion donacion = builder
                .producto(producto)
                .stock(stock)
                .puntosNecesarios(puntosNecesarios)
                .construir();

        colaborador.agregarNuevaDonacion(donacion);

        return donacion;
    }


    public Contribucion factoryMethod(Object ... Context){
        Contribucion contribucion = null;
        switch( (TipoDonacion) Context[0] ){
            case DONACION_DINERO: contribucion = this.donacionDeDinero( Context ); break;
            case DONACION_DE_VIANDA: contribucion = this.donacionDeVianda( Context ); break;
            case HACERSE_CARGO_DE_HELADERA: contribucion = this.hacerceCargoDeHeladera( Context ); break;
            case DISTRIBUCION_VIANDAS: contribucion = this.distribucionDeVianda( Context ); break;
            case ENTREGA_TARJETAS : contribucion = this.registrarTarjeta( Context ); break;
            case OFRECER_PRODUCTO: contribucion = this.ofrecerProducto( Context ); break;
        }
        return contribucion;
    }

    public SolicitudDeApertura procesarSolicitud(List<SolicitudDeApertura> solicitudes, TipoDonacion contexto) {
        LocalDateTime ahora = LocalDateTime.now();
        SolicitudDeApertura solicitudDeApertura = null;

        for (SolicitudDeApertura solicitud : solicitudes) {
            if (solicitud.getAccion().equals(contexto) && !solicitud.getFechaLimite().isBefore(ahora) && !solicitud.getRealizada()) {
                solicitudDeApertura = solicitud;
                break;
            }
        }
        return  solicitudDeApertura;
    }

}
