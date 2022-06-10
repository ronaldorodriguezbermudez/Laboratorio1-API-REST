/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;


public class Catalogo {
    
private int ID_CATALOGO;
private String CODIGO_CAR;
private String CODIGO_CUR;
private int ID_CICLO;
private int ANIO;
private String NUM_CICLO;

    static private Catalogo instancia = null;
    
    static public Catalogo getInstance(){
        if (instancia == null) {
             instancia = new Catalogo();
         }
         return instancia;
    }

    public Catalogo() {
    }

    public Catalogo(int ID_CATALOGO, String CODIGO_CAR, String CODIGO_CUR, int ID_CICLO, int ANIO, String NUM_CICLO) {
        this.ID_CATALOGO = ID_CATALOGO;
        this.CODIGO_CAR = CODIGO_CAR;
        this.CODIGO_CUR = CODIGO_CUR;
        this.ID_CICLO = ID_CICLO;
        this.ANIO = ANIO;
        this.NUM_CICLO = NUM_CICLO;
    }

    public int getID_CATALOGO() {
        return ID_CATALOGO;
    }

    public String getCODIGO_CAR() {
        return CODIGO_CAR;
    }

    public String getCODIGO_CUR() {
        return CODIGO_CUR;
    }

    public int getID_CICLO() {
        return ID_CICLO;
    }

    public int getANIO() {
        return ANIO;
    }

    public String getNUM_CICLO() {
        return NUM_CICLO;
    }

    public void setID_CATALOGO(int ID_CATALOGO) {
        this.ID_CATALOGO = ID_CATALOGO;
    }

    public void setCODIGO_CAR(String CODIGO_CAR) {
        this.CODIGO_CAR = CODIGO_CAR;
    }

    public void setCODIGO_CUR(String CODIGO_CUR) {
        this.CODIGO_CUR = CODIGO_CUR;
    }

    public void setID_CICLO(int ID_CICLO) {
        this.ID_CICLO = ID_CICLO;
    }

    public void setANIO(int ANIO) {
        this.ANIO = ANIO;
    }

    public void setNUM_CICLO(String NUM_CICLO) {
        this.NUM_CICLO = NUM_CICLO;
    }

    @Override
    public String toString() {
        return "Catalogo{" + "ID_CATALOGO=" + ID_CATALOGO + ", CODIGO_CAR=" + CODIGO_CAR + ", CODIGO_CUR=" + CODIGO_CUR + ", ID_CICLO=" + ID_CICLO + ", ANIO=" + ANIO + ", NUM_CICLO=" + NUM_CICLO + '}';
    }
    
   


}

