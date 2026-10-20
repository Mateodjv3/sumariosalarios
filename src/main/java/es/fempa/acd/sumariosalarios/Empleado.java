package es.fempa.acd.sumariosalarios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Empleado {

    private int id;
    private String nombre;
    private String dni;
    private List<Double> sueldosMensuales;

    public Empleado() {
    }

    // Constructor
    public Empleado(int id, String nombre, String dni, List<Double> sueldosMensuales) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.sueldosMensuales = sueldosMensuales;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Double> getSueldosMensuales() {
        return sueldosMensuales;
    }

    public void setSueldosMensuales(List<Double> sueldosMensuales) {
        this.sueldosMensuales = sueldosMensuales;
    }

    // Metodo para calcular el sueldo medio (usando Streams)
    public double calcularSueldoMedio() {
        double promedio = sueldosMensuales.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        return new BigDecimal(promedio)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    // Alias en forma de "getter" para que Jackson incluya el sueldo medio
    // automáticamente al serializar a JSON (getSueldoMedio -> "sueldoMedio").
    public double getSueldoMedio() {
        return calcularSueldoMedio();
    }

    // Metodo para obtener el sueldo máximo
    public double obtenerSueldoMaximo() {
        return sueldosMensuales.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);
    }

    // Metodo para obtener el sueldo mínimo
    public double obtenerSueldoMinimo() {
        return sueldosMensuales.stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0.0);
    }

    // Metodo toString para mostrar información del empleado
    @Override
    public String toString() {
        return String.format(
                "Empleado{id=%d, nombre='%s', dni='%s', sueldo medio=%.2f, sueldo maximo=%.2f, sueldo minimo=%.2f}",
                id, nombre, dni, calcularSueldoMedio(), obtenerSueldoMaximo(), obtenerSueldoMinimo());
    }
}
