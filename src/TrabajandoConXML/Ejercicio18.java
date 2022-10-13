package TrabajandoConXML;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
//Realizado por: Jorge Otín Caba
public class Ejercicio18 {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //fabricante de parsers

        factory.setIgnoringElementContentWhitespace(true);
        factory.setIgnoringComments(true);

        DocumentBuilder builder = factory.newDocumentBuilder(); //parser
        File archivo =new File( "src/Recursos/XMLS/alumnos.xml");
        Document docXML = builder.parse(archivo); //archivo XML almacenado en la variable
        System.out.println("ya esta parseado");

        //accedemos al primer nodo, el nodo raiz
        Node nodoRaiz = docXML.getFirstChild();
        System.out.println(nodoRaiz.getNodeName());
        NodeList nodosHijos = nodoRaiz.getChildNodes();
        nodoRaiz = limpiarNodos(nodoRaiz);
        Node nodoHijo=null;
        NamedNodeMap atrHijo = null;
        for (int i = 0; i < nodosHijos.getLength(); i++) {
            nodoHijo=nodosHijos.item(i);
            nodoHijo = limpiarNodos(nodoHijo);
            //accedemos a los atributos del nodo
            atrHijo = nodoHijo.getAttributes();
            for (int k=0; k<atrHijo.getLength(); k++) {
                System.out.println("atributo "+atrHijo.item(k).getNodeName()+": "+atrHijo.item(k).getTextContent());
            }
            System.out.println(nodosHijos.item(i).getNodeName());
            NodeList nodosNietos = nodosHijos.item(i).getChildNodes();
            for (int j = 0; j < nodosNietos.getLength(); j++) {
                System.out.println(nodosNietos.item(j).getNodeName()+ ": "+nodosNietos.item(j).getTextContent());

            }
        }
    }

    private static Node limpiarNodos(Node nodeAux) {
        if (nodeAux.hasChildNodes()){
            NodeList listaHijos = nodeAux.getChildNodes();
            Node aux = null;
            for (int j = 0; j < listaHijos.getLength(); j++) {
                aux = listaHijos.item(j);
                if (aux.getNodeType()==3){
                    aux.setTextContent(aux.getTextContent().trim());
                    if (aux.getTextContent().equals("")) // si el nodo es un nodo de texto vacio
                        aux.getParentNode().removeChild(aux);
                }

            }
        }
        return nodeAux;
    }

}
//Realizado por: Jorge Otín Caba