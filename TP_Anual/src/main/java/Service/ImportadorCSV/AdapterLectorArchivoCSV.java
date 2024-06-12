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

    public List<String[]> cargarArchivosCSV(String Link, String separador) throws IOException, CsvValidationException {
        List<String[]> list = new ArrayList<>();
        InputStream inputStream = new FileInputStream(Link);
        CSVReader csvReader = new CSVReader((new InputStreamReader(inputStream)));
        String[] fila = null;
        String[] parsear;
        while( (fila = csvReader.readNext()) != null ) {
            parsear = fila[0].split(separador);
            list.add(parsear);

        }
        return list;
    }


}