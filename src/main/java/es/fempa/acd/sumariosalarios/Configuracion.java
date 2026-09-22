package es.fempa.acd.sumariosalarios;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {

    private Properties properties;

    public Configuracion() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Lo siento, no se pudo encontrar application.properties");
                return;
            }
            // Cargar el archivo de propiedades
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Método para obtener el valor de una propiedad por su clave
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
