package es.fempa.acd.sumariosalarios;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EscritorArchivoJSON {

    // Escribe el archivo de SALIDA (procesado): incluye el sueldo medio (getSueldoMedio)
    public static void escribirArchivoJSON(String rutaArchivo, List<Empleado> empleados) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Crear un objeto auxiliar para envolver la lista de empleados
        EmpleadosWrapper empleadosWrapper = new EmpleadosWrapper();
        empleadosWrapper.setEmpleados(empleados);

        // Escribir el JSON en el archivo
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(rutaArchivo), empleadosWrapper);
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
}
