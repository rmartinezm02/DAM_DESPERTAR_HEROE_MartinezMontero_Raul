
public class EquipoPokemon
{
    // Atributos
    private Pokemon[] pokemonElegido;
    private int posicionPokemonActivo;
    private int pociones;

    // Constructor
    public EquipoPokemon(int pocionesIniciales)
    {
        pokemonElegido = new Pokemon[3];
        posicionPokemonActivo = 0;
        pociones = pocionesIniciales;
    }

    // Devolver el Pokemon activo
    public Pokemon devolverPokemonActivo()
    {
        return pokemonElegido[posicionPokemonActivo];
    }

    // Devolver todos los Pokemon
    public Pokemon[] getPokemonElegido()
    {
        return pokemonElegido;
    }

    // Devolver pociones disponibles
    public int getPociones()
    {
        return pociones;
    }

    // Comprobar si quedan Pokemon vivos
    public boolean hayPokemonVivos()
    {
        for (int i = 0; i < pokemonElegido.length; i++)
        {
            if (pokemonElegido[i] != null && !pokemonElegido[i].EstaDebilitado())
            {
                return true;
            }
        }

        return false;
    }
 // Elegir los tres Pokemon del jugador
    public void ElegirPokemon(Pokemon[] catalogo, Scanner entrada)
    {
        boolean[] seleccionados = new boolean[catalogo.length];

        System.out.println("\n┌─────────────────────┐");
        System.out.println("│ ELIGE TUS 3 POKEMON │ ");
        System.out.println("└─────────────────────┘");

        for (int i = 0; i < catalogo.length; i++)
        {
            System.out.println((i + 1) + ". " + catalogo[i].getNombre());
        }

        for (int i = 0; i < pokemonElegido.length; i++)
        {
            int opcion;

            do
            {
                System.out.println("\nElige tu Pokemon " + (i + 1) + ":");
                opcion = CombatePokemon.leerNumero(entrada, 1, catalogo.length);

                if (seleccionados[opcion - 1])
                {
                    System.out.println("Ya has elegido ese Pokemon. Escoge otro.");
                }

            } 
            while (seleccionados[opcion - 1]);

            seleccionados[opcion - 1] = true;

            pokemonElegido[i] = catalogo[opcion - 1].copiarPokemon();

            System.out.println("Has elegido a " + pokemonElegido[i].getNombre());
        }

        posicionPokemonActivo = 0;
    }

    // Crear equipo rival aleatorio
    public void elegirPokemonAleatorio(Pokemon[] catalogo)
    {
        Random aleatorio = new Random();

        boolean[] seleccionados = new boolean[catalogo.length];

        for (int i = 0; i < pokemonElegido.length; i++)
        {
            int posicion;

            do
            {
                posicion = aleatorio.nextInt(catalogo.length);

            }
            while (seleccionados[posicion]);

            seleccionados[posicion] = true;

            pokemonElegido[i] = catalogo[posicion].copiarPokemon();

            // Vida inicial de cada Pokemon rival
            if (i == 0)
            {
                pokemonElegido[i].setVida(80);
            }
            else if (i == 1)
            {
                pokemonElegido[i].setVida(90);
            }
            else
            {
                pokemonElegido[i].setVida(100);
            }
        }

        posicionPokemonActivo = 0;
    }