/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Excepciones.GlobalException;
import Excepciones.NoDataException;
import LogicaNegocio.Ciclo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Ronaldo
 */
public class ServicioCiclo {

    private static final String insertarCiclo = "{call insertarCarrera (?,?,?,?,?)}";
    private static final String actualizaCiclo = "{call actualizaCarrera (?,?,?,?,?)}";
    private static final String eliminarCiclo = "{call eliminarCarrera (?)}";
    private static final String listarCiclos = "{?=call listarCarreras ()}";
    private static final String consultarCiclo = "{?=call consultarCarrera (?)}";

    public ServicioCiclo() {

    }

    public void insertarCiclo(Ciclo elCiclo) throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(insertarCiclo);

            pstmt.setString(1, elCiclo.getNumero());
            pstmt.setInt(2, elCiclo.getAnnio());
            pstmt.setDate(3, (java.sql.Date) elCiclo.getFechaInicio());
            pstmt.setDate(4, (java.sql.Date) elCiclo.getFechaFin());

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

    public void actualizaCiclo(Ciclo elCiclo) throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(actualizaCiclo);
            pstmt.setString(1, elCiclo.getNumero());
            pstmt.setInt(2, elCiclo.getAnnio());
            pstmt.setDate(3, (java.sql.Date) elCiclo.getFechaInicio());
            pstmt.setDate(4, (java.sql.Date) elCiclo.getFechaFin());

            int resultado = pstmt.executeUpdate();

            //si es diferente de 0 es porq si afecto un registro o mas
            if (resultado != 0) {
                throw new NoDataException("No se realizo la actualizacion");
            } else {
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

    public void eliminarCiclo(String numero) throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(eliminarCiclo);
            pstmt.setString(1, numero);

            int resultado = pstmt.executeUpdate();

            if (resultado != 0) {
                throw new NoDataException("No se realizo el borrado");
            } else {
                System.out.println("\nEliminacion Satisfactoria!");
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

    public ArrayList listarCiclos() throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();

         Connection c = dbConexion.getConnection();
        ResultSet rs = null;
        ArrayList<Ciclo> coleccion = new ArrayList<>();
        Ciclo elCiclo = null;
        CallableStatement pstmt = null;
        
        try {
            pstmt = conexion.prepareCall(listarCiclos);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elCiclo = new Ciclo(
                        rs.getInt("ID_CICLO"),
                        rs.getString("NUMERO"),
                        rs.getInt("ANIO"),
                        rs.getDate("FECHA_INICIO"),
                        rs.getDate("FECHA_FIN"));
                       
             
                coleccion.add(elCiclo);
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
        if (coleccion == null || coleccion.isEmpty()) {
            throw new NoDataException("No hay datos");
        }
        return coleccion;
    }

    public Ciclo consultarCiclo(int numero) throws GlobalException, NoDataException {

        Connection conexion = dbConexion.getConnection();
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Ciclo elCiclo = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(consultarCiclo);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, numero);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elCiclo = new Ciclo(
                        rs.getInt("ID_CICLO"),
                        rs.getString("NUMERO"),
                        rs.getInt("ANIO"),
                        rs.getDate("FECHA_INICIO"),
                        rs.getDate("FECHA_FIN"));
                coleccion.add(elCiclo);
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
        return elCiclo;
    }
    
    
      public static void main(String[] args) throws GlobalException, NoDataException {
     //Date fecha = 28-11-1998;
     ServicioCiclo sc = new ServicioCiclo();
     
     //sa.insertarAlumno(a);  // 
     //sa.eliminarAlumno("111929292");
     //
     
     //System.out.println(sc.consultarCiclo(1));  
     //System.out.println(sc.listarCiclos());  
     
     
     
     }

}


