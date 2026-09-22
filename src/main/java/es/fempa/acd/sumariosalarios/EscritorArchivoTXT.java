package es.fempa.acd.sumariosalarios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class EscritorArchivoTXT {

    // Escribe el archivo de SALIDA (procesado): incluye el sueldo medio de cada empleado
    public static void escribirArchivoTXT(String rutaArchivo, List<Empleado> empleados) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Empleado empleado : empleados) {
                // Formatear la salida como id nombre dni sueldo medio, sueldos mensuales
                bw.write(empleado.getId() + " " + empleado.getNombre() + " " + empleado.getDni() + " " +
                        "| Salario Máximo: " + String.format(Locale.US, "%.2f", empleado.obtenerSueldoMaximo()) + "€ " +
                        "| Salario Minimo: " +String.format(Locale.US, "%.2f", empleado.obtenerSueldoMinimo()) + "€ " +
                        "| Media Salario: " +String.format(Locale.US, "%.2f", empleado.calcularSueldoMedio()) + "€");

                /*for (Double sueldo : empleado.getSueldosMensuales()) {
                    bw.write(" " + sueldo);
                }*/

                bw.newLine(); // Saltar a la siguiente línea
            }
        }
    }

    // Escribe el dataset ORIGINAL: id, nombre, dni y sueldos
    public static void escribirDatosGenerados(String rutaArchivo, List<Empleado> empleados) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Empleado empleado : empleados) {
                // Escribir id, nombre, dni y sueldos maximo, minimo y promedio de salarios
                bw.write(empleado.getId() + " " + empleado.getNombre() + " " + empleado.getDni());

                for (Double sueldo : empleado.getSueldosMensuales()) {
                    bw.write(" " + sueldo);
                }

                bw.newLine();
            }
        }
    }
}
