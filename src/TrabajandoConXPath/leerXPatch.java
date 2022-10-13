package TrabajandoConXPath;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class leerXPatch {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document archivoParseado = null;
        NodeList listaNodos = null;
        try {
            DocumentBuilder constructor = factory.newDocumentBuilder();
            File archivoXML = new File("src/Recursos/XMLS/alumnos.xml");
            if (archivoXML.exists()) {
                archivoParseado = constructor.parse(archivoXML);
                archivoParseado.normalize();
            }
            XPath xPath = XPathFactory.newInstance().newXPath();
            listaNodos = (NodeList) xPath.compile("//alumno").evaluate(archivoParseado, XPathConstants.NODESET);
            for (int i = 0; i < listaNodos.getLength(); i++) {
                System.out.println("Alumno " + (i + 1) + ": ");
                System.out.println("\tNombre: " + xPath.compile("nombre").evaluate(listaNodos.item(i)));
                System.out.println("\tEdad: " + xPath.compile("edad").evaluate(listaNodos.item(i)));
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
    }
}
