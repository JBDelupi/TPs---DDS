package Service.Mappers;

import Models.Domain.Builder.UsuariosBuilder.HumanoBuilder;
import Models.Domain.FormasDeContribucion.*;
import Models.Domain.Personas.Humano;
import Models.Domain.TipoDeDocumento;
import Service.DTO.HumanoDTO;
import Service.DTO.FormaColaboracionDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HumanoMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public Humano toEntity(HumanoDTO dto) {

        HumanoBuilder humanoBuilder = new HumanoBuilder();

        Humano colaborador = humanoBuilder
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .numeroDocumento(dto.getNumDocumento())
                .tipoDocumento( this.TipoSeleccionado(dto.getTipoDocumento() ) )
                .construir();

        Double puntaje = 0.0;

        for(FormaColaboracionDTO colaboracionDTO : dto.getColaboracionDTOS() ){
            FormaDeContribucion unaColaboracion = this.FactoryContribucion(colaboracionDTO.getFormaDeColaboracion());

            LocalDate fechaDonacion = LocalDate.parse(colaboracionDTO.getFechaColaboracion(), formatter);
            unaColaboracion.setFechaDeDonacion(fechaDonacion);

            colaborador.agregarNuevaDonacion(unaColaboracion);
            puntaje += Double.parseDouble(colaboracionDTO.getCantidad());
        }


        colaborador.setPuntaje(puntaje);

        return colaborador;


    }



    public HumanoDTO toDTO(Humano humano) {

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

    public FormaDeContribucion FactoryContribucion(String nombre) {
            FormaDeContribucion controller = null;
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
