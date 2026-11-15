import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LeerRanking
{
    // Mostrar ranking por consola
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

            // Leer los datos del XML
            for (int i = 0; i < jugadores.getLength(); i++)
            {
                Element jugador = (Element) jugadores.item(i);

                nombres[i] = jugador.getElementsByTagName("nombre")
                                    .item(0).getTextContent();

                puntuaciones[i] = Integer.parseInt(
                    jugador.getElementsByTagName("puntuacion")
                           .item(0).getTextContent()
                );
            }

            // Ordenar de mayor a menor
            ordenarRanking(nombres, puntuaciones);

            System.out.println("\n================================");
            System.out.println("       RANKING POKEMON");
            System.out.println("================================");

            for (int i = 0; i < nombres.length; i++)
            {
                System.out.println((i + 1) + ". "
                                   + nombres[i] + " - "
                                   + puntuaciones[i] + " puntos");
            }

            System.out.println("================================");
        }
        catch (Exception e)
        {
            System.out.println("Error al leer el ranking: " + e.getMessage());
        }
    }


    // Generar la pagina HTML
    public static void generarHTML()
    {
        try
        {
            File archivo = new File("RankingPokemon.xml");

            String[] nombres;
            int[] puntuaciones;

            if (archivo.exists())
            {
                DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
                DocumentBuilder constructor = fabrica.newDocumentBuilder();
                Document documento = constructor.parse(archivo);

                NodeList jugadores = documento.getElementsByTagName("jugador");

                nombres = new String[jugadores.getLength()];
                puntuaciones = new int[jugadores.getLength()];

                for (int i = 0; i < jugadores.getLength(); i++)
                {
                    Element jugador = (Element) jugadores.item(i);

                    nombres[i] = jugador.getElementsByTagName("nombre")
                                        .item(0).getTextContent();

                    puntuaciones[i] = Integer.parseInt(
                        jugador.getElementsByTagName("puntuacion")
                               .item(0).getTextContent()
                    );
                }
            }
            else
            {
                nombres = new String[0];
                puntuaciones = new int[0];
            }

            // Ordenar los resultados
            ordenarRanking(nombres, puntuaciones);

            // Crear el archivo HTML
            BufferedWriter escritor = new BufferedWriter(
                new FileWriter("RankingPokemon.html")
            );

            escritor.write("<!DOCTYPE html>");
            escritor.newLine();
            escritor.write("<html lang=\"es\">");
            escritor.newLine();
            escritor.write("<head>");
            escritor.newLine();
            escritor.write("<meta charset=\"UTF-8\">");
            escritor.newLine();
            escritor.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            escritor.newLine();
            escritor.write("<title>Ranking Pokemon</title>");
            escritor.newLine();
            escritor.write("<link rel=\"stylesheet\" href=\"EstiloRanking.css\">");
            escritor.newLine();
            escritor.write("</head>");
            escritor.newLine();

            escritor.write("<body>");
            escritor.newLine();

            escritor.write("<main>");
            escritor.newLine();
            escritor.write("<h1>RANKING POKEMON</h1>");
            escritor.newLine();
            escritor.write("<table>");
            escritor.newLine();

            escritor.write("<tr>");
            escritor.write("<th>POSICION</th>");
            escritor.write("<th>JUGADOR</th>");
            escritor.write("<th>PUNTUACION</th>");
            escritor.write("</tr>");
            escritor.newLine();

            if (nombres.length == 0)
            {
                escritor.write("<tr><td colspan=\"3\">Todavia no hay partidas registradas</td></tr>");
                escritor.newLine();
            }
            else
            {
                for (int i = 0; i < nombres.length; i++)
                {
                    escritor.write("<tr>");

                    escritor.write("<td>" + (i + 1) + "</td>");

                    escritor.write("<td>"
                                    + escaparHTML(nombres[i])
                                    + "</td>");

                    escritor.write("<td>"
                                    + puntuaciones[i]
                                    + " puntos</td>");

                    escritor.write("</tr>");
                    escritor.newLine();
                }
            }

            escritor.write("</table>");
            escritor.newLine();
            escritor.write("</main>");
            escritor.newLine();

            escritor.write("</body>");
            escritor.newLine();
            escritor.write("</html>");

            escritor.close();

            System.out.println("Pagina HTML del ranking actualizada.");
        }
        catch (Exception e)
        {
            System.out.println("Error al generar el HTML: " + e.getMessage());
        }
    }


    // Ordenar los arrays de mayor a menor puntuacion
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


    // Evitar que los nombres rompan el codigo HTML
    public static String escaparHTML(String texto)
    {
        texto = texto.replace("&", "&amp;");
        texto = texto.replace("<", "&lt;");
        texto = texto.replace(">", "&gt;");
        texto = texto.replace("\"", "&quot;");

        return texto;
    }
}