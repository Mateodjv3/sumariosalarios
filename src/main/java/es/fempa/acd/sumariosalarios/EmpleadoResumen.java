package es.fempa.acd.sumariosalarios;

public class EmpleadoResumen {
    private int id;
    private String nombre;
    private String dni;
    private double sueldoMedio;
    private double sueldoMaximo;
    private double sueldoMinimo;

    public EmpleadoResumen(int id, String nombre, String dni, double sueldoMedio, double sueldoMaximo, double sueldoMinimo) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.sueldoMedio = sueldoMedio;
        this.sueldoMaximo = sueldoMaximo;
        this.sueldoMinimo = sueldoMinimo;
    }

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
    public double getSueldoMedio() {
        return sueldoMedio;
    }
    public void setSueldoMedio(double sueldoMedio) {
        this.sueldoMedio = sueldoMedio;
    }
    public double getSueldoMaximo() {
        return sueldoMaximo;
    }
    public void setSueldoMaximo(double sueldoMaximo) {
        this.sueldoMaximo = sueldoMaximo;
    }
    public double getSueldoMinimo() {
        return sueldoMinimo;
    }
    public void setSueldoMinimo(double sueldoMinimo) {
        this.sueldoMinimo = sueldoMinimo;
    }

}