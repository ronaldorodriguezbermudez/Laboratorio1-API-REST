/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Excepciones.GlobalException;
import Excepciones.NoDataException;
import LogicaNegocio.Carrera;
import LogicaNegocio.Grupo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Ronaldo
 */
public class ServicioGrupo {

    private static final String insertarGrupo = "{call insertarGrupo(?,?,?,?,?)}";
    private static final String actualizaGrupo = "{call actualizaGrupo(?,?,?,?,?,?)}";
    private static final String eliminarGrupo = "{call eliminarGrupo(?)}";
    private static final String listarGrupos = "{?=call listarGrupos()}";
    private static final String consultarGrupo = "{?=call consultarGrupo(?)}";

    public ServicioGrupo() {

    }

    public void insertarGrupo(Grupo elGrupo) throws GlobalException, NoDataException {
        Connection conexion = dbConexion.getConnection();
        CallableStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conexion.prepareCall(insertarGrupo);
            //pstmt.setInt(1, elGrupo.getIdGrupo());
            pstmt.setInt(1, elGrupo.getCiclo());
            pstmt.setString(2, elGrupo.getCurso());
            pstmt.setInt(3, elGrupo.getNumero());
            pstmt.setString(4, elGrupo.getHorario());
            pstmt.setString(5, elGrupo.getProfesor());

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

    public void modificarGrupo(Grupo elGrupo) throws GlobalException, NoDataException {

        Connection c = dbConexion.getConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = c.prepareStatement(actualizaGrupo);
            pstmt.setInt(1, elGrupo.getIdGrupo());
            pstmt.setInt(2, elGrupo.getCiclo());
            pstmt.setString(3, elGrupo.getCurso());
            pstmt.setInt(4, elGrupo.getNumero());
            pstmt.setString(5, elGrupo.getHorario());
            pstmt.setString(6, elGrupo.getProfesor());;

            int resultado = pstmt.executeUpdate();

            //si es diferente de 0 es porq si afecto un registro o mas
            if (resultado == 0) {
                throw new NoDataException("No se realizo la actualizacion");
            } else {
                System.out.println("\nModificaci�n exito!");
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

    public ArrayList listarGrupos() throws GlobalException, NoDataException {

        Connection c = dbConexion.getConnection();
        ResultSet rs = null;
        ArrayList<Grupo> coleccion = new ArrayList<>();
        Grupo elGrupo = null;
        CallableStatement pstmt = null;

        try {
            pstmt = c.prepareCall(listarGrupos);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elGrupo = new Grupo(
                        rs.getInt("ID_GRUPO"),
                        rs.getInt("ID_CICLO"),
                        rs.getString("CODIGO_CUR"),
                        rs.getInt("NUMERO"),
                        rs.getString("HORARIO"),
                        rs.getString("ID_PROFESOR")
                );
                coleccion.add(elGrupo);
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

    public Grupo buscarGrupo(int id) throws GlobalException, NoDataException {
        Connection c = dbConexion.getConnection();
        ResultSet rs = null;
        Grupo elGrupo = null;
        CallableStatement pstmt = null;
        try {
            pstmt = c.prepareCall(consultarGrupo);
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.setInt(2, id);
            pstmt.execute();
            rs = (ResultSet) pstmt.getObject(1);
            while (rs.next()) {
                elGrupo = new Grupo(
                        rs.getInt("ID_GRUPO"),
                        rs.getInt("ID_CICLO"),
                        rs.getString("CODIGO_CUR"),
                        rs.getInt("NUMERO"),
                        rs.getString("HORARIO"),
                        rs.getString("ID_PROFESOR")
                );
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
        return elGrupo;
    }

    public void eliminarGrupo(int ID_GRUPO) throws GlobalException, NoDataException {
        Connection c = dbConexion.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = c.prepareStatement(eliminarGrupo);
            pstmt.setInt(1, ID_GRUPO);

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
        ServicioGrupo sg = new ServicioGrupo();

        //Carrera c = new Carrera();
        Grupo g = new Grupo(4, 3, "LIC101", 1, "L y J 8:00-10:40", "115273388");
        sg.insertarGrupo(g);
        //System.out.println(sg.listarGrupos());
        //System.out.println(sg.buscarGrupo(8));
        //sg.eliminarGrupo(4);
        //sg.modificarGrupo(g);

    }

}
