package TrabajandoConXPath;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class manejadorAlumnos extends DefaultHandler {
    //Nuestro manejador tiene que guardar alumnos
    private ArrayList<Alumno> alumnos = new ArrayList<>();
    //variable que almacena los caracteres de texto
    private StringBuilder buffer = new StringBuilder();
    private Alumno alumAux;

    public ArrayList<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "listadealumnos":
                break;
            case "alumno":
                alumAux = new Alumno();
                break;
            case "nombre", "edad":
                buffer.delete(0, buffer.length());
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        switch (qName) {
            case "listadealumnos":
                break;
            case "alumno":
                alumnos.add(alumAux);
                break;
            case "nombre":
                alumAux.setNombre(buffer.toString());
                break;
            case "edad":
                alumAux.setEdad(Integer.parseInt(buffer.toString()));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }
}
