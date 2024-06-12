package Service.Converters;

import Models.Domain.FormasDeContribucion.*;
import Models.Domain.FormasDeContribucion.*;
import Models.Domain.Personas.Humano;
import Service.DTO.ColaboradorDTO;
import Service.DTO.FormaColaboracionDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HumanoService {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public Humano toEntity(ColaboradorDTO dto) {

        Humano colaborador = new Humano(dto.getNombre(), dto.getApellido());
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




    public ColaboradorDTO toDTO(Humano humano) {

        return null;
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
