
import java.util.Scanner;

public class ProyectoPokemon
{
    public static void main(String args[])
    {
        Scanner entrada = new Scanner(System.in);
        String nombre_jugador;
        int opcion;

        System.out.println("Escribe el nombre de tu personaje: ");
        nombre_jugador = entrada.nextLine();


        do
        {
            System.out.println("┌──────────────────────────────────────────────────────────────┐");
            System.out.println("│      ┌────────────────────────────────────────────────┐      │");
            System.out.println("│      │          POKEMON EL DESPERTAR DEL HÉROE        │      │");
            System.out.println("│      └────────────────────────────────────────────────┘      │");
            System.out.println("│           ┌──────────────────────────────────────┐           │");
            System.out.println("│           │ 1. Nueva Partida                     │           │");
            System.out.println("│           └──────────────────────────────────────┘           │");
            System.out.println("│           ┌──────────────────────────────────────┐           │");
            System.out.println("│           │ 2. Consultar Ranking                 │           │");
            System.out.println("│           └──────────────────────────────────────┘           │");
            System.out.println("│           ┌──────────────────────────────────────┐           │");
            System.out.println("│           │ 3. Ayuda                             │           │");
            System.out.println("│           └──────────────────────────────────────┘           │");
            System.out.println("│           ┌──────────────────────────────────────┐           │");
            System.out.println("│           │ 4. Salir                             │           │");
            System.out.println("│           └──────────────────────────────────────┘           │");
            System.out.println("│            Elige una opción:                                 │");
            System.out.println("└──────────────────────────────────────────────────────────────┘");

            if (entrada.hasNextInt())
            {
                opcion = entrada.nextInt();

                if (opcion >= 1 && opcion <= 4)
                {
                    switch (opcion)
                    {
                        case 1:

                            iniciarPartida(entrada, nombre_jugador);

                        break;

                        case 2:
                            LeerRanking.mostrarRankingConsola(); 
                            
                        break;

                        case 3:

                            System.out.println("┌──────────────────────────────────────────────────────────────┐");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│    Ayuda a tus Pokemons a vencer al equipo enemigo.          │");
                            System.out.println("│    Para ello, deberás hacer uso de los números del teclado   │");
                            System.out.println("│    y elegir el movimiento correcto.                          │");
                            System.out.println("│                                                              │");
                            System.out.println("│    Te enfrentarás a 3 Pokemons enemigos,                     │");
                            System.out.println("│    Cada uno más difícil que el anterior.                     │");
                            System.out.println("│                                                              │");
                            System.out.println("│    Consejo: Recuerda utilizar tus pociones con cabeza.       │");
                            System.out.println("│                                                              │");
                            System.out.println("│    El tipo del Pokemon no afectará en el combate.            │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("└──────────────────────────────────────────────────────────────┘");

                        break;

                        case 4:

                            System.out.println("┌──────────────────────────────────────────────────────────────┐");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                        ¡Hasta pronto!                        │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("│                                                              │");
                            System.out.println("└──────────────────────────────────────────────────────────────┘");

                        break;
                    }
                }
                else
                {
                    System.out.println("Opción no válida, introduce un número del (1-4)");
                    opcion = 0;
                }
            }
            else
            {
                System.out.println("Opción no válida, introduce un número del (1-4)");
                entrada.next();
                opcion = 0;
            }

        }
        while (opcion != 4);

        entrada.close();
    }

    public static void iniciarPartida(Scanner entrada, String nombre_jugador)
    {
        System.out.println("┌──────────────────────────────────────────────────────────────┐");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                 ¡Comienza la aventura Pokémon!               │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("│                                                              │");
        System.out.println("└──────────────────────────────────────────────────────────────┘");

        Pokemon[] catalogo = CatalogoPokemon.crearCatalogo();

        CombatePokemon combate = new CombatePokemon();

        combate.iniciarCombate(entrada, catalogo, nombre_jugador);
    }
}
