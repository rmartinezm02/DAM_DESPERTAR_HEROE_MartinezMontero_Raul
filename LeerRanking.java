import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LeerRanking
{
    // Mostrar ranking ordenado por consola
    public static void mostrarRankingConsola()
    {
        try
        {
            File archivo = new File("RankingPokemon.xml");

            if (!archivo.exists())
            {
                System.out.println("\nTodavia no hay partidas registradas.");
                return;
            }

            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.parse(archivo);

            NodeList jugadores = documento.getElementsByTagName("jugador");

            if (jugadores.getLength() == 0)
            {
                System.out.println("\nTodavia no hay partidas registradas.");
                return;
            }

            String[] nombres = new String[jugadores.getLength()];
            int[] puntuaciones = new int[jugadores.getLength()];

            for (int i = 0; i < jugadores.getLength(); i++)
            {
                Element jugador = (Element) jugadores.item(i);

                nombres[i] = jugador.getElementsByTagName("nombre").item(0).getTextContent();
                puntuaciones[i] = Integer.parseInt(
                    jugador.getElementsByTagName("puntuacion").item(0).getTextContent()
                );
            }

            ordenarRanking(nombres, puntuaciones);

            System.out.println("\n================================");
            System.out.println("       RANKING POKEMON");
            System.out.println("================================");

            for (int i = 0; i < nombres.length; i++)
            {
                System.out.println((i + 1) + ". " + nombres[i] + " - " + puntuaciones[i] + " puntos");
            }

            System.out.println("================================");
        }
        catch (Exception e)
        {
            System.out.println("Error al leer el ranking: " + e.getMessage());
        }
    }

    // Generar la página HTML automáticamente aplicando la hoja ranking.xsl
    public static void generarHTML()
    {
        try
        {
            TransformerFactory fabrica = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File("ranking.xsl"));
            Transformer transformador = fabrica.newTransformer(xslt);

            Source xml = new StreamSource(new File("RankingPokemon.xml"));
            StreamResult salida = new StreamResult(new File("RankingPokemon.html"));

            transformador.transform(xml, salida);

            System.out.println("Página HTML generada con XSLT correctamente.");
        }
        catch (Exception e)
        {
            System.out.println("Error al generar el HTML con XSLT: " + e.getMessage());
        }
    }

    // Método auxiliar para ordenar los arrays en la consola
    public static void ordenarRanking(String[] nombres, int[] puntuaciones)
    {
        for (int i = 0; i < puntuaciones.length - 1; i++)
        {
            for (int j = 0; j < puntuaciones.length - 1 - i; j++)
            {
                if (puntuaciones[j] < puntuaciones[j + 1])
                {
                    int auxiliarPuntuacion = puntuaciones[j];
                    puntuaciones[j] = puntuaciones[j + 1];
                    puntuaciones[j + 1] = auxiliarPuntuacion;

                    String auxiliarNombre = nombres[j];
                    nombres[j] = nombres[j + 1];
                    nombres[j + 1] = auxiliarNombre;
                }
            }
        }
    }
}