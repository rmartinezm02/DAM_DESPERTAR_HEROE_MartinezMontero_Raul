import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ModificarXML 
{
    public static void main(String[] args) 
    {
        try
        {
            File archivo = new File("alumnos.xml");
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.parse(archivo);

            documento.getDocumentElement().normalize();
            
            NodeList lista = documento.getElementsByTagName("alumno");

            boolean encontrado = false;

            // CORREGIDO: Eliminado el punto y coma (;) que sobraba al final del for
            for (int i = 0; i < lista.getLength(); i++)
            {
                Element alumno = (Element) lista.item(i);

                if (alumno.getAttribute("id").equals("1"))
                {
                    // CORREGIDO: getElementsByTagName (con 's')
                    Element edad = (Element) alumno
                                            .getElementsByTagName("edad")
                                            .item(0);
                    
                    // CORREGIDO: setTextContent para cambiar el valor
                    edad.setTextContent("25"); // Cambiamos la edad a 25
                    encontrado = true;
                    break; // Ya lo encontramos, salimos del bucle
                }
            }

            if (encontrado)
            {
                // IMPORTANTE: Para guardar los cambios en el archivo físico, necesitamos el Transformer
                TransformerFactory transformerFabrica = TransformerFactory.newInstance();
                Transformer transformer = transformerFabrica.newTransformer();

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                DOMSource origen = new DOMSource(documento);
                StreamResult destino = new StreamResult(new File("alumnos.xml"));

                transformer.transform(origen, destino);

                System.out.println("Alumno modificado y guardado correctamente.");
            }
            else
            {
                System.out.println("No se encontró al alumno con ese ID.");
            }

        }
        catch (Exception e)
        {
            // CORREGIDO: Se completa el bloque catch que estaba vacío
            System.out.println("Error modificando el XML: " + e.getMessage());
        }
    }
}
