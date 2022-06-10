/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Excepciones.GlobalException;
import Excepciones.NoDataException;
import LogicaNegocio.Carrera;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

public class ServicioCarrera{

    private static final String insertarCarrera = "{call insertarCarrera(?,?,?)}";
    private static final String actualizaCarrera = "{call actualizaCarrera(?,?,?)}";
    private static final String eliminarCarrera = "{call eliminarCarrera(?)}";
    private static final String listarCarreras = "{?=call listarCarreras()}";
    private static final String consultarCarrera = "{?=call consultarCarrera(?)}";

    public ServicioCarrera() {

    }

    public void insertarCarrera(Carrera laCarrera) throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();
        CallableStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conexion.prepareCall(insertarCarrera);
            pstmt.setString(1, laCarrera.getCodigo());
            pstmt.setString(2, laCarrera.getNombre());
            pstmt.setString(3, laCarrera.getTitulo());

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
                //conexion.close();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    public void modificarCarrera(Carrera laCarrera) throws GlobalException, NoDataException {

        Connection c = dbConexion.getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = c.prepareStatement(actualizaCarrera);
            pstmt.setString(1, laCarrera.getCodigo());
            pstmt.setString(2, laCarrera.getNombre());
            pstmt.setString(3, laCarrera.getTitulo());

            int resultado = pstmt.executeUpdate();

            //si es diferente de 0 es porq si afecto un registro o mas
            if (resultado != 0) {
                throw new NoDataException("Se realizo la actualizacion");
            } else {
                System.out.println("\nModificaci�n falló!");
            }
        } catch (SQLException e) {
            throw new GlobalException("Sentencia no valida");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                //c.close();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    
    public ArrayList listarCarreras() throws GlobalException, NoDataException {

        Connection c = dbConexion.getConnection();
        ResultSet rs = null;
        ArrayList<Carrera> coleccion = new ArrayList<>();
        Carrera laCarrera = null;
        CallableStatement pstmt = null;
        
        try {
            pstmt = c.prepareCall(listarCarreras);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                laCarrera = new Carrera(
                        rs.getString("codigo_car"),
                        rs.getString("nombre"),
                        rs.getString("titulo")
                );
                coleccion.add(laCarrera);
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
        if (coleccion == null || coleccion.isEmpty()) {
         throw new NoDataException("No hay datos");
         }
        return coleccion;
    }

    

    public Carrera buscarCarrera(String id) throws GlobalException, NoDataException {
        Connection c = dbConexion.getConnection();
        ResultSet rs = null;
        Carrera laCarrera = null;
        CallableStatement pstmt = null;
        try {
            pstmt = c.prepareCall(consultarCarrera);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                laCarrera = new Carrera(
                        rs.getString("codigo_car"),
                        rs.getString("nombre"),
                        rs.getString("titulo"));
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
                //c.close();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
        return laCarrera;
    }

    public void eliminarCarrera(String laCarrera) throws GlobalException, NoDataException {

        Connection c = dbConexion.getConnection();

        PreparedStatement pstmt = null;
        try {
            pstmt = c.prepareStatement(eliminarCarrera);
            pstmt.setString(1, laCarrera);

            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
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
                //c.close();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
    
    
    
        public static void main(String[] args) throws GlobalException, NoDataException {
        // TODO code application logic here
        ServicioCarrera sc = new ServicioCarrera();
        //Carrera c = new Carrera();
        
       // System.out.println(sc.listarCarreras());
       // System.out.println(sc.buscarCarrera("GEO"));
       
       
        //Carrera c = new Carrera("GiO", "Geografia", "Diplomado");
//      Cuenta c1 = new Cuenta("963", "1234",  "nombre2", "apellido2","correo@aaa","54232","86732332","direcccion1","tipo1");*/
        //ModeloCarrera mC = new ModeloCarrera();
//        Cuenta cm = new Cuenta("123", "1234", "nombreMOD", "apellidoMOD","correo@dMODs","2323MOD2","23233M2","diMrecccion","tipMo");

        //mC.consultar(c.getCodigo());
        //sc.insertarCarrera(c);
        //sc.eliminarCarrera("Prueba");
        //sc.modificarCarrera(c);
        //System.out.println(sc.buscarCarrera(c.getCodigo()));
        //mC.consultar(c.getCodigo());

        System.out.println(sc.listarCarreras());
        //System.out.println(sc.buscarCarrera("GEO"));
        //mC.insertar(c1);
    }

}
