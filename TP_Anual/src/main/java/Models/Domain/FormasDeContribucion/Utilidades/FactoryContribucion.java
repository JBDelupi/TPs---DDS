package Models.Domain.FormasDeContribucion.Utilidades;

import Controller.DTO.CrearContribucionDTO;
import Models.Domain.Builder.UsuariosBuilder.FisicoBuilder;
import Models.Domain.Builder.UsuariosBuilder.VulnerableBuilder;
import Models.Domain.Excepciones.NoHaySolicitudExepction;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.Utilidades.TipoFrecuencia;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Builder.ContribucionBuilder.*;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.EntregaDeTarjeta;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;
import Models.Domain.Personas.Actores.*;
import Models.Domain.Producto.Producto;
import Models.Domain.Producto.TipoRubro;
import Models.Domain.Tarjetas.SolicitudDeApertura;
import Models.Domain.Tarjetas.TarjetaAlimentar;
import Models.Domain.Tarjetas.TipoAccion;
import Models.Repository.Dao;
import Models.Repository.PseudoBaseDatosHeladera;
import Models.Repository.PseudoBaseDatosProductosOfrecidos;
import Service.Server.exceptions.UnauthorizedResponseException;
import lombok.Getter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.random.RandomGenerator;

@Getter
public class FactoryContribucion {
    private final Persona persona;


    public FactoryContribucion(Persona persona) {
        this.persona = persona;
    }

    // ------------------- MÉTODOS AUXILIARES -------------------------------------//
    private Colaborador obtenerColaborador() {
        if (!this.persona.checkRol(TipoRol.COLABORADOR)) {
            throw new UnauthorizedResponseException();
        }
        return (Colaborador) persona.getRol(TipoRol.COLABORADOR);
    }


    private void validarSolicitud(List<SolicitudDeApertura> solicitudes, TipoDonacion tipoDonacion) {
        SolicitudDeApertura solicitudDeApertura = procesarSolicitud(solicitudes, tipoDonacion);
        if (solicitudDeApertura == null) {
            throw new NoHaySolicitudExepction("No hay solicitud o expiró");
        }
        solicitudDeApertura.setRealizada(true);
    }

    public SolicitudDeApertura procesarSolicitud(List<SolicitudDeApertura> solicitudes, TipoDonacion tipoDonacion) {
        LocalDateTime ahora = LocalDateTime.now();
        return solicitudes.stream()
                .filter(solicitud -> solicitud.getAccion().equals(tipoDonacion) && !solicitud.getFechaLimite().isBefore(ahora) && !solicitud.getRealizada())
                .findFirst()
                .orElse(null);
    }


    // ------------------- CREAR CONTRIBUCIONES -----------------------------------//

    public void generarDonacion(CrearContribucionDTO crearContribucionDTO) {
        Colaborador colaborador = this.obtenerColaborador();
        colaborador.agregarNuevaDonacion(this.factoryMethod(crearContribucionDTO));

    }


    private Contribucion DonacionDeVianda(CrearContribucionDTO dto){

        String nombre = dto.getParams().get("nombre");
      //  DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      //  LocalDate fechaCaducidad = LocalDate.parse(dto.getParams().get("fechaDeCaducidad"), formato);
        int calorias = Integer.parseInt(dto.getParams().get("calorias"));
        int peso = Integer.parseInt(dto.getParams().get("peso"));

        String heladeraId = dto.getParams().get("heladera");
        Heladera heladera = PseudoBaseDatosHeladera.getInstance().getId(heladeraId);

        //validarSolicitud(obtenerColaborador().getTarjeta().getSolicitudesDeApertura(),TipoDonacion.DONACION_DE_VIANDA);

        Vianda vianda = new Vianda();
        vianda.setNombre(nombre);
      //  vianda.setFechaDeCaducidad(fechaCaducidad);
        vianda.setCalorias(calorias);
        vianda.setPeso(peso);
        vianda.setId(1);

        DonacionDeViandaBuilder builder = new DonacionDeViandaBuilder();
        Contribucion donacion = builder.heladera(heladera).vianda(vianda).construir();

        return donacion;
    }

    private Contribucion DonacionDeDinero(CrearContribucionDTO dto) {
        double monto = Double.parseDouble(dto.getParams().get("monto"));
        TipoFrecuencia frecuencia = TipoFrecuencia.valueOf(dto.getParams().get("frecuencia"));

        DonacionDeDineroBuilder builder = new DonacionDeDineroBuilder();
        Contribucion donacion = builder.monto(monto).frecuencia(frecuencia).construir();

        return donacion;
    }


