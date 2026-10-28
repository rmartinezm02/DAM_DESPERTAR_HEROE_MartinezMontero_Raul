public class Pokemon
{
    // Atributos
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private String tipo;

    private int ataqueBase;
    private int defensaBase;

    private int potenciadoAtaque;
    private int potenciadoDefensa;

    private AtaquePokemon ataquePokemon;

    // Constructor
    public Pokemon(String nombre, int ataque, int defensa, String tipo)
    {
        this.nombre = nombre;
        this.vida = 100;
        this.ataque = ataque;
        this.defensa = defensa;
        this.tipo = tipo;

        this.ataqueBase = ataque;
        this.defensaBase = defensa;

        this.potenciadoAtaque = 0;
        this.potenciadoDefensa = 0;
    }

    // Getters
    public String getNombre()
    {
        return nombre;
    }

    public int getVida()
    {
        return vida;
    }

    public int getAtaque()
    {
        return ataque;
    }

    public int getDefensa()
    {
        return defensa;
    }

    public String getTipo()
    {
        return tipo;
    }

    public AtaquePokemon getAtaquePokemon()
    {
        return ataquePokemon;
    }

    // Setters
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setVida(int vida)
    {
        if (vida > 100)
        {
            this.vida = 100;
        }
        else if (vida < 0)
        {
            this.vida = 0;
        }
        else
        {
            this.vida = vida;
        }
    }

    public void setAtaque(int ataque)
    {
        this.ataque = ataque;
    }

    public void setDefensa(int defensa)
    {
        this.defensa = defensa;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public void setAtaquePokemon(AtaquePokemon ataquePokemon)
    {
        this.ataquePokemon = ataquePokemon;
    }

    // Subir ataque
    public boolean PotenciarAtaque()
    {
        if (potenciadoAtaque < 3)
        {
            ataque += 15;
            potenciadoAtaque++;

            System.out.println(" "+nombre + " ha aumentado su ataque en 15 puntos.");
            System.out.println(" Mejoras de ataque: " + potenciadoAtaque + "/3");

            return true;
        }

        System.out.println(" "+nombre + " ya ha alcanzado el máximo de mejoras de ataque.");
        return false;
    }

    // Subir defensa
    public boolean PotenciarDefensa()
    {
        if (potenciadoDefensa < 3)
        {
            defensa += 15;
            potenciadoDefensa++;

            System.out.println(" "+nombre + " ha aumentado su defensa en 15 puntos.");
            System.out.println(" Mejoras de defensa: " + potenciadoDefensa + "/3");

            return true;
        }

        System.out.println(" "+nombre + " ya ha alcanzado el máximo de mejoras de defensa.");
        return false;
    }

    // Recibir daño
    public void RecibirDamage(int damage)
    {
        if (damage < 0)
        {
            damage = 0;
        }

        vida -= damage;

        if (vida < 0)
        {
            vida = 0;
        }

        System.out.println(" Vida restante: " + vida);

        if (vida == 0)
        {
            System.out.println(" "+nombre + " ha sido debilitado.");
        }
    }

    // Recuperar vida con una poción
    public void CurarPocion()
    {
        vida += 40;

        if (vida > 100)
        {
            vida = 100;
        }

        System.out.println(" "+nombre + " ha recuperado vida.");
        System.out.println(" Vida actual: " + vida);
    }

    // Comprobar si está debilitado
    public boolean EstaDebilitado()
    {
        return vida <= 0;
    }

    // Reiniciar estadísticas al terminar la partida
    public void ReiniciarCombate()
    {
        vida = 100;
        ataque = ataqueBase;
        defensa = defensaBase;

        potenciadoAtaque = 0;
        potenciadoDefensa = 0;
    }

    // Crear una copia independiente del Pokémon
    public Pokemon copiarPokemon()
    {
        Pokemon copia = new Pokemon(nombre, ataqueBase, defensaBase, tipo);

        if (ataquePokemon != null)
        {
            copia.setAtaquePokemon(
                new AtaquePokemon(
                    ataquePokemon.getNombre(),
                    ataquePokemon.getDamageBase()
                )
            );
        }

        return copia;
    }
}