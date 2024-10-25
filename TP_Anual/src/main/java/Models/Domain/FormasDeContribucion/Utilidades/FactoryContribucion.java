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
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Domain.Producto.Producto;
import Models.Domain.Producto.TipoRubro;
import Models.Domain.Tarjetas.SolicitudDeApertura;
import Models.Domain.Tarjetas.TarjetaAlimentar;
import Models.Domain.Tarjetas.TipoAccion;
import Models.Repository.EntityManager.EntityManagerHelper;
import Models.Repository.RepoContribucion;
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

    private static FactoryContribucion instancia;
    private Persona persona;
    private RepoContribucion repo = new RepoContribucion(Colaborador.class);

    public static FactoryContribucion getInstance(){
        if(instancia == null ){
            instancia = new FactoryContribucion();
        }
        return instancia;
    }

    private FactoryContribucion() {

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



    private void DonacionDeVianda(CrearContribucionDTO dto){

        String nombre = dto.getParams().get("nombre");
        LocalDate fechaCaducidad = LocalDate.parse(dto.getParams().get("fechaDeCaducidad"));
        int calorias = Integer.parseInt(dto.getParams().get("calorias"));
        int peso = Integer.parseInt(dto.getParams().get("peso"));

        String heladeraId = dto.getParams().get("heladera");
        Heladera heladera = EntityManagerHelper.getEntityManager().find(Heladera.class, heladeraId);

        //validarSolicitud(obtenerColaborador().getTarjeta().getSolicitudesDeApertura(),TipoDonacion.DONACION_DE_VIANDA);

        Vianda vianda = new Vianda();
        vianda.setNombre(nombre);
        vianda.setFechaDeCaducidad(fechaCaducidad);
        vianda.setCalorias(calorias);
        vianda.setPeso(peso);

        DonacionDeViandaBuilder builder = new DonacionDeViandaBuilder();
        Contribucion donacion = builder.heladera(heladera).vianda(vianda).construir();

        Colaborador colaborador = this.obtenerColaborador();
        colaborador.agregarNuevaDonacion(donacion);

        repo.modificar(colaborador);
    }

    private void DonacionDeDinero(CrearContribucionDTO dto) {

        double monto = Double.parseDouble(dto.getParams().get("monto"));
        TipoFrecuencia frecuencia = TipoFrecuencia.valueOf(dto.getParams().get("frecuencia"));

        DonacionDeDineroBuilder builder = new DonacionDeDineroBuilder();
        Contribucion donacion = builder.monto(monto).frecuencia(frecuencia).construir();

        Colaborador colaborador = this.obtenerColaborador();
        colaborador.agregarNuevaDonacion(donacion);

        repo.modificar(colaborador);


    }


    private void registrarTarjeta(CrearContribucionDTO dto) {

        Integer cantidadMenores = 0;
        String calle = dto.getParams().get("calle");
        String numero = dto.getParams().get("numero");
        String localidad = dto.getParams().get("localidad");
        String documento  = dto.getParams().get("documento");
        TipoDeDocumento tipoDocumento = TipoDeDocumento.valueOf(dto.getParams().get("tipoDocumento"));
        LocalDate fechaNacimiento = LocalDate.parse( dto.getParams().get("fechaNacimiento"));
        String apellido = dto.getParams().get("apellido");
        String nombre = dto.getParams().get("nombre");


        if(dto.getParams().get("menoresACargo").equals("si")){
            cantidadMenores = Integer.parseInt(dto.getParams().get("cantidadMenores"));
        }

        VulnerableBuilder vulnerableBuilder = new VulnerableBuilder();
        PersonaVulnerable rolVulnerable = vulnerableBuilder.menoresACargo(cantidadMenores).construir();

        Direccion direccion = new Direccion();
        direccion.setNumero(numero);
        direccion.setLocalidad(localidad);
        direccion.setCalle(calle);

        FisicoBuilder personaVulnerableBuilder = new FisicoBuilder();
        Fisico persona = personaVulnerableBuilder
                .nombre(nombre)
                .numeroDocumento(documento)
                .tipoDocumento(tipoDocumento)
                .apellido(apellido)
                .direccion(direccion)
                .fechaNacimiento(fechaNacimiento)
                .rol(rolVulnerable)
                .construir();

        TarjetaAlimentar tarjetaAlimentar = new TarjetaAlimentar(persona);
        Contribucion donacion = new EntregaDeTarjeta(tarjetaAlimentar);
        Colaborador colaborador = this.obtenerColaborador();
        colaborador.agregarNuevaDonacion(donacion);

        repo.agregar(persona);
        repo.modificar(colaborador);

    }

    // Distribución de Viandas
    private void distribucionDeViandas(CrearContribucionDTO dto) {

        String heladeraOrigenId = dto.getParams().get("heladeraOrigen");
        Heladera heladeraOrigen = EntityManagerHelper.getEntityManager().find(Heladera.class,heladeraOrigenId);

        String heladeraDestinoId = dto.getParams().get("heladeraDestino");
        Heladera heladeraDestino = EntityManagerHelper.getEntityManager().find(Heladera.class,heladeraDestinoId);

        int cantidad = Integer.parseInt(dto.getParams().get("cantidadViandas"));
        String motivo = dto.getParams().get("motivo");

       // validarSolicitud(obtenerColaborador().getTarjeta().getSolicitudesDeApertura(), TipoDonacion.DONACION_DE_VIANDA);
        Colaborador colaborador = this.obtenerColaborador();

        colaborador.getTarjeta().agregarNuevoUso(heladeraDestino, TipoAccion.AGREGAR);

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

        colaborador.agregarNuevaDonacion(donacion);

        repo.modificar(colaborador);

    }

    // Hacerse Cargo de una Heladera
    private void hacerseCargoDeHeladera(CrearContribucionDTO dto) {

        String nombreCaracteristico = dto.getParams().get("nombreCaracteristico");
        String id = dto.getParams().get("heladeraId");

        Heladera heladera = EntityManagerHelper.getEntityManager().find(Heladera.class,id);
        heladera.setResponsable(this.persona);


        HacerseCargoDeHeladeraBuilder builder = new HacerseCargoDeHeladeraBuilder();
        Contribucion donacion = builder.nombreCaracteristico(nombreCaracteristico).heladera(heladera).construir();


        Colaborador colaborador = this.obtenerColaborador();
        colaborador.agregarNuevaDonacion(donacion);

        repo.modificar(colaborador);

    }

    private void ofrecerProducto(CrearContribucionDTO dto) {

        String nombre = dto.getParams().get("nombreProducto");
        String imagen = dto.getParams().get("imagenProducto");
        String descripcion = dto.getParams().get("descripcionProducto");
        String rubroProducto = dto.getParams().get("rubroProducto");
        Integer stock = Integer.parseInt(dto.getParams().get("stock"));
        double puntosNecesarios = Double.parseDouble(dto.getParams().get("puntosNecesarios"));

        TipoRubro tipoRubro = TipoRubro.valueOf(rubroProducto);

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setImagen(imagen);
        producto.setDescripcion(descripcion);
        producto.setRubro(tipoRubro);

        OfrecerProductoBuilder builder = new OfrecerProductoBuilder();
        Contribucion donacion = builder.producto(producto)
                .puntosNecesarios(puntosNecesarios)
                .stock(stock)
                .construir();


        Colaborador colaborador = this.obtenerColaborador();
        colaborador.agregarNuevaDonacion(donacion);

        repo.modificar(colaborador);


    }

    // ------------------- FACTORY METHOD ----------------------------------------//
    public void factoryMethod(String id, CrearContribucionDTO dto) {
        TipoDonacion tipo = TipoDonacion.valueOf(dto.getTipoDonacion());
        this.persona = EntityManagerHelper.getEntityManager().find(Persona.class,id);
        switch (tipo) {
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
