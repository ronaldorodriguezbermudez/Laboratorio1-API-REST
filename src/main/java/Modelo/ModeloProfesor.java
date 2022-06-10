package Modelo;

import AccesoDatos.ServicioProfesor;

import Excepciones.GlobalException;
import Excepciones.NoDataException;
import LogicaNegocio.Profesor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloProfesor extends Observable {

    ServicioProfesor sp;

    public ModeloProfesor() {
        sp = new ServicioProfesor();
    }

    public void insertar(Profesor p) {
        try {
            sp.insertar_profesor(p);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }

    public Profesor consultar(String id) {
        Profesor aux = null;
        try {
            aux = sp.buscar_profesor(id);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
        return aux;
    }

    public void eliminar(String p) {
        try {
            sp.eliminar_profesor(p);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }

    public Collection listarProfesor() {
        ArrayList c = null;
        try {
            c = (ArrayList) sp.listarProfesor();
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public void modificar(Profesor p) {
        try {
            sp.modificar_profesor(p);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloProfesor.class.getName()).log(Level.SEVERE, null, ex);
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

        Profesor p = new Profesor("sha", "s", "sa", "sa");
        //sp.insertar_profesor(p);  // ok
        //sp.eliminar_profesor("sa");  //ok
        //sp.modificar_profesor(p); //ok

        ModeloProfesor mp = new ModeloProfesor();
        
        mp.insertar(p);
        //System.out.println(mp.listarProfesor());
        //System.out.println(mp.consultar("sha"));
        //mp.modificar(p);
        //mp.eliminar("sha");
       
        
    }

}
// insertar ok
// modificar ok
// Eliminar ok
// listar - ok
// consultar - ok
