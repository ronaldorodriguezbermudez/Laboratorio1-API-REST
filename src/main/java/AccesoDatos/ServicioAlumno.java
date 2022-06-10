package AccesoDatos;

import Excepciones.GlobalException;
import Excepciones.NoDataException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import LogicaNegocio.Alumno;
import java.io.IOException;
import java.sql.Connection;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;


public class ServicioAlumno {

    private static final String insertarAlumno = "{call insertarAlumno (?,?,?,?,?,?)}";
    private static final String actualizaAlumno = "{call actualizaAlumno (?,?,?,?,?,?)}";
    private static final String eliminarAlumno = "{call eliminarAlumno(?)}";
    private static final String listarAlumnos = "{?=call listarAlumnos()}";
    private static final String consultarAlumno = "{?=call consultarAlumno(?)}";

    public ServicioAlumno() {

    }

  
    public void insertarAlumno(Alumno elAlumno) throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();
        CallableStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            pstmt = conexion.prepareCall(insertarAlumno);

            pstmt.setString(1, elAlumno.getCedula());
            pstmt.setString(2, elAlumno.getNombre());
            pstmt.setString(3, elAlumno.getTelefono());
            pstmt.setString(4, elAlumno.getEmail());
            pstmt.setString(5, elAlumno.getFechaNacimiento());
            pstmt.setString(6, elAlumno.getCarrera());

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

    public void modificarAlumno(Alumno elAlumno) throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = conexion.prepareStatement(actualizaAlumno);
            pstmt.setString(1, elAlumno.getCedula());
            pstmt.setString(2, elAlumno.getNombre());
            pstmt.setString(3, elAlumno.getTelefono());
            pstmt.setString(4, elAlumno.getEmail());
            pstmt.setString(5, elAlumno.getFechaNacimiento());
            pstmt.setString(6, elAlumno.getCarrera());

            int resultado = pstmt.executeUpdate();

            //si es diferente de 0 es porq si afecto un registro o mas
            if (resultado == 0) {
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

    public void eliminarAlumno(String id) throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();
        PreparedStatement pstmt = null;
        
        try {
            pstmt = conexion.prepareStatement(eliminarAlumno);
            pstmt.setString(1, id);

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
                
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }

    public ArrayList listarAlumnos() throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();
        ResultSet rs = null;
        ArrayList<Alumno> coleccion = new ArrayList<>();
        Alumno elAlumno = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(listarAlumnos);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elAlumno = new Alumno(
                        rs.getString("ID_ALUMNO"),
                        rs.getString("NOMBRE"),
                        rs.getString("TELEFONO"),
                        rs.getString("EMAIL"),
                        rs.getString("FECHA_NACIMIENTO"),
                        rs.getString("CARRERA"));
                coleccion.add(elAlumno);
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

    

    public Alumno buscarAlumno(String id) throws GlobalException, NoDataException {

        Connection conexion = dbConexion.getConnection();
        
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Alumno elAlumno = null;
        CallableStatement pstmt = null;
        try {
            pstmt = conexion.prepareCall(consultarAlumno);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                        elAlumno = new Alumno(
                        rs.getString("ID_ALUMNO"),
                        rs.getString("NOMBRE"),
                        rs.getString("TELEFONO"),
                        rs.getString("EMAIL"),
                        rs.getString("FECHA_NACIMIENTO"),
                        rs.getString("CARRERA"));
                coleccion.add(elAlumno);
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
        return elAlumno;
    }
   public static void main(String[] args) throws GlobalException, NoDataException {

     Alumno a = new Alumno("sa","aaaaa","sa","sa","28-11-1998","EIF");
     ServicioAlumno sa = new ServicioAlumno();
     //sa.insertarAlumno(a);  // 
     //sa.eliminarAlumno("sa");
     //sa.modificarAlumno(a);
     
     
     //System.out.println(sa.listarAlumnos());  
     //System.out.println(sa.buscarAlumno("sa"));  
     
     }
}
