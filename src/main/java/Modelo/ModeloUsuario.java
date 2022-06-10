/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import AccesoDatos.ServicioUsuario;
import Excepciones.GlobalException;
import Excepciones.NoDataException;
import LogicaNegocio.Carrera;
import LogicaNegocio.Usuario;
import LogicaNegocio.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Josué
 */
public class ModeloUsuario extends Observable{
    
    ServicioUsuario su = new ServicioUsuario();
    
    private static ModeloUsuario modelo;
    
    private ModeloUsuario(){
   
    }
    
    static public ModeloUsuario getInstance(){
        if (modelo == null) {
             modelo = new ModeloUsuario();
         }
         return modelo;
    }
    
    public void insertar(Usuario u) {
        try {
            su.insertarUsuario(u);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }
    
    public Usuario consultar(String id) {
        Usuario aux = null;
        try {
            aux = su.consultarUsuario(id);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
        return aux;
    }
    
    public void eliminar(String id) {
        try {
            su.eliminarUsuario(id);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        setChanged();
        notifyObservers(null);
    }
    
    public Collection listarCarrera() {
        Collection c = null;
        try {
            c = su.listarUsuarios();
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    
    public void modificar(Usuario u) {
        try {
            su.actualizarUsuario(u);
        } catch (GlobalException | NoDataException ex) {
            Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
    
}
