
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
