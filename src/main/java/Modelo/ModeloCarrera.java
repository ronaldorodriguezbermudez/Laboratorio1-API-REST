package Modelo;

import AccesoDatos.ServicioCarrera;

import Excepciones.GlobalException;
import Excepciones.NoDataException;
import LogicaNegocio.Carrera;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloCarrera extends Observable {

    ServicioCarrera sC;

    public ModeloCarrera() {
        sC = new ServicioCarrera();
    }

    public void insertar(Carrera c) {
        try {
            sC.insertarCarrera(c);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }

    public Carrera consultar(String id) {
        Carrera aux = null;
        try {
            aux = sC.buscarCarrera(id);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
        return aux;
    }



    public void eliminar(String c) {
        try {
            sC.eliminarCarrera(c);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }
    
    
    public Collection listarCarrera() {
        ArrayList c = null;
        try {
            c = (ArrayList) sC.listarCarreras();
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public void modificar(Carrera c) {
        try {
            sC.modificarCarrera(c);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloCarrera.class.getName()).log(Level.SEVERE, null, ex);
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

        Carrera c = new Carrera("Gi87O", "Gfia", "Diplomado");
        ModeloCarrera mC = new ModeloCarrera();

        //System.out.println(mC.consultar(c.getCodigo()));
       // mC.insertar(c);
        //mC.eliminar(c);
        //mC.modificar(c);
        //mC.eliminar(c);
        //mC.modificar(c);
        //System.out.println(mC.consultar("GEO"));

        System.out.println(mC.listarCarrera());
        //System.out.println(mC.consultar(c.getCodigo()));
        //mC.insertar(c1);
    }

}
// insertar ok
// modificar ok
// Eliminar ok
// listar -
// consultar -