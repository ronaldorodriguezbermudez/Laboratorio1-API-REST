
package LogicaNegocio;

/**
 *
 * @author Josué
 */
public class Usuario {
    private String id;
    private String clave;
    private String nombre;
    private String email;
    private int idRol;

    static private Usuario usuario = null;
    
    static public Usuario getInstance(){
        if (usuario == null) {
             usuario = new Usuario();
         }
         return usuario;
    }

    public Usuario() {
    }

    public Usuario(String id, String clave, String nombre, String email, int idRol) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
        this.email = email;
        this.idRol = idRol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", clave=" + clave + ", nombre=" + nombre + ", email=" + email + ", idRol=" + idRol + '}';
    }
     
}
