package Control;

import Excepciones.NoDataException;
import LogicaNegocio.Alumno;
import Modelo.ModeloAlumno;


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
import javax.ws.rs.core.Response;




@Path("/alumno")
public class AlumnoControl {


    
    
    
@GET
@Path("/listar")
@Produces(MediaType.APPLICATION_JSON)
public String listarAlumno()throws NoDataException{
    ModeloAlumno ma = new ModeloAlumno();
    String m = new Gson().toJson(ma.listarAlumno());
    return m;
}

@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public void agregarAlumno(Alumno a)throws NoDataException{
    ModeloAlumno ma = new ModeloAlumno();
    ma.insertar(a);
}

@DELETE
@Path("/{id_alumno}")
public void eliminarAlumno(@PathParam("id_alumno") String id)throws NoDataException{
    ModeloAlumno ma = new ModeloAlumno();
    ma.eliminar(id);
}

@PUT
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public void actualizarAlumno(Alumno a)throws NoDataException{
    ModeloAlumno ma = new ModeloAlumno();
    ma.modificar(a);
}

@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/{id_alumno}")
public Alumno buscarAlumno(@PathParam("id_alumno") String id)throws NoDataException{
    ModeloAlumno ma = new ModeloAlumno();
    return ma.consultar(id);
}
                                                 


 






}