    private Contribucion registrarTarjeta(CrearContribucionDTO dto) {

        String nombre = dto.getParams().get("nombreBeneficiario");
        int menoresACargo = 0;

        if(dto.getParams().get("menoresACargo") != null){
            menoresACargo = Integer.parseInt(dto.getParams().get("menoresACargo"));
        }

        VulnerableBuilder vulnerableBuilder = new VulnerableBuilder();
        PersonaVulnerable vulnerable = vulnerableBuilder.menoresACargo(menoresACargo).construir();


        FisicoBuilder personaVulnerableBuilder = new FisicoBuilder();
        Fisico personaVulnerable = personaVulnerableBuilder.nombre(nombre).rol(vulnerable).construir();

        TarjetaAlimentar tarjeta = new TarjetaAlimentar(personaVulnerable);
        Contribucion donacion = new EntregaDeTarjeta(tarjeta);

        return donacion;
    }

    // Distribución de Viandas
    private Contribucion distribucionDeViandas(CrearContribucionDTO dto) {

        String heladeraOrigenId = dto.getParams().get("heladeraOrigen");
        //Heladera heladeraOrigen = (Heladera) heladeraRepository.buscar(heladeraOrigenId);
        Heladera heladeraOrigen = PseudoBaseDatosHeladera.getInstance().getId(heladeraOrigenId);

        String heladeraDestinoId = dto.getParams().get("heladeraDestino");
        //Heladera heladeraDestino = (Heladera) heladeraRepository.buscar(heladeraDestinoId);
        Heladera heladeraDestino = PseudoBaseDatosHeladera.getInstance().getId(heladeraDestinoId);


        int cantidad = Integer.parseInt(dto.getParams().get("cantidadViandas"));
        String motivo = dto.getParams().get("motivo");

       // validarSolicitud(obtenerColaborador().getTarjeta().getSolicitudesDeApertura(), TipoDonacion.DONACION_DE_VIANDA);
        obtenerColaborador().getTarjeta().agregarNuevoUso(heladeraDestino, TipoAccion.AGREGAR);

        for (int i = 0; i < cantidad; i++) {
            Vianda vianda = heladeraOrigen.obtenerVianda();
            heladeraDestino.agregarVianda(vianda);
        }

        DistribucionDeViandasBuilder builder = new DistribucionDeViandasBuilder();
        Contribucion donacion = builder.heladeraOrigen(heladeraOrigen)
                .heladeraDestino(heladeraDestino)
                .cantidadDeViandasAMover(cantidad)
                .motivos(motivo)
                .construir();

        return donacion;
    }

    // Hacerse Cargo de una Heladera
    private Contribucion hacerseCargoDeHeladera(CrearContribucionDTO dto) {

        String nombreCaracteristico = dto.getParams().get("nombreCaracteristico");
        String id = dto.getParams().get("heladeraId");


        Heladera heladera = PseudoBaseDatosHeladera.getInstance().getId(id);
        heladera.setResponsable(this.persona);


        HacerseCargoDeHeladeraBuilder builder = new HacerseCargoDeHeladeraBuilder();
        Contribucion donacion = builder.nombreCaracteristico(nombreCaracteristico).heladera(heladera).construir();

        return donacion;
    }

    private Contribucion ofrecerProducto(CrearContribucionDTO dto) {

        String nombre = dto.getParams().get("nombreProducto");
        String imagen = dto.getParams().get("imagenProducto");
        String descripcion = dto.getParams().get("descripcionProducto");
        String rubroProducto = dto.getParams().get("rubroProducto");
        Double precio = Double.parseDouble(dto.getParams().get("precioProducto"));
        Integer stock = Integer.parseInt(dto.getParams().get("stock"));
       // double puntosNecesarios = Double.parseDouble(dto.getParams().get("puntosNecesarios"));

        TipoRubro tipoRubro = TipoRubro.valueOf(rubroProducto);

        Producto producto = new Producto(tipoRubro, nombre, imagen, descripcion);
        producto.setId(RandomGenerator.getDefault().nextInt());


        OfrecerProductoBuilder builder = new OfrecerProductoBuilder();
        Contribucion donacion = builder.producto(producto)
                .puntosNecesarios(3.00) // Hardcodeado agregar vista
                .stock(stock)
                .construir();

        PseudoBaseDatosProductosOfrecidos.getInstance().agregar((OfrecerProducto) donacion);

        return donacion;
    }

    // ------------------- FACTORY METHOD ----------------------------------------//
    public Contribucion factoryMethod(CrearContribucionDTO dto) {
        TipoDonacion tipo = TipoDonacion.valueOf(dto.getTipoDonacion());
        return switch (tipo) {
            case DONACION_DINERO -> DonacionDeDinero(dto);
            case DONACION_DE_VIANDA -> DonacionDeVianda(dto);
            case HACERSE_CARGO_DE_HELADERA -> hacerseCargoDeHeladera(dto);
            case DISTRIBUCION_VIANDAS -> distribucionDeViandas(dto);
            case ENTREGA_TARJETAS -> registrarTarjeta(dto);
            case OFRECER_PRODUCTO -> ofrecerProducto(dto);
            default -> throw new IllegalArgumentException("Tipo de donación no soportado");
        };
    }
}
