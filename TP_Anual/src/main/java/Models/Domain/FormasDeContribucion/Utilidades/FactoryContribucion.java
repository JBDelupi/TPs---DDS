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
    private final Persona persona;

    public FactoryContribucion(Persona persona) {
        this.persona = persona;
    }

    // ------------------- MÉTODOS AUXILIARES -------------------------------------//
    private Colaborador obtenerColaborador() {
        if (!this.persona.checkRol(TipoRol.COLABORADOR)) {
            throw new Permisos.UnauthorizedAccessException("No tienes acceso");
        }
        return (Colaborador) persona.getRol(TipoRol.COLABORADOR);
    }

    private void validarPermisos(Class<?> tipoPersona, String mensaje) {
        if (persona.getClass().isAssignableFrom(tipoPersona)) {
            throw new Permisos.UnauthorizedAccessException(mensaje);
        }
    }

    private SolicitudDeApertura validarSolicitud(List<SolicitudDeApertura> solicitudes, TipoDonacion tipoDonacion) {
        SolicitudDeApertura solicitudDeApertura = procesarSolicitud(solicitudes, tipoDonacion);
        if (solicitudDeApertura == null) {
            throw new NoHaySolicitudExepction("No hay solicitud o expiró");
        }
        solicitudDeApertura.setRealizada(true);
        return solicitudDeApertura;
    }

    // ------------------- LO HACEN TODOS ----------------------------------------//
    private Contribucion donacionDeDinero(Object... context) {
        Colaborador colaborador = obtenerColaborador();
        Double monto = (Double) context[1];
        TipoFrecuencia tipoFrecuencia = (TipoFrecuencia) context[2];

        DonacionDeDineroBuilder builder = new DonacionDeDineroBuilder();
        Contribucion donacion = builder.monto(monto).frecuencia(tipoFrecuencia).construir();

        colaborador.agregarNuevaDonacion(donacion);
        return donacion;
    }

    private Contribucion donacionDeVianda(Object... context) {
        validarPermisos(Fisico.class, "No tienes acceso");

        Colaborador colaborador = obtenerColaborador();
        Vianda vianda = (Vianda) context[1];
        Heladera heladera = (Heladera) context[2];

        SolicitudDeApertura solicitud = validarSolicitud(colaborador.getTarjeta().getSolicitudesDeApertura(), (TipoDonacion) context[0]);
        colaborador.getTarjeta().agregarNuevoUso(heladera, TipoAccion.AGREGAR);
        heladera.agregarVianda(vianda);

        DonacionDeViandaBuilder builder = new DonacionDeViandaBuilder();
        Contribucion donacion = builder.heladera(heladera).vianda(vianda).construir();

        colaborador.agregarNuevaDonacion(donacion);
        return donacion;
    }

    private Contribucion distribucionDeVianda(Object... context) {
        validarPermisos(Fisico.class, "No tienes acceso");
        Colaborador colaborador = obtenerColaborador();

        Heladera heladeraOrigen = (Heladera) context[1];
        Heladera heladeraDestino = (Heladera) context[2];
        Integer cantidad = (Integer) context[3];
        String motivo = (String) context[4];

        validarSolicitud(colaborador.getTarjeta().getSolicitudesDeApertura(), (TipoDonacion) context[0]);
        colaborador.getTarjeta().agregarNuevoUso(heladeraDestino, TipoAccion.AGREGAR);

        for (int i = 0; i < cantidad; i++) {
            Vianda vianda = heladeraOrigen.obtenerVianda();
            heladeraDestino.agregarVianda(vianda);
        }

        DistribucionDeViandasBuilder builder = new DistribucionDeViandasBuilder();
        Contribucion donacion = builder.heladeraOrigen(heladeraOrigen).heladeraDestino(heladeraDestino).cantidadDeViandasAMover(cantidad).motivos(motivo).construir();

        colaborador.agregarNuevaDonacion(donacion);
        return donacion;
    }

    private Contribucion registrarTarjeta(Object... context) {
        validarPermisos(Fisico.class, "No tienes acceso");
        Colaborador colaborador = obtenerColaborador();

        String nombre = (String) context[1];
        Integer menoresACargo = (Integer) context[2];

        VulnerableBuilder vulnerableBuilder = new VulnerableBuilder();
        PersonaVulnerable vulnerable = vulnerableBuilder.menoresACargo(menoresACargo).construir();

        FisicoBuilder personaVulnerableBuilder = new FisicoBuilder();
        Fisico personaVulnerable = personaVulnerableBuilder.nombre(nombre).rol(vulnerable).construir();

        TarjetaAlimentar tarjeta = new TarjetaAlimentar(personaVulnerable);
        Contribucion donacion = new EntregaDeTarjeta(tarjeta);

        colaborador.agregarNuevaDonacion(donacion);
        return donacion;
    }

    private Contribucion hacerceCargoDeHeladera(Object... context) {
        validarPermisos(Juridico.class, "No tienes acceso");
        Colaborador colaborador = obtenerColaborador();

        String nombreCaracteristico = (String) context[1];
        Heladera heladera = (Heladera) context[3];

        HacerseCargoDeHeladeraBuilder builder = new HacerseCargoDeHeladeraBuilder();
        Contribucion donacion = builder.nombreCaracteristico(nombreCaracteristico).heladera(heladera).construir();

        colaborador.agregarNuevaDonacion(donacion);
        return donacion;
    }

    private Contribucion ofrecerProducto(Object... context) {
        validarPermisos(Juridico.class, "No tienes acceso");

        Colaborador colaborador = obtenerColaborador();

        Producto producto = (Producto) context[1];
        Double puntosNecesarios = (Double) context[2];
        Integer stock = (Integer) context[3];

        OfrecerProductoBuilder builder = new OfrecerProductoBuilder();
        Contribucion donacion = builder.producto(producto).stock(stock).puntosNecesarios(puntosNecesarios).construir();

        colaborador.agregarNuevaDonacion(donacion);
        return donacion;
    }

    // ------------------- FACTORY METHOD ----------------------------------------//
    public Contribucion factoryMethod(Object... context) {
        TipoDonacion tipo = (TipoDonacion) context[0];
        switch (tipo) {
            case DONACION_DINERO:
                return donacionDeDinero(context);
            case DONACION_DE_VIANDA:
                return donacionDeVianda(context);
            case HACERSE_CARGO_DE_HELADERA:
                return hacerceCargoDeHeladera(context);
            case DISTRIBUCION_VIANDAS:
                return distribucionDeVianda(context);
            case ENTREGA_TARJETAS:
                return registrarTarjeta(context);
            case OFRECER_PRODUCTO:
                return ofrecerProducto(context);
            default:
                throw new IllegalArgumentException("Tipo de donación no soportado");
        }
    }

    // ------------------- PROCESAR SOLICITUD -------------------------------------//
    public SolicitudDeApertura procesarSolicitud(List<SolicitudDeApertura> solicitudes, TipoDonacion tipoDonacion) {
        LocalDateTime ahora = LocalDateTime.now();
        return solicitudes.stream()
                .filter(solicitud -> solicitud.getAccion().equals(tipoDonacion) && !solicitud.getFechaLimite().isBefore(ahora) && !solicitud.getRealizada())
                .findFirst()
                .orElse(null);
    }
}
