package Service.ImportadorCSV;

import Service.ImportadorCSV.DTO.FisicoDTO;
import Service.ImportadorCSV.DTO.FormaColaboracionDTO;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class ImportadorCSV {
    private AdapterImportadorCSV adapterImportadorCSV;
    private Set<FisicoDTO> colaboradoresDTO;
    private static ImportadorCSV instacia;


    private ImportadorCSV(InputStream input, String separador) throws CsvValidationException, IOException {
        this.adapterImportadorCSV = new AdapterLectorArchivoCSV();
        this.cargarDatosColaborador(input,separador);

    }

    public static ImportadorCSV getInstance(InputStream input , String separador) throws CsvValidationException, IOException {
        if(instacia==null){
            instacia = new ImportadorCSV(input, separador);
        }
        return instacia;
    }





    private void cargarDatosColaborador(InputStream input,String separador) throws CsvValidationException, IOException {
        List<String[]> datosCSV = adapterImportadorCSV.cargarArchivosCSV(input, separador);
        this.ToDtoColaborador(datosCSV);
    }

    private boolean esElMismoDocumento(String unDocumento, String otroDocumento){
        return unDocumento.equals((String) otroDocumento);
    }

    private boolean esElMismoTipoDocumento(String unTipoDocumento, String otroTipoDocumento){
        return unTipoDocumento.equals((String) otroTipoDocumento);
    }

    private boolean esColaboradorRepetido(String TipoDocumento, String numeroDocumento){
        return colaboradoresDTO.stream().anyMatch( unColaborador -> this.esElMismoDocumento(unColaborador.getNumDocumento(), numeroDocumento) &&  this.esElMismoTipoDocumento(TipoDocumento, unColaborador.getTipoDocumento()));
    }

    private FisicoDTO getColaboradorDTO(String TipoDocumento, String numeroDocumento){
        return  colaboradoresDTO.stream().filter(unColaborador ->this.esElMismoDocumento(unColaborador.getNumDocumento(), numeroDocumento) &&  this.esElMismoTipoDocumento(TipoDocumento, unColaborador.getTipoDocumento())).findFirst().get();
    }


    private void ToDtoColaborador(List<String[]> list) {
        this.colaboradoresDTO = new HashSet<>();

        for (String[] linea : list) {

            FormaColaboracionDTO unaColaboracion = new FormaColaboracionDTO();
            FisicoDTO unColaborador = null;

            if(! this.esColaboradorRepetido(linea[0],linea[1]) ) {
                unColaborador = new FisicoDTO();
                unColaborador.setTipoDocumento(linea[0]);
                unColaborador.setNumDocumento(linea[1]);
                unColaborador.setNombre(linea[2]);
                unColaborador.setApellido(linea[3]);
                unColaborador.setMail(linea[4]);
                colaboradoresDTO.add(unColaborador);
            } else{
                unColaborador = this.getColaboradorDTO(linea[0],linea[1]);
            }
            unaColaboracion.setFechaColaboracion(linea[5]);
            unaColaboracion.setFormaDeColaboracion(linea[6]);
            unaColaboracion.setCantidad(linea[7]);
            unColaborador.agregarColaboracion(unaColaboracion);



        }

    }


}
