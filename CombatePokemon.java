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

            // Si el Pokemon del jugador esta debilitado
            if (pokemonJugador.EstaDebilitado())
            {
                System.out.println("\nTu Pokemon ha sido debilitado.");

                System.out.println("Debes elegir otro Pokemon.");
                mostrarEquipo(jugador);

                do
                {
                    System.out.println("Elige un Pokemon:");
                    opcion = leerNumero(entrada, 1, 3);

                }
                while (!jugador.cambiarPokemonActivo(opcion - 1));

                pokemonJugador = jugador.devolverPokemonActivo();
            }

            // Reiniciar variables de cada turno
            turnoEsquivado = false;
            rivalHaEsquivado = false;
            rivalDerrotadoEsteTurno = false;

            // Mostrar estado del combate
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.println("\n\n");
            System.out.println("─────────────────────────────────────────────────────");

            System.out.println("┌───────────┐");
            System.out.println("│TU POKEMON:│");
            System.out.println("└───────────┘");
            mostrarPokemon(pokemonJugador);

            System.out.println("┌──────────────┐");
            System.out.println("│POKEMON RIVAL:│");
            System.out.println("└──────────────┘");
            mostrarPokemon(pokemonRival);

            // Menu de acciones
            System.out.println("┌────────────────────────────┐");
            System.out.println("│      ELIGE TU ACCION       │");
            System.out.println("└────────────────────────────┘");
            System.out.println("┌────────────────────────────┐");
            System.out.println("│1. Atacar                   │");
            System.out.println("│2. Subir ataque             │");
            System.out.println("│3. Subir defensa            │");
            System.out.println("│4. Esquivar (" + esquivasRestantes + ")             │");
            System.out.println("│5. Usar pocion (" + jugador.getPociones() + ")          │");
            System.out.println("│6. Huir                     │");
            System.out.println("└────────────────────────────┘");

            opcion = leerNumero(entrada, 1, 6);
            System.out.println("┌───────────────────────────────────────────────┐");


            // Ejecutar accion del jugador
            switch (opcion)
            {
                case 1:

                    AtaquePokemon ataque = pokemonJugador.getAtaquePokemon();

                    System.out.println("\n " + pokemonJugador.getNombre()
                                       + " utiliza " + ataque.getNombre() + "!");

                    // 20% de probabilidad de que el rival esquive
                    if (Math.random() < 0.20)
                    {
                        rivalHaEsquivado = true;

                        System.out.println(" ¡" + pokemonRival.getNombre()
                                           + " ha esquivado el ataque!");

                        // Al esquivar el rival, TÚ pierdes 15 ataque o 15 defensa
                        if (Math.random() < 0.5)
                        {
                            pokemonJugador.setAtaque(pokemonJugador.getAtaque() - 15);
                            System.out.println(" Al esquivar el rival, "
                                               + pokemonJugador.getNombre()
                                               + " ha perdido 15 puntos de ataque.");
                        }
                        else
                        {
                            pokemonJugador.setDefensa(pokemonJugador.getDefensa() - 15);
                            System.out.println(" Al esquivar el rival, "
                                               + pokemonJugador.getNombre()
                                               + " ha perdido 15 puntos de defensa.");
                        }
                    }
                    else
                    {
                        damage = (2 * pokemonJugador.getAtaque()
                                  - pokemonRival.getDefensa()) / 5;

                        if (damage < 0)
                        {
                            damage = 0;
                        }

                        System.out.println(" Ha causado " + damage + " puntos de daño.");
                        pokemonRival.RecibirDamage(damage);
                    }

                break;

                case 2:

                    pokemonJugador.PotenciarAtaque();

                break;

                case 3:

                    pokemonJugador.PotenciarDefensa();

                break;

                case 4:

                    if (esquivasRestantes > 0)
                    {
                        esquivasRestantes--;
                        turnoEsquivado = true;

                        System.out.println("\n " + pokemonJugador.getNombre()
                                           + " se prepara para esquivar!");

                        // Al esquivar tú, el RIVAL pierde 15 ataque o 15 defensa
                        if (Math.random() < 0.5)
                        {
                            pokemonRival.setAtaque(pokemonRival.getAtaque() - 15);
                            System.out.println(" Al esquivar, " + pokemonRival.getNombre()
                                               + " ha perdido 15 puntos de ataque.");
                        }
                        else
                        {
                            pokemonRival.setDefensa(pokemonRival.getDefensa() - 15);
                            System.out.println(" Al esquivar, " + pokemonRival.getNombre()
                                               + " ha perdido 15 puntos de defensa.");
                        }

                        System.out.println(" Esquivas restantes: " + esquivasRestantes);
                    }
                    else
                    {
                        System.out.println("\n Ya no te quedan esquivas contra este Pokemon.");
                    }

                break;

                case 5:

                    jugador.usarPocion();
                    mostrarPokemon(pokemonJugador);

                break;

                case 6:

                    System.out.println("┌─────────────────────┐");
                    System.out.println("│Has huido del combate│");
                    System.out.println("└─────────────────────┘");
                    System.exit(0);

                break;
            }


            // Comprobar si el Pokemon rival ha sido derrotado
            if (pokemonRival.EstaDebilitado())
            {
                enemigosDerrotados++;
                rivalDerrotadoEsteTurno = true;

                System.out.println("\n El Pokemon rival ha sido debilitado.");
                System.out.println("Has conseguido 100 puntos.");

                // Comprobar si quedan Pokemon rivales
                if (!rival.avanzarSiguientePokemon())
                {
                    break;
                }

                // Reiniciar las esquivas para el nuevo rival
                esquivasRestantes = 3;

                pokemonRival = rival.devolverPokemonActivo();

                System.out.println("\n El rival saca a "
                                   + pokemonRival.getNombre() + "!");
            }

            // Turno del rival
            // El rival no ataca si ha sido debilitado o acaba de salir otro Pokemon
            if (!pokemonRival.EstaDebilitado() && !rivalDerrotadoEsteTurno)
            {
                if (turnoEsquivado)
                {
                    System.out.println("\n Has esquivado el ataque rival!");
                }
                else if (rivalHaEsquivado)
                {
                    System.out.println("\n " + pokemonRival.getNombre()
                                       + " se ha centrado en esquivar y no ataca este turno.");
                }
                else
                {
                    AtaquePokemon ataqueRival = pokemonRival.getAtaquePokemon();

                    damageRival = (2 * pokemonRival.getAtaque()
                                   - pokemonJugador.getDefensa()) / 5;

                    if (damageRival < 0)
                    {
                        damageRival = 0;
                    }

                    System.out.println("\n El rival contraataca!");
                    System.out.println(" " + pokemonRival.getNombre()
                                       + " utiliza " + ataqueRival.getNombre() + "!");
                    System.out.println(" Ha causado " + damageRival + " puntos de daño.");

                    pokemonJugador.RecibirDamage(damageRival);
                }
            }
        }

         // Calcular puntuacion final
        int puntuacionFinal = enemigosDerrotados * 100;

        // Resultado final
        System.out.println("===============================");
        System.out.println("        FIN DEL COMBATE");
        System.out.println("===============================");

        if (jugador.hayPokemonVivos() && !rival.hayPokemonVivos())
        {
            System.out.println("┌─────────────────────────┐");
            System.out.println("│ENHORABUENA HAS GANADO!!!│");
            System.out.println("└─────────────────────────┘\n\n\n");
        }
        else
        {
            System.out.println("┌──────────────────────────────────────────────────┐");
            System.out.println("│TODOS TUS POKEMONS SE HAN DEBILITADO. HAS PERDIDO.│");
            System.out.println("└──────────────────────────────────────────────────┘");
        }

        // Guardar puntuacion en el ranking
        GuardarRanking.agregarJugador(nombre_jugador, puntuacionFinal);

        System.out.println("Partida registrada en el ranking con "
                           + puntuacionFinal + " puntos.");

        // Reiniciar estadisticas de ambos equipos
        jugador.reiniciarEquipo();
        rival.reiniciarEquipo();
    }

     // Mostrar datos de un Pokemon
    public void mostrarPokemon(Pokemon pokemon)
    {
        int vida = pokemon.getVida();

        if (vida >= 100)
        {
            System.out.println("┌──────────────────────┐");
            System.out.println("  " + pokemon.getNombre());
            System.out.println("   ██████████" + pokemon.getVida() + "/100  ");
            System.out.println("└──────────────────────┘");
        }
        else if (vida >= 90)
        {
            System.out.println("┌──────────────────────┐");
            System.out.println("  " + pokemon.getNombre());
            System.out.println("   █████████░" + pokemon.getVida() + "/100   ");
            System.out.println("└──────────────────────┘");
        }
        else if (vida >= 80)
        {
            System.out.println("┌──────────────────────┐");
            System.out.println("  " + pokemon.getNombre());
            System.out.println("    ████████░░" + pokemon.getVida() + "/100   ");
            System.out.println("└──────────────────────┘");
        }
        else if (vida >= 70)
        {
            System.out.println("┌──────────────────────┐");
            System.out.println("  " + pokemon.getNombre());
            System.out.println("    ███████░░░" + pokemon.getVida() + "/100   ");
            System.out.println("└──────────────────────┘");
        }
        else if (vida >= 60)
        {
            System.out.println("┌──────────────────────┐");
            System.out.println("  " + pokemon.getNombre());
            System.out.println("    ██████░░░░" + pokemon.getVida() + "/100   ");
            System.out.println("└──────────────────────┘");
        }
        else if (vida >= 50)
        {
            System.out.println("┌──────────────────────┐");
            System.out.println("  " + pokemon.getNombre());
            System.out.println("    █████░░░░░" + pokemon.getVida() + "/100   ");
            System.out.println("└──────────────────────┘");
        }
        else if (vida >= 40)
        {
            System.out.println("┌──────────────────────┐");
            System.out.println("  " + pokemon.getNombre());
            System.out.println("    ████░░░░░░" + pokemon.getVida() + "/100   ");
            System.out.println("└──────────────────────┘");
        }
        else if (vida >= 30)
        {
            System.out.println("┌──────────────────────┐");
            System.out.println("  " + pokemon.getNombre());
            System.out.println("    ███░░░░░░░" + pokemon.getVida() + "/100   ");
            System.out.println("└──────────────────────┘");
        }
        else if (vida >= 20)
        {
            System.out.println("┌──────────────────────┐");
            System.out.println("  " + pokemon.getNombre());
            System.out.println("    ██░░░░░░░░" + pokemon.getVida() + "/100   ");
            System.out.println("└──────────────────────┘");
        }
        else if (vida > 0)
        {
            System.out.println("┌──────────────────────┐");
            System.out.println("  " + pokemon.getNombre());
            System.out.println("    █░░░░░░░░░" + pokemon.getVida() + "/100   ");
            System.out.println("└──────────────────────┘");
        }
        else
        {
            System.out.println("┌──────────────────────┐");
            System.out.println("  " + pokemon.getNombre());
            System.out.println("    ░░░░░░░░░░" + pokemon.getVida() + "/100    ");
            System.out.println("└──────────────────────┘");
        }

        System.out.println("Tipo: " + pokemon.getTipo());
        System.out.println("Ataque: " + pokemon.getAtaque());
        System.out.println("Defensa: " + pokemon.getDefensa());

        System.out.println("Ataque de daño: "
                           + pokemon.getAtaquePokemon().getNombre()
                           + " (" + pokemon.getAtaquePokemon().getDamageBase()
                           + " puntos)");
    }


