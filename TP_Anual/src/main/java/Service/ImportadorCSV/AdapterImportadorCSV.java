package Service.ImportadorCSV;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;

public interface AdapterImportadorCSV {

    List<String[]> cargarArchivosCSV(String Link, String separador) throws IOException, CsvValidationException;
}
