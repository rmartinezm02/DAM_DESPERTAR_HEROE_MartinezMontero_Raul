import java.util.Scanner;

public class CombatePokemon
{
    // Iniciar combate
    public void iniciarCombate(Scanner entrada, Pokemon[] catalogo, String nombre_jugador)
    {
        // Variables del combate
        EquipoPokemon jugador = new EquipoPokemon(3);
        EquipoPokemon rival = new EquipoPokemon(0);
        int esquivasRestantes = 3;
        boolean turnoEsquivado;
        boolean rivalHaEsquivado;
        boolean rivalDerrotadoEsteTurno;
        int opcion;
        int damage;
        int damageRival;
        int enemigosDerrotados = 0;

        // Elegir equipos
        jugador.ElegirPokemon(catalogo, entrada);
        rival.elegirPokemonAleatorio(catalogo);

        System.out.println("\n===================================");
        System.out.println("        COMIENZA EL COMBATE   ");
        System.out.println("===================================");

        System.out.println("\n Tu primer Pokemon es: "
                           + jugador.devolverPokemonActivo().getNombre());

        System.out.println(" El rival comienza con: "
                           + rival.devolverPokemonActivo().getNombre());

        // Mientras ambos equipos tengan Pokemon vivos
        while (jugador.hayPokemonVivos() && rival.hayPokemonVivos())
        {
            Pokemon pokemonJugador = jugador.devolverPokemonActivo();
            Pokemon pokemonRival = rival.devolverPokemonActivo();

