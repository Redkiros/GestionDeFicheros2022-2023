package TrabajandoConXML;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class leerXML {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document archivoParseado = null;
        try {
            DocumentBuilder constructor = factory.newDocumentBuilder();
            File archivoXML = new File("src/Recursos/XMLS/alumnos.xml");
            if (archivoXML.exists()) {
                archivoParseado = constructor.parse(archivoXML);
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        Node nodoRaiz = archivoParseado.getFirstChild();
        System.out.println("El nodo raiz: " + nodoRaiz.getNodeName());
        NodeList nodosHijos = nodoRaiz.getChildNodes();
        nodoRaiz = limpiarNodos(nodoRaiz);
        NamedNodeMap atrHijo = null;
        for (int i = 0; i < nodosHijos.getLength(); i++) {
            nodoRaiz = nodosHijos.item(i);
            nodoRaiz = limpiarNodos(nodoRaiz);
            //accedemos a los atributos del nodo
            atrHijo = nodoRaiz.getAttributes();
            for (int k = 0; k < atrHijo.getLength(); k++) {
                System.out.println("atributo " + atrHijo.item(k).getNodeName() + ": " + atrHijo.item(k).getTextContent());
            }
            System.out.println(nodosHijos.item(i).getNodeName());
            NodeList nodosNietos = nodosHijos.item(i).getChildNodes();
            for (int j = 0; j < nodosNietos.getLength(); j++) {
                System.out.println(nodosNietos.item(j).getNodeName() + ": " + nodosNietos.item(j).getTextContent());

            }
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
