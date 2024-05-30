package Service.ImportadorCSV;

import Service.ImportadorCSV.DTO.ColaboradorDTO;
import Service.ImportadorCSV.DTO.FormaColaboracionDTO;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class ImportadorCSV {
    private AdapterImportadorCSV adapterImportadorCSV;
    private Set<ColaboradorDTO> colaboradoresDTO;
    private List<FormaColaboracionDTO> colaboracionesDTO;
    private String URL;

    public ImportadorCSV(String URL) throws CsvValidationException, IOException {
        this.URL = URL;
        this.colaboracionesDTO = new ArrayList<>();
        this.colaboradoresDTO = new HashSet<>();
        this.adapterImportadorCSV = new AdapterLectorArchivoCSV();
        this.cargarDatosColaborador();

    }

    private void cargarDatosColaborador() throws CsvValidationException, IOException {
        List<String[]> datosCSV = adapterImportadorCSV.cargarArchivosCSV(URL);
        this.ToDtoColaborador(datosCSV);
    }

    private boolean esColaboradorRepetido(String TipoDocumento, String numeroDocumento){
        return colaboradoresDTO.stream().anyMatch(f->f.getTipoDocumento().equals((String) TipoDocumento )  && f.getNumDocumento().equals((String)numeroDocumento ));
    }

    private void ToDtoColaborador(List<String[]> list) {

        for (String[] linea : list) {
            ColaboradorDTO unColaborador = new ColaboradorDTO();
            FormaColaboracionDTO unaForma = new FormaColaboracionDTO();

            if(! this.esColaboradorRepetido(linea[0],linea[1]) ) {
                unColaborador.setTipoDocumento(linea[0]);
                unColaborador.setNumDocumento(linea[1]);
                unColaborador.setNombre(linea[2]);
                unColaborador.setApellido(linea[3]);
                unColaborador.setMail(linea[4]);
                colaboradoresDTO.add(unColaborador);
            }
            unaForma.setTipoDocumento(linea[0]);
            unaForma.setNumDocumento(linea[1]);
            unaForma.setFechaColaboracion(linea[5]);
            unaForma.setFormaDeColaboracion(linea[6]);
            unaForma.setCantidad(linea[7]);

            colaboracionesDTO.add(unaForma);

        }

    }


}
