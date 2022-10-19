package TrabajandoConXPath;

import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class leerSAX {
    public static void main(String[] args) {
        File archivo = new File("src/Recursos/XMLS/alumnos.xml");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            manejadorAlumnos miManejador = new manejadorAlumnos();
            saxParser.parse(archivo, miManejador);
            for (Alumno alum : miManejador.getAlumnos()) {
                System.out.println(alum.getNombre());
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
