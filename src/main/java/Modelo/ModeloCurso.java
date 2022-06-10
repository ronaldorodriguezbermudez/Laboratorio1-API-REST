package Modelo;


import AccesoDatos.ServicioCurso;

import Excepciones.GlobalException;
import Excepciones.NoDataException;
import LogicaNegocio.Curso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloCurso extends Observable {

    ServicioCurso sC;

    public ModeloCurso() {
        sC = new ServicioCurso();
    }

    public void insertar(Curso c) {
        try {
            sC.insertar_curso(c);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }

    public Curso consultar(String id) {
        Curso aux = null;
        try {
            aux = sC.buscar_curso(id);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
        return aux;
    }



    public void eliminar(String c) {
        try {
            sC.eliminar_curso(c);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }
    
    
    public Collection listarCurso() {
        ArrayList c = null;
        try {
            c = (ArrayList) sC.listar_curso();
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public void modificar(Curso c) {
        try {
            sC.modificar_curso(c);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloCurso.class.getName()).log(Level.SEVERE, null, ex);
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

        Curso c = new Curso("ba","guggs",88,6);
        ModeloCurso mC = new ModeloCurso();
        
        //System.out.println(mC.consultar(c.getCodigo()));
       //mC.insertar(c);
        //mC.eliminar(c);
        //mC.modificar(c);
        //mC.eliminar("ba");
        mC.modificar(c);
        //System.out.println(mC.consultar("GEO"));

        //System.out.println(mC.listarCurso());
       //System.out.println(mC.consultar(c.getCodigo()));
        //mC.insertar(c1);
    }

}
// insertar ok
// modificar 
// Eliminar ok
// listar -
// consultar -