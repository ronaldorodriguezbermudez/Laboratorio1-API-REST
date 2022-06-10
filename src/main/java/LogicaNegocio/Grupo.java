package LogicaNegocio;

public class Grupo {

    private int idGrupo;
    private int numero;
    private String horario;
    private int ciclo;
    private String curso;
    private String profesor;

    static private Grupo instancia = null;
    
    static public Grupo getInstance(){
        if (instancia == null) {
             instancia = new Grupo();
         }
         return instancia;
    }

    public Grupo() {
    }

    public Grupo(int idGrupo, int ciclo ,String curso,int numero, String horario,  String profesor) {
        this.idGrupo = idGrupo;
        this.numero = numero;
        this.horario = horario;
        this.ciclo = ciclo;
        this.curso = curso;
        this.profesor = profesor;
    }

    public Grupo(int numero, String horario, int ciclo, String curso, String profesor) {
        this.numero = numero;
        this.horario = horario;
        this.ciclo = ciclo;
        this.curso = curso;
        this.profesor = profesor;
    }



    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCiclo() {
        return ciclo;
    }

  

    public String getCurso() {
        return curso;
    }

    public String getProfesor() {
        return profesor;
    }

    @Override
    public String toString() {
        return "Grupo{" + "idGrupo=" + idGrupo + ", numero=" + numero + ", horario=" + horario + ", ciclo=" + ciclo + ", curso=" + curso + ", profesor=" + profesor + '}';
    }
    

}
