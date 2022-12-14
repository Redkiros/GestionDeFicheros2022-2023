package TrabajandoConXPath;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class leerXPatch {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document archivoParseado = null;
        NodeList listaNodos = null;
        Alumno alAux = null;
        String nombre = "";
        String edad = "";
        ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
        File archivoXML = new File("src/Recursos/XMLS/alumnos.xml");

        try {
            DocumentBuilder constructor = factory.newDocumentBuilder();
            if (archivoXML.exists()) {
                archivoParseado = constructor.parse(archivoXML);
                archivoParseado.normalize();
            }
            XPath xPath = XPathFactory.newInstance().newXPath();
            listaNodos = (NodeList) xPath.compile("//alumno").evaluate(archivoParseado, XPathConstants.NODESET);
            for (int i = 0; i < listaNodos.getLength(); i++) {
                nombre = xPath.compile("nombre").evaluate(listaNodos.item(i));
                edad = xPath.compile("edad").evaluate(listaNodos.item(i));
                alAux = new Alumno(nombre, Integer.valueOf(edad));
                listaAlumnos.add(alAux);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
        alAux = new Alumno("pepe", 13);
        try {
            addAlumno(alAux, archivoParseado, archivoXML);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addAlumno(Alumno alAux, Document archivo, File archXML) throws TransformerException {
        Element elAlumno = archivo.createElement("alumno");
        Element elTagNombre = archivo.createElement("nombre");
        elAlumno.appendChild(elTagNombre);
        Element elTagEdad = archivo.createElement("edad");
        elTagNombre.appendChild(archivo.createTextNode(alAux.getNombre()));
        elAlumno.appendChild(elTagNombre);
        elTagEdad.appendChild(archivo.createTextNode(alAux.getEdad().toString()));
        elAlumno.appendChild(elTagEdad);
        Node raiz = archivo.getFirstChild();
        raiz = limpiarNodos(raiz);
        raiz.appendChild(elAlumno);
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Source source = new DOMSource(archivo);
            Result result = new StreamResult(archXML);
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http:xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerFactoryConfigurationError e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

    }

    private static Node limpiarNodos(Node nodeAux) {
        if (nodeAux.hasChildNodes()) {
            NodeList listaHijos = nodeAux.getChildNodes();
            Node aux = null;
            for (int j = 0; j < listaHijos.getLength(); j++) {
                aux = listaHijos.item(j);
                if (aux.getNodeType() == 3) {
                    aux.setTextContent(aux.getTextContent().trim());
                    if (aux.getTextContent().equals("")) // si el nodo es un nodo de texto vacio
                        aux.getParentNode().removeChild(aux);
                }

            }
        }
        return nodeAux;
    }
}
