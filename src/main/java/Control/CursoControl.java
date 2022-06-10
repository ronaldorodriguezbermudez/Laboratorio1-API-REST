package Control;

import Excepciones.NoDataException;
import LogicaNegocio.Curso;
import Modelo.ModeloCurso;

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

@Path("/curso")
public class CursoControl {
           
        
@GET
@Path("/art")
@Produces(MediaType.APPLICATION_JSON)
public Curso getCurso() {
    Curso c = new Curso("ba","guggs",88,6);
    return c;
}


@GET
@Path("/listar")
@Produces(MediaType.APPLICATION_JSON)
public String listarCurso()throws NoDataException{
    ModeloCurso mC = new ModeloCurso();
    String m = new Gson().toJson(mC.listarCurso());
    return m;
   
}

@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public void agregarCurso(Curso c)throws NoDataException{
    ModeloCurso mC = new ModeloCurso();
    mC.insertar(c);
}

@DELETE
@Path("/{codigo_cur}")
public void eliminarCurso(@PathParam("codigo_cur") String id)throws NoDataException{
    ModeloCurso mC = new ModeloCurso();
    mC.eliminar(id);
}


@PUT
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public void actualizarCurso(Curso c)throws NoDataException{
    ModeloCurso mC = new ModeloCurso();;
    mC.modificar(c);
}

@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("/{codigo_cur}")
public Curso buscarCurso(@PathParam("codigo_cur") String id)throws NoDataException{
    ModeloCurso mC = new ModeloCurso();
    return mC.consultar(id);
}
                                                 


    public static void main(String args[]) {

        Curso c = new Curso("ba","guggs",88,6);
        ModeloCurso mC = new ModeloCurso();;

        //System.out.println(mC.consultar(c.getCodigo()));
       // mC.insertar(c);
        //mC.eliminar(c);
        //mC.modificar(c);
        //mC.eliminar(c);
        //mC.modificar(c);
        //System.out.println(mC.consultar("GEO"));

        //System.out.println(mC.listarCarrera());
        //System.out.println(mC.consultar(c.getCodigo()));
        //mC.insertar(c1);
    }







}
