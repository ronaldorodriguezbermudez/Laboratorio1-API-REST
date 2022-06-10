/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import Excepciones.GlobalException;
import Excepciones.NoDataException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import LogicaNegocio.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.internal.OracleTypes;


/**
 *
 * @author Estudiante
 */
public class ServicioUsuario{
    
    private static final String INSERTARUSUARIO = "{call insertarUsuario(?,?,?,?,?)}";
    private static final String LISTARUSUARIO = "{?=call listarUsuarios()}";
    private static final String ACTUALIZAUSUARIO ="{call actualizaUsuario(?,?,?,?,?)}";
    private static final String ELIMINARUSUARIO  = "{call eliminarUsuario(?)}";
    private static final String CONSULTARUSUARIO  = "{?=call consultarUsuario(?)}";
    
    
    /** Creates a new instance of servicioUsuario */
    public ServicioUsuario() {
        
    }
    public void insertarUsuario(Usuario elUsuario) throws GlobalException, NoDataException  	{
        Connection conexion = dbConexion.getConnection();
        CallableStatement pstmt=null;
        
        try {
            pstmt = conexion.prepareCall(INSERTARUSUARIO);
            pstmt.setString(1,elUsuario.getId());
            pstmt.setString(2,elUsuario.getClave());
            pstmt.setString(3,elUsuario.getNombre());
            pstmt.setString(4,elUsuario.getEmail());
            pstmt.setInt(5,elUsuario.getIdRol());
            boolean resultado = pstmt.execute();
            if (resultado == true) {
                throw new NoDataException("No se realizo la inserci�n");
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
    public Collection listarUsuarios() throws GlobalException, NoDataException{      
        Connection conexion = dbConexion.getConnection();      
        
        ResultSet rs=null;
        ArrayList coleccion= new ArrayList();
        Usuario elUsuario=null;
        CallableStatement pstmt=null;
        try{
            pstmt = conexion.prepareCall(LISTARUSUARIO);          
            pstmt.registerOutParameter(1,OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
             while (rs.next()) {
                elUsuario = new Usuario(rs.getString("id_user"),
                                        rs.getString("clave"),
                                        rs.getString("nombre"),
                                        rs.getString("email"),
                                        rs.getInt("id_rol"));
                coleccion.add(elUsuario);
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
        return coleccion;
    }
    public Usuario consultarUsuario(String cedula) throws GlobalException, NoDataException  {
      Connection conexion = dbConexion.getConnection();      
        
        ResultSet rs=null;
        Usuario elUsuario=null;
        CallableStatement pstmt=null;
        try{
            pstmt = conexion.prepareCall(CONSULTARUSUARIO);          
            pstmt.registerOutParameter(1,OracleTypes.CURSOR);
            pstmt.setString(2,cedula);
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
             while (rs.next()) {
                 elUsuario = new Usuario(rs.getString("id_user"),
                                        rs.getString("clave"),
                                        rs.getString("nombre"),
                                        rs.getString("email"),
                                        rs.getInt("id_rol"));
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
        if (elUsuario== null) {
            throw new NoDataException("No hay datos");
        }
        return elUsuario;
   }
    public void actualizarUsuario(Usuario elUsuario) throws GlobalException, NoDataException  {
        Connection conexion = dbConexion.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ACTUALIZAUSUARIO);
            pstmt.setString(1,elUsuario.getId());
            pstmt.setString(2,elUsuario.getClave());
            pstmt.setString(3,elUsuario.getNombre());
            pstmt.setString(4,elUsuario.getEmail());
            pstmt.setInt(5,elUsuario.getIdRol());
            int resultado = pstmt.executeUpdate();
            
            //si es diferente de 0 es porq si afecto un registro o mas
            if (resultado != 0) {
                throw new NoDataException ("No se realizo la actualizaci�n");
            }
            else{
               System.out.println("\nModificaci�n Satisfactoria!");
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
    public void eliminarUsuario(String cedula) throws GlobalException, NoDataException  {
        Connection conexion = dbConexion.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(ELIMINARUSUARIO);
            pstmt.setString(1,cedula);

            int resultado = pstmt.executeUpdate();
            
            if (resultado != 0) {
                throw new NoDataException ("No se realizo el borrado");
            }
            else{
               System.out.println("\nEliminación Satisfactoria!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no válida");
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
        // TODO code application logic here
        ServicioUsuario se = new ServicioUsuario();
        Usuario u = new Usuario();
        System.out.println(se.consultarUsuario("117260099"));
    }
}

