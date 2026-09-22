package es.fempa.acd.sumariosalarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSetEmpleados {

    // Método estático que genera la lista de empleados
    public static List<Empleado> generarEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        Random random = new Random();

        // Nombres y DNIs de ejemplo
        String[] nombres = {"Juan Perez", "Maria Garcia", "Pedro Rodriguez", "Laura Sanchez", "Javier Lopez",
                "Ana Fernandez", "Miguel Torres", "Lucia Martin", "Raul Gonzalez", "Carmen Ruiz",
                "Sergio Hernandez", "Sofia Morales", "David Castillo", "Isabel Ortega", "Manuel Vega",
                "Paula Rojas", "Ruben Gimenez", "Adriana Ramos", "Jose Ibanez", "Claudia Pardo",
                "Vicente Varela", "Andrea Gutierrez", "Fernando Serrano", "Beatriz Diaz", "Diego Romero"};

        String[] dnis = {"12345678A", "87654321B", "12398745C", "87451236D", "74951238E", "98234567F", "34567821G",
                "54872345H", "67543218I", "32458769J", "82736451K", "92748365L", "43658729M", "98237465N",
                "12387594O", "76543218P", "12983756Q", "67834512R", "98237645S", "78452139T",
                "92387546U", "34827619V", "23879456W", "87236451X", "94738265Y"};

        // Generar 25 empleados
        for (int i = 0; i < 25; i++) {
            List<Double> sueldosMensuales = generarSueldosAleatorios(random);
            Empleado empleado = new Empleado(i + 1, nombres[i], dnis[i], sueldosMensuales);
            empleados.add(empleado);
        }

        return empleados;
    }

    // Método auxiliar para generar sueldos aleatorios entre 1200 y 2000 euros
    private static List<Double> generarSueldosAleatorios(Random random) {
        List<Double> sueldosMensuales = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            double sueldo = 1200 + (800 * random.nextDouble()); // Generar sueldos entre 1200 y 2000
            sueldo = Math.round(sueldo * 100.0) / 100.0; // redondear a 2 decimales
            sueldosMensuales.add(sueldo);
        }
        return sueldosMensuales;
    }
}
