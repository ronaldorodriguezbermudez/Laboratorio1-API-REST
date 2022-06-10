
package LogicaNegocio;

/**
 *
 * @author Josué
 */
public class Rol {
    private int id;
    private String nombre;

    public Rol(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    static private Rol rol = null;
    
    static public Rol getInstance(){
        if (rol == null) {
             rol = new Rol();
         }
         return rol;
    }

    public Rol() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Rol{" + "idUser=" + id + ", nombre=" + nombre + '}';
    }
    
    
}
