public class AtaquePokemon
{
    // Atributos
    private String nombre;
    private int damageBase;

    // Constructor
    public AtaquePokemon(String nombre, int damageBase)
    {
        this.nombre = nombre;
        this.damageBase = damageBase;
    }

    // Getters
    public String getNombre()
    {
        return nombre;
    }

    public int getDamageBase()
    {
        return damageBase;
    }

    // Setters
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setDamageBase(int damageBase)
    {
        this.damageBase = damageBase;
    }
}