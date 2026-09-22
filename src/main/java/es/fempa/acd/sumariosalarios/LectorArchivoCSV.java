package es.fempa.acd.sumariosalarios;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivoCSV {

    public static List<Empleado> leerArchivoCSV(String rutaArchivo) throws IOException {
        List<Empleado> empleados = new ArrayList<>();

        // NOTA: EscritorArchivoCSV escribe el nombre completo en UN solo campo
        // (id, nombre, dni, sueldos...), así que aquí lo leemos igual: un campo por nombre.
        try (CSVParser csvParser = new CSVParser(new FileReader(rutaArchivo), CSVFormat.DEFAULT)) {
            for (CSVRecord record : csvParser) {
                int id = Integer.parseInt(record.get(0));
                String nombre = record.get(1);
                String dni = record.get(2);

                List<Double> sueldosMensuales = new ArrayList<>();
                for (int i = 3; i < record.size(); i++) {
                    sueldosMensuales.add(Double.parseDouble(record.get(i)));
                }

                Empleado empleado = new Empleado(id, nombre, dni, sueldosMensuales);
                empleados.add(empleado);
            }
        }

        return empleados;
    }
}
