package Control;

import Excepciones.NoDataException;
import Modelo.ModeloProfesor;
import LogicaNegocio.Profesor;
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

@Path("/profesor")
public class ProfesorControl {
    



@GET
@Path("/listar")
@Produces(MediaType.APPLICATION_JSON)
public String listarProfesor()throws NoDataException{
    ModeloProfesor mp = new ModeloProfesor();
    String m = new Gson().toJson(mp.listarProfesor());
    return m;
   
}

@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public void agregarProfesor(Profesor p)throws NoDataException{
    ModeloProfesor mp = new ModeloProfesor();
    mp.insertar(p);
}

@DELETE
@Path("/{id_profesor}")
public void eliminarProfesor(@PathParam("id_profesor") String id)throws NoDataException{
    ModeloProfesor mp = new ModeloProfesor();
    mp.eliminar(id);
}


@PUT
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public void actualizarProfesor(Profesor p)throws NoDataException{
    ModeloProfesor mp = new ModeloProfesor();
    mp.modificar(p);
}

@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/{id_profesor}")
public Profesor buscarProfesor(@PathParam("id_profesor") String id)throws NoDataException{
    ModeloProfesor mp = new ModeloProfesor();
    return mp.consultar(id);
}
                                                 


    public static void main(String args[]) {

        Profesor p = new Profesor("sha", "sasha", "sa", "sa");
        ModeloProfesor mp = new ModeloProfesor();

        //System.out.println(mC.consultar(c.getCodigo()));
       // mC.insertar(c);
        //mC.eliminar(c);
        mp.modificar(p);
        //mC.eliminar(c);
        //mC.modificar(c);
        //System.out.println(mC.consultar("GEO"));

        //System.out.println(mC.listarCarrera());
        //System.out.println(mC.consultar(c.getCodigo()));
        //mC.insertar(c1);
    }







}
