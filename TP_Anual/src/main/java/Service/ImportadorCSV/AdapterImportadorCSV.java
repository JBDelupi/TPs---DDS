package Service.ImportadorCSV;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface AdapterImportadorCSV {

    List<String[]> cargarArchivosCSV(InputStream Link, String separador) throws IOException, CsvValidationException;
}
