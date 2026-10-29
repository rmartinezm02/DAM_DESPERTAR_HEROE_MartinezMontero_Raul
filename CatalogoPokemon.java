public class CatalogoPokemon
{
    public static Pokemon[] crearCatalogo()
    {
        Pokemon[] catalogo = new Pokemon[10];

        // Pikachu
        catalogo[0] = new Pokemon("Pikachu", 65, 45, "Electrico");
        catalogo[0].setAtaquePokemon(new AtaquePokemon("Trueno", 35));

        // Charizard
        catalogo[1] = new Pokemon("Charizard", 75, 55, "Fuego");
        catalogo[1].setAtaquePokemon(new AtaquePokemon("Lanzallamas", 35));

        // Blastoise
        catalogo[2] = new Pokemon("Blastoise", 55, 80, "Agua");
        catalogo[2].setAtaquePokemon(new AtaquePokemon("Hidrobomba", 40));

        // Venusaur
        catalogo[3] = new Pokemon("Venusaur", 60, 70, "Planta");
        catalogo[3].setAtaquePokemon(new AtaquePokemon("Rayo Solar", 40));

        // Gengar
        catalogo[4] = new Pokemon("Gengar", 80, 40, "Fantasma");
        catalogo[4].setAtaquePokemon(new AtaquePokemon("Bola Sombra", 35));

        // Machamp
        catalogo[5] = new Pokemon("Machamp", 85, 65, "Lucha");
        catalogo[5].setAtaquePokemon(new AtaquePokemon("Puño Dinámico", 40));

        // Alakazam
        catalogo[6] = new Pokemon("Alakazam", 90, 35, "Psiquico");
        catalogo[6].setAtaquePokemon(new AtaquePokemon("Psiquico", 35));

        // Golem
        catalogo[7] = new Pokemon("Golem", 65, 90, "Roca");
        catalogo[7].setAtaquePokemon(new AtaquePokemon("Terremoto", 40));

        // Dragonite
        catalogo[8] = new Pokemon("Dragonite", 85, 75, "Dragon");
        catalogo[8].setAtaquePokemon(new AtaquePokemon("Garra Dragon", 35));

        // Butterfree
        

        return catalogo;
    }
}