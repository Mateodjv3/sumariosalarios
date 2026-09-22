package es.fempa.acd.sumariosalarios;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class EscritorArchivoExcel {

    // Escribe el archivo de SALIDA (procesado): incluye el sueldo medio de cada empleado
    public static void escribirArchivoExcel(String rutaArchivo, List<Empleado> empleados) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Empleados");

        // Crear fila de encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("DNI");
        headerRow.createCell(3).setCellValue("Sueldo Medio");
        for (int i = 1; i <= 12; i++) {
            headerRow.createCell(3 + i).setCellValue("Sueldo " + i);
        }

        // Escribir datos de empleados
        int rowNum = 1;
        for (Empleado empleado : empleados) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(empleado.getId());
            row.createCell(1).setCellValue(empleado.getNombre());
            row.createCell(2).setCellValue(empleado.getDni());
            row.createCell(3).setCellValue(empleado.calcularSueldoMedio());

            for (int i = 0; i < empleado.getSueldosMensuales().size(); i++) {
                row.createCell(4 + i).setCellValue(empleado.getSueldosMensuales().get(i));
            }
        }

        // Escribir el archivo
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            workbook.write(fos);
        }

        workbook.close();
    }

    // Escribe el dataset ORIGINAL (sin procesar): id, nombre, dni y sueldos mensuales
    public static void escribirDatosGenerados(String rutaArchivo, List<Empleado> empleados) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Empleados");

        // Crear fila de encabezados
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre");
        headerRow.createCell(2).setCellValue("DNI");
        for (int i = 1; i <= 12; i++) {
            headerRow.createCell(2 + i).setCellValue("Sueldo " + i);
        }

        // Escribir datos de empleados
        int rowNum = 1;
        for (Empleado empleado : empleados) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(empleado.getId());
            row.createCell(1).setCellValue(empleado.getNombre());
            row.createCell(2).setCellValue(empleado.getDni());

            for (int i = 0; i < empleado.getSueldosMensuales().size(); i++) {
                row.createCell(3 + i).setCellValue(empleado.getSueldosMensuales().get(i));
            }
        }

        // Escribir el archivo
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            workbook.write(fos);
        }

        workbook.close();
    }
}
