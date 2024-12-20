package Service.ImportadorCSV.Mappers;

import Models.Domain.Builder.UsuariosBuilder.ColaboradorBuilder;
import Models.Domain.Builder.UsuariosBuilder.FisicoBuilder;
import Models.Domain.FormasDeContribucion.ContribucionesGenerales.DonacionDeDinero;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.DistribucionDeViandas;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.DonacionDeVianda;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.EntregaDeTarjeta;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Service.ImportadorCSV.DTO.FactoryContribucionDTO;
import Service.ImportadorCSV.DTO.FisicoDTO;
import Service.ImportadorCSV.DTO.FormaColaboracionDTO;
import Service.Notificacion.Mensaje.MensajeBienvenida;
import Service.Validador.CredencialDeAcceso;
import Service.Validador.Encriptador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FisicoMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public  Fisico  toEntity(FisicoDTO dto) {

        FisicoBuilder fisicoBuilder = new FisicoBuilder();
        CredencialDeAcceso credencialDeAcceso = new CredencialDeAcceso(dto.getMail(), Encriptador.getInstancia().encriptarMD5(dto.getNumDocumento()) );

        Fisico fisico = fisicoBuilder
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .numeroDocumento(dto.getNumDocumento())
                .credencialDeAcceso(credencialDeAcceso)
                .tipoDocumento( this.TipoSeleccionado(dto.getTipoDocumento() ) )
                .construir();

        fisico.setCodigoDeNotificacion(dto.getMail());

        new MensajeBienvenida(fisico.getCodigoDeNotificacion(), fisico.getNumeroDocumento());


        ColaboradorBuilder colaboradorBuilder = new ColaboradorBuilder();
        Colaborador colaborador = colaboradorBuilder
                .construir(fisico);

        fisico.agregarRol(colaborador);

        Double puntaje = 0.0;

        for(FormaColaboracionDTO colaboracionDTO : dto.getColaboracionDTOS() ){
            Contribucion unaColaboracion = FactoryContribucionDTO.getContribucion(colaboracionDTO.getFormaDeColaboracion());

            LocalDate fechaDonacion = LocalDate.parse(colaboracionDTO.getFechaColaboracion(), formatter);
            unaColaboracion.setFechaDeDonacion(fechaDonacion);


            colaborador.agregarNuevaDonacion(unaColaboracion);
            puntaje += Double.parseDouble(colaboracionDTO.getCantidad());
        }

        colaborador.setPuntaje(puntaje);


        return fisico;


    }



    public FisicoDTO toDTO(Fisico fisico) {

        return null;
    }



    public TipoDeDocumento TipoSeleccionado(String tipoDocumento){
        TipoDeDocumento tipoDeDocumento = null;
        switch(tipoDocumento){
            case "DNI" :
                tipoDeDocumento = TipoDeDocumento.DNI; break;
            case "LE" :
                tipoDeDocumento = TipoDeDocumento.LIBRETA; break;
            case "LC" :
                tipoDeDocumento = TipoDeDocumento.PASAPORTE; break;
        }
        return tipoDeDocumento;
    }

    public Contribucion FactoryContribucion(String nombre) {
            Contribucion controller = null;
            switch (nombre) {
                case "DINERO":
                    controller = new DonacionDeDinero();
                    break;
                case "DONACION_VIANDAS":
                    controller = new DonacionDeVianda();
                    break;
                case "REDISTRIBUCION_VIANDAS":
                    controller = new DistribucionDeViandas();
                    break;
                case "ENTREGA_TARJETAS":
                    controller = new EntregaDeTarjeta();
                    break;
            }
            return controller;
    }



}
