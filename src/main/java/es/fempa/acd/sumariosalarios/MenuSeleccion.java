package es.fempa.acd.sumariosalarios;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MenuSeleccion {

    public static void mostrarMenu() throws Exception {
        // Crear una instancia de Configuracion para obtener las rutas de los archivos
        Configuracion configuracion = new Configuracion();

        // Generar el dataset y escribir los archivos de datos (uno por cada formato)
        List<Empleado> empleados = DataSetEmpleados.generarEmpleados();
        generarArchivosDeDatos(empleados);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el tipo de archivo a procesar:");
        System.out.println("1. TXT");
        System.out.println("2. CSV");
        System.out.println("3. Excel");
        System.out.println("4. XML");
        System.out.println("5. JSON");
        System.out.println("0. Salir");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea nueva después del número

        String rutaArchivo = null;
        List<Empleado> empleadosLeidos = null;

        switch (opcion) {
            case 1:
                rutaArchivo = configuracion.getProperty("ruta.txt");
                empleadosLeidos = LectorArchivoTXT.leerArchivoTXT(rutaArchivo);
                break;
            case 2:
                rutaArchivo = configuracion.getProperty("ruta.csv");
                empleadosLeidos = LectorArchivoCSV.leerArchivoCSV(rutaArchivo);
                break;
            case 3:
                rutaArchivo = configuracion.getProperty("ruta.xlsx");
                empleadosLeidos = LectorArchivoExcel.leerArchivoExcel(rutaArchivo);
                break;
            case 4:
                rutaArchivo = configuracion.getProperty("ruta.xml");
                empleadosLeidos = LectorArchivoXML.leerArchivoXML(rutaArchivo);
                break;
            case 5:
                rutaArchivo = configuracion.getProperty("ruta.json");
                empleadosLeidos = LectorArchivoJSON.leerArchivoJSON(rutaArchivo);
                break;
            case 0:
                System.out.println("Saliendo del programa...");
                return;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        if (empleadosLeidos != null) {
            mostrarSumario(empleadosLeidos);
            System.out.println("Introduzca la ruta para el archivo de salida:");
            String rutaSalida = scanner.nextLine();

            // Guardar el archivo en el mismo formato que el archivo de entrada
            switch (opcion) {
                case 1:
                    EscritorArchivoTXT.escribirArchivoTXT(rutaSalida, empleadosLeidos);
                    break;
                case 2:
                    EscritorArchivoCSV.escribirArchivoCSV(rutaSalida, empleadosLeidos);
                    break;
                case 3:
                    EscritorArchivoExcel.escribirArchivoExcel(rutaSalida, empleadosLeidos);
                    break;
                case 4:
                    EscritorArchivoXML.escribirArchivoXML(rutaSalida, empleadosLeidos);
                    break;
                case 5:
                    EscritorArchivoJSON.escribirArchivoJSON(rutaSalida, empleadosLeidos);
                    break;
            }

            System.out.println("Archivo de salida guardado correctamente en: " + rutaSalida);
        }
    }

    // Metodo para mostrar el sumario en consola (Tarea ;D)
    private static void mostrarSumario(List<Empleado> empleados) {
        System.out.println();
        System.out.println("===== Detalle por empleado =====");
        empleados.forEach(System.out::println);

        double sueldoMedioGeneral = empleados.stream()
                .mapToDouble(Empleado::calcularSueldoMedio)
                .average()
                .orElse(0.0);

        double sueldoMaximoGeneral = empleados.stream()
                .mapToDouble(Empleado::obtenerSueldoMaximo)
                .max()
                .orElse(0.0);

        double sueldoMinimoGeneral = empleados.stream()
                .mapToDouble(Empleado::obtenerSueldoMinimo)
                .min()
                .orElse(0.0);

        System.out.println();
        System.out.println("===== Resumen general (" + empleados.size() + " empleados) =====");
        System.out.printf(Locale.US, "Sueldo medio general: %.2f%n", sueldoMedioGeneral);
        System.out.printf(Locale.US, "Sueldo máximo general: %.2f%n", sueldoMaximoGeneral);
        System.out.printf(Locale.US, "Sueldo mínimo general: %.2f%n", sueldoMinimoGeneral);
        System.out.println();
    }

    // Metodo para generar los archivos de datos en los 5 formatos(Tarea ;D)
    private static void generarArchivosDeDatos(List<Empleado> empleados) throws IOException {
        Configuracion configuracion = new Configuracion();

        EscritorArchivoTXT.escribirDatosGenerados(configuracion.getProperty("ruta.txt"), empleados);
        EscritorArchivoCSV.escribirDatosGenerados(configuracion.getProperty("ruta.csv"), empleados);
        EscritorArchivoExcel.escribirDatosGenerados(configuracion.getProperty("ruta.xlsx"), empleados);
        EscritorArchivoXML.escribirDatosGenerados(configuracion.getProperty("ruta.xml"), empleados);
        EscritorArchivoJSON.escribirDatosGenerados(configuracion.getProperty("ruta.json"), empleados);
    }
}
