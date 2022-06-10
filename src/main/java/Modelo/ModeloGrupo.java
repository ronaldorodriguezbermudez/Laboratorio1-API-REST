package Modelo;



import AccesoDatos.ServicioGrupo;
import Excepciones.GlobalException;
import Excepciones.NoDataException;
import LogicaNegocio.Grupo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloGrupo extends Observable {

    ServicioGrupo sg;

    public ModeloGrupo() {
        sg = new ServicioGrupo();
    }

    public void insertar(Grupo g) {
        try {
            sg.insertarGrupo(g);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }

    public Grupo consultar(int id) {
        Grupo aux = null;
        try {
            aux = sg.buscarGrupo(id);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
        return aux;
    }



    public void eliminar(int c) {
        try {
            sg.eliminarGrupo(c);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }
    
    
    public Collection listarGrupo() {
        ArrayList c = null;
        try {
            c = (ArrayList) sg.listarGrupos();
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public void modificar(Grupo c) {
        try {
            sg.modificarGrupo(c);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloGrupo.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(null);
    }

    public static void main(String args[]) {


        ModeloGrupo mC = new ModeloGrupo();
        Grupo g = new Grupo(8, 2, "LIC101", 1, "L y J 8:00-10:40", "115273388");
    
        
        //System.out.println(mC.consultar(c.getCodigo()));
       //mC.insertar(g);
        //mC.eliminar(c);
        mC.modificar(g);
        //mC.eliminar(c);
        //mC.modificar(c);
        //System.out.println(mC.consultar("GEO"));

        //System.out.println(mC.listarCarrera());
        //System.out.println(mC.consultar(c.getCodigo()));
        //mC.insertar(c1);
    }

}
// insertar ok
// modificar ok
// Eliminar ok
// listar -
// consultar -