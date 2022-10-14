package TrabajandoConXPath;

import org.w3c.dom.*;
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

public class leerXPatchCliente {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document archivoParseado = null;
        NodeList listaNodos = null;
        Cliente alAux = null;
        String apellido = "";
        Integer cp;
        String dni;
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        File archivoXML = new File("src/Recursos/XMLS/clientes.xml");

        try {
            DocumentBuilder constructor = factory.newDocumentBuilder();
            if (archivoXML.exists()) {
                archivoParseado = constructor.parse(archivoXML);
                archivoParseado.normalize();
            }
            XPath xPath = XPathFactory.newInstance().newXPath();
            listaNodos = (NodeList) xPath.compile("//cliente").evaluate(archivoParseado, XPathConstants.NODESET);
            for (int i = 0; i < listaNodos.getLength(); i++) {
                dni = xPath.compile("@DNI").evaluate(listaNodos.item(i));
                apellido = xPath.compile("apellido").evaluate(listaNodos.item(i));
                cp = Integer.valueOf(xPath.compile("CP").evaluate(listaNodos.item(i)));
                alAux = new Cliente(dni,apellido, cp);
                listaClientes.add(alAux);
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
        alAux = new Cliente("24214211A", "Gomez", 34665);
        try {
            addClientes(alAux, archivoParseado, archivoXML);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addClientes(Cliente alAux, Document archivo, File archXML) throws TransformerException {
        Element elAlumno = archivo.createElement("cliente");
        Attr elDni = archivo.createAttribute("@DNI");
        Element elTagNombre = archivo.createElement("apellido");
        elAlumno.appendChild(elTagNombre);
        Element elTagEdad = archivo.createElement("CP");
        elTagNombre.appendChild(archivo.createTextNode(alAux.getApellido()));
        elAlumno.appendChild(elTagNombre);
        elTagEdad.appendChild(archivo.createTextNode(alAux.getCP().toString()));
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
