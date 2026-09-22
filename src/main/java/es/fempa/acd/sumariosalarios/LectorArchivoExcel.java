package es.fempa.acd.sumariosalarios;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivoExcel {

    public static List<Empleado> leerArchivoExcel(String rutaArchivo) throws IOException {
        List<Empleado> empleados = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(rutaArchivo);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    // Saltar la primera fila (encabezado)
                    continue;
                }

                // NOTA: EscritorArchivoExcel escribe el nombre completo en UNA sola
                // celda (columna 1), así que lo leemos igual: id, nombre, dni, sueldos...
                int id = (int) row.getCell(0).getNumericCellValue();
                String nombre = row.getCell(1).getStringCellValue();
                String dni = row.getCell(2).getStringCellValue();

                List<Double> sueldosMensuales = new ArrayList<>();
                for (int i = 3; i < 15; i++) {
                    sueldosMensuales.add(row.getCell(i).getNumericCellValue());
                }

                Empleado empleado = new Empleado(id, nombre, dni, sueldosMensuales);
                empleados.add(empleado);
            }
        }

        return empleados;
    }
}
