package Models.Domain.FormasDeContribucion.Utilidades;

import Controller.DTO.CrearContribucionDTO;
import Models.Domain.Builder.UsuariosBuilder.FisicoBuilder;
import Models.Domain.Builder.UsuariosBuilder.VulnerableBuilder;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.Utilidades.TipoFrecuencia;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Builder.ContribucionBuilder.*;
import Models.Domain.Excepciones.Permisos;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.EntregaDeTarjeta;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;
import Models.Domain.Personas.Actores.*;
import Models.Domain.Producto.Producto;
import Models.Domain.Tarjetas.TarjetaAlimentar;
import Models.Repository.Dao;
import lombok.Getter;

@Getter
public class FactoryContribucion {
    private final Persona persona;
    private final Dao viandaRepository;
    private final Dao heladeraRepository;

    public FactoryContribucion(Persona persona, Dao viandaRepository, Dao heladeraRepository) {
        this.persona = persona;
        this.viandaRepository = viandaRepository;
        this.heladeraRepository = heladeraRepository;
    }

    // ------------------- MÉTODOS AUXILIARES -------------------------------------//
    private Colaborador obtenerColaborador() {
        if (!this.persona.checkRol(TipoRol.COLABORADOR)) {
            throw new Permisos.UnauthorizedAccessException("No tienes acceso");
        }
        return (Colaborador) persona.getRol(TipoRol.COLABORADOR);
    }

    // ------------------- CREAR CONTRIBUCIONES -----------------------------------//

    public void generarDonacion(CrearContribucionDTO crearContribucionDTO) {
        Colaborador colaborador = this.obtenerColaborador();
        colaborador.agregarNuevaDonacion(this.factoryMethod(crearContribucionDTO));
        // agregar base de datos
    }


    // Donación de Vianda
    private Contribucion DonacionDeVianda(CrearContribucionDTO dto) {
        int viandaId = Integer.parseInt(dto.getParams().get("viandaId"));
        Vianda vianda = (Vianda) viandaRepository.buscar(viandaId);

        int heladeraId = Integer.parseInt(dto.getParams().get("heladeraId"));
        Heladera heladera = (Heladera) heladeraRepository.buscar(heladeraId);

        DonacionDeViandaBuilder builder = new DonacionDeViandaBuilder();
        Contribucion donacion = builder.heladera(heladera).vianda(vianda).construir();

        obtenerColaborador().agregarNuevaDonacion(donacion);
        return donacion;
    }

    // Donación de Dinero
    private Contribucion DonacionDeDinero(CrearContribucionDTO dto) {
        double monto = Double.parseDouble(dto.getParams().get("monto"));
        TipoFrecuencia frecuencia = TipoFrecuencia.valueOf(dto.getParams().get("frecuencia"));

       // int donanteId = Integer.parseInt(dto.getParams().get("donanteId"));

        DonacionDeDineroBuilder builder = new DonacionDeDineroBuilder();
        Contribucion donacion = builder.monto(monto).frecuencia(frecuencia).construir();

        return donacion;
    }


    // Registro de Tarjeta
    private Contribucion registrarTarjeta(CrearContribucionDTO dto) {
        String nombre = dto.getParams().get("nombreBeneficiario");
        int menoresACargo = Integer.parseInt(dto.getParams().get("menoresACargo"));

        VulnerableBuilder vulnerableBuilder = new VulnerableBuilder();
        PersonaVulnerable vulnerable = vulnerableBuilder.menoresACargo(menoresACargo).construir();

        FisicoBuilder personaVulnerableBuilder = new FisicoBuilder();
        Fisico personaVulnerable = personaVulnerableBuilder.nombre(nombre).rol(vulnerable).construir();

        TarjetaAlimentar tarjeta = new TarjetaAlimentar(personaVulnerable);
        Contribucion donacion = new EntregaDeTarjeta(tarjeta);

        obtenerColaborador().agregarNuevaDonacion(donacion);
        return donacion;
    }

    // Distribución de Viandas
    private Contribucion crearDistribucionDeViandas(CrearContribucionDTO dto) {
        int heladeraOrigenId = Integer.parseInt(dto.getParams().get("heladeraOrigenId"));
        Heladera heladeraOrigen = (Heladera) heladeraRepository.buscar(heladeraOrigenId);

        int heladeraDestinoId = Integer.parseInt(dto.getParams().get("heladeraDestinoId"));
        Heladera heladeraDestino = (Heladera) heladeraRepository.buscar(heladeraDestinoId);

        int cantidad = Integer.parseInt(dto.getParams().get("cantidadViandas"));
        String motivo = dto.getParams().get("motivo");

        DistribucionDeViandasBuilder builder = new DistribucionDeViandasBuilder();
        Contribucion donacion = builder.heladeraOrigen(heladeraOrigen)
                .heladeraDestino(heladeraDestino)
                .cantidadDeViandasAMover(cantidad)
                .motivos(motivo)
                .construir();

        obtenerColaborador().agregarNuevaDonacion(donacion);
        return donacion;
    }

    // Hacerse Cargo de una Heladera
    private Contribucion hacerseCargoDeHeladera(CrearContribucionDTO dto) {
        String nombreCaracteristico = dto.getParams().get("nombreCaracteristico");

        int heladeraId = Integer.parseInt(dto.getParams().get("heladeraId"));
        Heladera heladera = (Heladera) heladeraRepository.buscar(heladeraId);

        HacerseCargoDeHeladeraBuilder builder = new HacerseCargoDeHeladeraBuilder();
        Contribucion donacion = builder.nombreCaracteristico(nombreCaracteristico).heladera(heladera).construir();

        obtenerColaborador().agregarNuevaDonacion(donacion);
        return donacion;
    }

    // Ofrecer Producto
    private Contribucion ofrecerProducto(CrearContribucionDTO dto) {
        int productoId = Integer.parseInt(dto.getParams().get("productoId"));
        Producto producto = (Producto) viandaRepository.buscar(productoId);

        double puntosNecesarios = Double.parseDouble(dto.getParams().get("puntosNecesarios"));
        int stock = Integer.parseInt(dto.getParams().get("stock"));

        OfrecerProductoBuilder builder = new OfrecerProductoBuilder();
        Contribucion donacion = builder.producto(producto)
                .puntosNecesarios(puntosNecesarios)
                .stock(stock)
                .construir();

        obtenerColaborador().agregarNuevaDonacion(donacion);
        return donacion;
    }

    // ------------------- FACTORY METHOD ----------------------------------------//
    public Contribucion factoryMethod(CrearContribucionDTO dto) {
        TipoDonacion tipo = TipoDonacion.valueOf(dto.getTipoDonacion());
        return switch (tipo) {
            case DONACION_DINERO -> DonacionDeDinero(dto);
            case DONACION_DE_VIANDA -> DonacionDeVianda(dto);
            case HACERSE_CARGO_DE_HELADERA -> hacerseCargoDeHeladera(dto);
            case DISTRIBUCION_VIANDAS -> crearDistribucionDeViandas(dto);
            case ENTREGA_TARJETAS -> registrarTarjeta(dto);
            case OFRECER_PRODUCTO -> ofrecerProducto(dto);
            default -> throw new IllegalArgumentException("Tipo de donación no soportado");
        };
    }
}
