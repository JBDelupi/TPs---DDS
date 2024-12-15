package Service.ImportadorCSV;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AdapterLectorArchivoCSV implements  AdapterImportadorCSV{

    public List<String[]> cargarArchivosCSV(InputStream input, String separador) throws IOException, CsvValidationException {
        List<String[]> list = new ArrayList<>();
        CSVReader csvReader = new CSVReader((new InputStreamReader(input)));
        String[] fila = null;
        String[] parsear;
        while( (fila = csvReader.readNext()) != null ) {
            parsear = fila[0].split(separador);
            list.add(parsear);

        }
        return list;
    }


}