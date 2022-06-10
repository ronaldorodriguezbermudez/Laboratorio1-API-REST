package AccesoDatos;


import Excepciones.GlobalException;
import Excepciones.NoDataException;
import LogicaNegocio.Profesor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.internal.OracleTypes;

public class ServicioProfesor {

    private static final String INSERTAR_PROFESOR = "{call insertarProfesor(?,?,?,?)}";
    private static final String MODIFICAR_PROFESOR = "{call actualizaProfesor(?,?,?,?)}";
    private static final String BUSCAR_PROFESOR = "{?=call consultarProfesor(?)}";
    private static final String LISTAR_PROFESOR = "{?=call listarProfesores()}";
    private static final String ELIMINAR_PROFESOR = "{call eliminarProfesor(?)}";

    public ServicioProfesor() {
    }

    /*Insertar profesores*/
    public void insertar_profesor(Profesor elProfesor) throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();
        CallableStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conexion.prepareCall(INSERTAR_PROFESOR);
            pstmt.setString(1, elProfesor.getCedula());
            pstmt.setString(2, elProfesor.getNombre());
            pstmt.setString(3, elProfesor.getTelefono());
            pstmt.setString(4, elProfesor.getEmail());
            
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la insercion");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Llave duplicada");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    /*Modificar profesores*/
    public void modificar_profesor(Profesor elProfesor) throws GlobalException, NoDataException {
        
        Connection c = dbConexion.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = c.prepareStatement(MODIFICAR_PROFESOR);
            pstmt.setString(1, elProfesor.getCedula());
            pstmt.setString(2, elProfesor.getNombre());
            pstmt.setString(3, elProfesor.getTelefono());
            pstmt.setString(4, elProfesor.getEmail());
            
            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo la actualización");
            } else {
                System.out.println("\nModificación exitosa!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    /*Buscar profesores*/
    public Profesor buscar_profesor(String id) throws GlobalException, NoDataException {

        Connection c = dbConexion.getConnection();
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Profesor elProfesor = null;
        CallableStatement pstmt = null;
        try {
            pstmt = c.prepareCall(BUSCAR_PROFESOR);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elProfesor = new Profesor(rs.getString("ID_PROFESOR"),
                        rs.getString("NOMBRE"),
                        rs.getString("TELEFONO"),
                        rs.getString("EMAIL"));
                coleccion.add(elProfesor);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
              
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
            throw new NoDataException("No hay datos");
        }
        return elProfesor;
    }

    /*Listar profesores*/
     public ArrayList listarProfesor() throws GlobalException, NoDataException {

        Connection c = dbConexion.getConnection();
        ResultSet rs = null;
        ArrayList<Profesor> coleccion = new ArrayList<>();
        Profesor elProfesor = null;
        CallableStatement pstmt = null;
        
        try {
            pstmt = c.prepareCall(LISTAR_PROFESOR);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elProfesor = new Profesor(
                        rs.getString("ID_PROFESOR"),
                        rs.getString("NOMBRE"),
                        rs.getString("TELEFONO"),
                        rs.getString("EMAIL")
                );
                coleccion.add(elProfesor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
               // c.close();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        if (coleccion == null || coleccion.size() == 0) {
         throw new NoDataException("No hay datos");
         }
        return coleccion;
    }

    /*Eliminar profesores*/
    public void eliminar_profesor(String id) throws GlobalException, NoDataException {
       Connection c = dbConexion.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = c.prepareStatement(ELIMINAR_PROFESOR);
            pstmt.setString(1, id);

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("No se realizo el borrado");
            } else {
                System.out.println("\nEliminación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
               
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
    
    
    
     public static void main(String[] args) throws GlobalException, NoDataException {
     ServicioProfesor sp = new ServicioProfesor();
     Profesor p = new Profesor("sha","sssghfa","sa","sa");
     //sp.insertar_profesor(p);  // ok
     //sp.eliminar_profesor("sa");  //ok
     //sp.modificar_profesor(p); //ok
     
     System.out.println(sp.listarProfesor());  //[LogicaNegocio.Profesor@588df31b, LogicaNegocio.Profesor@33b37288, LogicaNegocio.Profesor@77a57272, LogicaNegocio.Profesor@7181ae3f
     //System.out.println(sp.buscar_profesor("sa"));  //LogicaNegocio.Profesor@588df31b
     
     }
     
}
