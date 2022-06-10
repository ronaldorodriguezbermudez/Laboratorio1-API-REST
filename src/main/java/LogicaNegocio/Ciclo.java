package LogicaNegocio;


import java.util.Date;



public class Ciclo {
    private int idCiclo;
    private String numero;
    private int annio;
    private Date fechaInicio;
    private Date fechaFin;

    public Ciclo(int idCiclo, String numero, int annio, Date fechaInicio, Date fechaFin) {
        this.idCiclo = idCiclo;
        this.numero = numero;
        this.annio = annio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    
    static private Ciclo instancia = null;
    
    static public Ciclo getInstance(){
        if (instancia == null) {
             instancia = new Ciclo();
         }
         return instancia;
    }

    public Ciclo() {
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getAnnio() {
        return annio;
    }

    public void setAnnio(int annio) {
        this.annio = annio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Ciclo{" + "idCiclo=" + idCiclo + ", numero=" + numero + ", annio=" + annio + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + '}';
    }

    
}
