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

public class leerXPatchEmpleado {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document archivoParseado = null;
        NodeList listaNodos = null;
        Empleado alAux = null;
        String apellido = "";
        String nombre = "";
        String sueldo;
        String base;
        String IRPF;
        String dni;
        ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
        File archivoXML = new File("src/Recursos/XMLS/Empleados.xml");

        try {
            DocumentBuilder constructor = factory.newDocumentBuilder();
            if (archivoXML.exists()) {
                archivoParseado = constructor.parse(archivoXML);
                archivoParseado.normalize();
            }
            XPath xPath = XPathFactory.newInstance().newXPath();
            listaNodos = (NodeList) xPath.compile("//empleado").evaluate(archivoParseado, XPathConstants.NODESET);
            for (int i = 0; i < listaNodos.getLength(); i++) {
                dni = xPath.compile("@DNI").evaluate(listaNodos.item(i));
                nombre = xPath.compile("nombre").evaluate(listaNodos.item(i));
                apellido = xPath.compile("apellido").evaluate(listaNodos.item(i));
                sueldo = String.valueOf(xPath.compile("sueldo").evaluate(listaNodos.item(i)));
                base = String.valueOf(xPath.compile("base").evaluate(listaNodos.item(i)));
                IRPF = String.valueOf(xPath.compile("IRPF").evaluate(listaNodos.item(i)));
                alAux = new Empleado(dni,nombre,apellido, sueldo,base,IRPF);
                listaEmpleados.add(alAux);
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
        alAux = new Empleado("24214211A", "Luis", "Gomez","2333","2300","21");
        try {
            addEmpleados(alAux, archivoParseado, archivoXML);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addEmpleados(Empleado alAux, Document archivo, File archXML) throws TransformerException {

        Element elEmpleado = archivo.createElement("empleado");
        Attr elDni = archivo.createAttribute("@DNI");

        Element elTagNombre = archivo.createElement("nombre");
        elEmpleado.appendChild(elTagNombre);

        Element elTagApellido = archivo.createElement("apellido");
        elEmpleado.appendChild(elTagApellido);

        Element elTagSueldo = archivo.createElement("sueldo");
        elEmpleado.appendChild(elTagSueldo);

        Element elTagBase = archivo.createElement("base");
        elEmpleado.appendChild(elTagBase);

        Element elTagIRPF = archivo.createElement("IRPF");
        elEmpleado.appendChild(elTagIRPF);

        Element elTagEdad = archivo.createElement("CP");
        elTagNombre.appendChild(archivo.createTextNode(alAux.getNombre()));
        elTagApellido.appendChild(archivo.createTextNode(alAux.getApellido()));
        elTagSueldo.appendChild(archivo.createTextNode(alAux.getSueldo()));
        elTagBase.appendChild(archivo.createTextNode(alAux.getBase()));
        elTagIRPF.appendChild(archivo.createTextNode(alAux.getIRPF()));
        elEmpleado.appendChild(elTagNombre);
        elEmpleado.appendChild(elTagEdad);
        Node raiz = archivo.getFirstChild();
        raiz = limpiarNodos(raiz);
        raiz.appendChild(elEmpleado);
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
