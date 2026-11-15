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

public class GuardarRanking
{
    public static void agregarJugador(String nombre, int puntuacion)
    {
        try
        {
            File archivo = new File("RankingPokemon.xml");

            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();

            Document documento;

            if (archivo.exists())
            {
                documento = constructor.parse(archivo);
            }
            else
            {
                documento = constructor.newDocument();

                Element ranking = documento.createElement("ranking");
                documento.appendChild(ranking);
            }

            Element ranking = documento.getDocumentElement();

            // Buscar el siguiente ID
            NodeList jugadores = documento.getElementsByTagName("jugador");
            int id = 1;

            for (int i = 0; i < jugadores.getLength(); i++)
            {
                Element jugadorActual = (Element) jugadores.item(i);
                int idActual = Integer.parseInt(jugadorActual.getAttribute("id"));

                if (idActual >= id)
                {
                    id = idActual + 1;
                }
            }

            // Crear el nuevo jugador
            Element jugador = documento.createElement("jugador");
            jugador.setAttribute("id", String.valueOf(id));

            Element elementoNombre = documento.createElement("nombre");
            elementoNombre.setTextContent(nombre);

            Element elementoPuntuacion = documento.createElement("puntuacion");
            elementoPuntuacion.setTextContent(String.valueOf(puntuacion));

            jugador.appendChild(elementoNombre);
            jugador.appendChild(elementoPuntuacion);

            ranking.appendChild(jugador);

            // Guardar los cambios en el XML
            TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
            Transformer transformador = fabricaTransformador.newTransformer();

            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource origen = new DOMSource(documento);
            StreamResult destino = new StreamResult(archivo);

            transformador.transform(origen, destino);

            System.out.println("Partida guardada en el ranking.");

            // Actualizar automáticamente el HTML
            LeerRanking.generarHTML();
        }
        catch (Exception e)
        {
            System.out.println("Error al guardar el ranking: " + e.getMessage());
        }
    }
}