package Control;

import Excepciones.NoDataException;
import LogicaNegocio.Grupo;
import Modelo.ModeloGrupo;

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

@Path("/grupo")
public class GrupoControl {

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public String listarGrupo() throws NoDataException {
        ModeloGrupo mg = new ModeloGrupo();
        String m = new Gson().toJson(mg.listarGrupo());
        return m;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void agregarGrupo(Grupo c) throws NoDataException {
        ModeloGrupo mg = new ModeloGrupo();
        mg.insertar(c);
    }

    @DELETE
    @Path("/{id_grupo}")
    public void eliminarGrupo(@PathParam("id_grupo") int id) throws NoDataException {
        ModeloGrupo mg = new ModeloGrupo();
        mg.eliminar(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void actualizarGrupo(Grupo c) throws NoDataException {
        ModeloGrupo mg = new ModeloGrupo();
        mg.modificar(c);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id_grupo}")
    public Grupo buscarGrupo(@PathParam("id_grupo") int id) throws NoDataException {
        ModeloGrupo mg = new ModeloGrupo();
        return mg.consultar(id);
    }

    public static void main(String args[]) {
        Grupo g = new Grupo(9, 3, "LIC101", 1, "L y J 8:00-10:40", "115273388");
        ModeloGrupo mg = new ModeloGrupo();
        
        //Carrera c = new Carrera("Gi87O", "Gfia", "Diplomado");
        // ModeloCarrera mC = new ModeloCarrera();
        //System.out.println(mC.consultar(c.getCodigo()));
        //mC.insertar(c);
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
