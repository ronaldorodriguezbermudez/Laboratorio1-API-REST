package AccesoDatos;


import Excepciones.GlobalException;
import Excepciones.NoDataException;
import LogicaNegocio.Carrera;
import LogicaNegocio.Curso;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import oracle.jdbc.internal.OracleTypes;

public class ServicioCurso{
    
    private static final String INSERTAR_CURSO = "{call insertarCurso(?,?,?,?)}";
    private static final String MODIFICAR_CURSO = "{call actualizaCurso(?,?,?,?)}";
    private static final String BUSCAR_CURSO = "{?=call consultarCurso(?)}";
    private static final String LISTAR_CURSO = "{?=call listarCursos()}";
    private static final String ELIMINAR_CURSO = "{call eliminarCurso(?)}";

    public ServicioCurso() {
    }

    /*Insertar alumnos*/
    public void insertar_curso(Curso elCurso) throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();
        CallableStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conexion.prepareCall(INSERTAR_CURSO);
            pstmt.setString(1, elCurso.getCodigo());
            pstmt.setString(2, elCurso.getNombre());
            pstmt.setInt(3, elCurso.getCreditos());
            pstmt.setInt(4, elCurso.getHorasSemanales());
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


    public void modificar_curso(Curso elCurso) throws GlobalException, NoDataException {
       
        Connection c = dbConexion.getConnection();
        PreparedStatement pstmt = null;
        
        try {
            pstmt =  c.prepareStatement(MODIFICAR_CURSO);
            pstmt.setString(1, elCurso.getCodigo());
            pstmt.setString(2, elCurso.getNombre());
            pstmt.setInt(3, elCurso.getCreditos());
            pstmt.setInt(4, elCurso.getHorasSemanales());
            
            int resultado = pstmt.executeUpdate();

            if (resultado == 0) {
                throw new NoDataException("no se realizo la actualización");
            } else {
                System.out.println("\nModificación con Exito!");
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

    /*Buscar alumnos*/
    public Curso buscar_curso(String id) throws GlobalException, NoDataException {

       Connection c = dbConexion.getConnection();
        ResultSet rs = null;
        Curso elCurso = null;
        CallableStatement pstmt = null;

        try {
            pstmt = c.prepareCall(BUSCAR_CURSO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setString(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elCurso = new Curso(
                        rs.getString("codigo_cur"),
                        rs.getString("nombre"),
                        rs.getInt("creditos"),
                        rs.getInt("horas_semanales"));
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
        return elCurso;
    }

    /*Listar alumnos*/
    public ArrayList listar_curso() throws GlobalException, NoDataException {
        Connection c = dbConexion.getConnection();
        ResultSet rs = null;
        ArrayList<Curso> coleccion = new ArrayList<>();
        Curso elCurso = null;
        CallableStatement pstmt = null;
        
        try {
            pstmt = c.prepareCall(LISTAR_CURSO);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elCurso = new Curso(
                        rs.getString("CODIGO_CUR"),
                        rs.getString("nombre"),
                        rs.getInt("creditos"),
                        rs.getInt("horas_semanales"));
                coleccion.add(elCurso);
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

    /*Eliminar alumnos*/
    public void eliminar_curso(String id) throws GlobalException, NoDataException {
        Connection c = dbConexion.getConnection();
        PreparedStatement pstmt = null;
        
        try {
            pstmt = c.prepareStatement(ELIMINAR_CURSO);
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
                //c.close();
            } catch (SQLException e) {
                throw new GlobalException("Estatutos invalidos o nulos");
            }
        }
    }
    
    public static void main(String[] args) throws GlobalException, NoDataException {
        // TODO code application logic here
        ServicioCurso sc = new ServicioCurso();
        //Carrera c = new Carrera();
        Curso c = new Curso("a","gghbfxb",800008,6);
        //sc.insertar_curso(c);
        //System.out.println(sc.buscar_curso(c.getCodigo()));
        //sc.insertarCarrera(c);
        sc.eliminar_curso("a");
        //sc.modificar_curso(c);
        //System.out.println(sc.buscar_curso("ACV303"));
        //System.out.println(sc.buscar_curso(c.getCodigo()));
        //sc.eliminar_curso("a");
       //System.out.println(sc.listar_curso());
        //System.out.println(sc.buscarCarrera("GEO"));
        //mC.insertar(c1);
    }
}

// inserta - eliminar - listar - buscar 
