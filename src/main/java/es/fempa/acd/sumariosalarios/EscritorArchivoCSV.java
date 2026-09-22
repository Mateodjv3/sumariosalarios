package es.fempa.acd.sumariosalarios;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EscritorArchivoCSV {

    // Escribe el archivo de SALIDA (procesado): incluye el sueldo medio de cada empleado
    public static void escribirArchivoCSV(String rutaArchivo, List<Empleado> empleados) throws IOException {
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(rutaArchivo), CSVFormat.DEFAULT)) {
            for (Empleado empleado : empleados) {
                // Escribir id, nombre, dni, sueldo medio y sueldos mensuales
                csvPrinter.print(empleado.getId());
                csvPrinter.print(empleado.getNombre());
                csvPrinter.print(empleado.getDni());

                csvPrinter.print("| Salario Máximo: " + empleado.obtenerSueldoMaximo() + "€");
                csvPrinter.print("| Salario Mínimo: " + empleado.obtenerSueldoMinimo() + "€");
                csvPrinter.print("| Media Salario: " + empleado.calcularSueldoMedio() + "€");

                /*for (Double sueldo : empleado.getSueldosMensuales()) {
                    csvPrinter.print(sueldo);
                }*/

                csvPrinter.println();
            }
        }
    }

    // Escribe el dataset ORIGINAL (sin procesar): id, nombre, dni y sueldos mensuales
    public static void escribirDatosGenerados(String rutaArchivo, List<Empleado> empleados) throws IOException {
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(rutaArchivo), CSVFormat.DEFAULT)) {
            for (Empleado empleado : empleados) {
                csvPrinter.print(empleado.getId());
                csvPrinter.print(empleado.getNombre());
                csvPrinter.print(empleado.getDni());

                for (Double sueldo : empleado.getSueldosMensuales()) {
                    csvPrinter.print(sueldo);
                }

                csvPrinter.println();
            }
        }
    }
}
