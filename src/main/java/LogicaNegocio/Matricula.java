/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;



/**
 *
 * @author Ronaldo
 */
public class Matricula {

    private int ID_MATRICULA;
    private int ID_GRUPO;
    private String ID_ALUMNO;
    private float NOTA;

    public Matricula(int ID_MATRICULA, int ID_GRUPO, String ID_ALUMNO, float NOTA) {
        this.ID_MATRICULA = ID_MATRICULA;
        this.ID_GRUPO = ID_GRUPO;
        this.ID_ALUMNO = ID_ALUMNO;
        this.NOTA = NOTA;
    }

    static private Matricula instancia = null;
    
    static public Matricula getInstance(){
        if (instancia == null) {
             instancia = new Matricula();
         }
         return instancia;
    }

    public Matricula() {
    }

    public int getID_MATRICULA() {
        return ID_MATRICULA;
    }

    public int getID_GRUPO() {
        return ID_GRUPO;
    }

    public String getID_ALUMNO() {
        return ID_ALUMNO;
    }

    public float getNOTA() {
        return NOTA;
    }

    public void setID_MATRICULA(int ID_MATRICULA) {
        this.ID_MATRICULA = ID_MATRICULA;
    }

    public void setID_GRUPO(int ID_GRUPO) {
        this.ID_GRUPO = ID_GRUPO;
    }

    public void setID_ALUMNO(String ID_ALUMNO) {
        this.ID_ALUMNO = ID_ALUMNO;
    }

    public void setNOTA(float NOTA) {
        this.NOTA = NOTA;
    }

    @Override
    public String toString() {
        return "Matricula{" + "ID_MATRICULA=" + ID_MATRICULA + ", ID_GRUPO=" + ID_GRUPO + ", ID_ALUMNO=" + ID_ALUMNO + ", NOTA=" + NOTA + '}';
    }
    
    
    
    
    
}
