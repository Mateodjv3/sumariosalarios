package es.fempa.acd.sumariosalarios;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EscritorArchivoJSON {

    // Escribe el archivo de SALIDA (procesado): incluye el sueldo medio (getSueldoMedio)
    public static void escribirArchivoJSON(String rutaArchivo, List<Empleado> empleados) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Convertir cada Empleado a un resumen (sin los 12 sueldos: solo maximo, minimo y medio)
        List<EmpleadoResumen> resumenes = new ArrayList<>();
        for (Empleado empleado : empleados) {
            resumenes.add(new EmpleadoResumen(
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getDni(),
                    empleado.calcularSueldoMedio(),
                    empleado.obtenerSueldoMaximo(),
                    empleado.obtenerSueldoMinimo()));
        }

        EmpleadosResumenWrapper wrapper = new EmpleadosResumenWrapper();
        wrapper.setEmpleados(resumenes);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(rutaArchivo), wrapper);

        /*// Crear un objeto auxiliar para envolver la lista de empleados
        EmpleadosWrapper empleadosWrapper = new EmpleadosWrapper();
        empleadosWrapper.setEmpleados(empleados);

        // Escribir el JSON en el archivo
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(rutaArchivo), empleadosWrapper);*/
    }

    // Escribe el dataset ORIGINAL (sin procesar)
    public static void escribirDatosGenerados(String rutaArchivo, List<Empleado> empleados) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        EmpleadosWrapper empleadosWrapper = new EmpleadosWrapper();
        empleadosWrapper.setEmpleados(empleados);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(rutaArchivo), empleadosWrapper);
    }

    // Clase auxiliar para mapear el JSON
    public static class EmpleadosWrapper {
        private List<Empleado> empleados;

        public List<Empleado> getEmpleados() {
            return empleados;
        }

        public void setEmpleados(List<Empleado> empleados) {
            this.empleados = empleados;
        }
    }

    // Clase auxiliar para mapear el JSON de salida (resumen)
    public static class EmpleadosResumenWrapper {
        private List<EmpleadoResumen> empleados;

        public List<EmpleadoResumen> getEmpleados() {
            return empleados;
        }

        public void setEmpleados(List<EmpleadoResumen> empleados) {
            this.empleados = empleados;
        }
    }
}
