package es.fempa.acd.sumariosalarios;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LectorArchivoXML {

    public static List<Empleado> leerArchivoXML(String rutaArchivo) throws Exception {
        List<Empleado> empleados = new ArrayList<>();
        SAXBuilder saxBuilder = new SAXBuilder();
        File archivo = new File(rutaArchivo);
        Document documento = saxBuilder.build(archivo);
        Element rootElement = documento.getRootElement(); // "empleados"

        for (Element empleadoElement : rootElement.getChildren("empleado")) {
            int id = Integer.parseInt(empleadoElement.getChildText("id"));
            String nombre = empleadoElement.getChildText("nombre");
            String dni = empleadoElement.getChildText("dni");

            List<Double> sueldosMensuales = new ArrayList<>();
            for (Element sueldoElement : empleadoElement.getChild("sueldos").getChildren("sueldo")) {
                sueldosMensuales.add(Double.parseDouble(sueldoElement.getText()));
            }

            Empleado empleado = new Empleado(id, nombre, dni, sueldosMensuales);
            empleados.add(empleado);
        }

        return empleados;
    }
}
