package Modelo;

import AccesoDatos.ServicioAlumno;

import Excepciones.GlobalException;
import Excepciones.NoDataException;
import LogicaNegocio.Alumno;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloAlumno extends Observable {

    ServicioAlumno sa;

    public ModeloAlumno() {
        sa = new ServicioAlumno();
    }

    public void insertar(Alumno a) {
        try {
            sa.insertarAlumno(a);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }

    public Alumno consultar(String id) {
        Alumno aux = null;
        try {
            aux = sa.buscarAlumno(id);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
        return aux;
    }



    public void eliminar(String a) {
        try {
            sa.eliminarAlumno(a);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }
    
    
    public Collection listarAlumno() {
        ArrayList c = null;
        try {
            c = (ArrayList) sa.listarAlumnos();
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public void modificar(Alumno a) {
        try {
            sa.modificarAlumno(a);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloAlumno.class.getName()).log(Level.SEVERE, null, ex);
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
        Alumno c = new Alumno("saa","bbbbaa","sa","sa","28-11-1998","EIF");
        //Carrera c = new Carrera("Gi87O", "Gfia", "Diplomado");
        ModeloAlumno mC = new ModeloAlumno();

        //System.out.println(mC.consultar(c.getCodigo()));
       //mC.insertar(c);
        //mC.eliminar(c);
        //mC.modificar(c);
        //mC.eliminar(c.getCedula());
        //mC.modificar(c);
        //System.out.println(mC.consultar("saa"));

        //System.out.println(mC.listarAlumno());
        //System.out.println(mC.consultar(c.getCodigo()));
        //mC.insertar(c1);
    }

}
// insertar ok
// modificar ok
// Eliminar ok
// listar -
// consultar -