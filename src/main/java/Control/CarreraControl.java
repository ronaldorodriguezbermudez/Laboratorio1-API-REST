package Control;

import Excepciones.NoDataException;
import LogicaNegocio.Carrera;
import Modelo.ModeloCarrera;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/carrera")
public class CarreraControl {

@GET
@Path("/listar")
@Produces(MediaType.APPLICATION_JSON)
public String listarCarrera()throws NoDataException{
    ModeloCarrera mC = new ModeloCarrera();
    String m = new Gson().toJson(mC.listarCarrera());
    return m;
}

@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public void agregarCarrera(Carrera c)throws NoDataException{
    ModeloCarrera mC = new ModeloCarrera();
    mC.insertar(c);
}

@DELETE
@Path("/{codigo_car}")
public void eliminarCarrera(@PathParam("codigo_car") String id)throws NoDataException{
    ModeloCarrera mC = new ModeloCarrera();
    mC.eliminar(id);
}

@PUT
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public void actualizarCarrera(Carrera c)throws NoDataException{
    ModeloCarrera mC = new ModeloCarrera();
    mC.modificar(c);
}

@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/{codigo_car}")
public Carrera buscarCarrera(@PathParam("codigo_car") String id)throws NoDataException{
    ModeloCarrera mC = new ModeloCarrera();
    return mC.consultar(id);
}
                                                 


    public static void main(String args[]){
        
        Carrera c = new Carrera("Gi87O", "Gfia", "Diplomado");
        ModeloCarrera mC = new ModeloCarrera();
        //System.out.println(mC.consultar(c.getCodigo()));
        //mC.insertar(c);
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
