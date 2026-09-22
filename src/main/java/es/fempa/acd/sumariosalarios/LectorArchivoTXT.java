package es.fempa.acd.sumariosalarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivoTXT {

    public static List<Empleado> leerArchivoTXT(String rutaArchivo) throws IOException {
        List<Empleado> empleados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }
                // Dividir la línea en los datos correspondientes
                String[] datos = linea.trim().split("\\s+");

                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1] + " " + datos[2];
                String dni = datos[3];
                List<Double> sueldosMensuales = new ArrayList<>();

                // Leer los 12 sueldos
                for (int i = 4; i < datos.length; i++) {
                    sueldosMensuales.add(Double.parseDouble(datos[i]));
                }

                // Crear un objeto Empleado y agregarlo a la lista
                Empleado empleado = new Empleado(id, nombre, dni, sueldosMensuales);
                empleados.add(empleado);
            }
        }

        return empleados;
    }
}
