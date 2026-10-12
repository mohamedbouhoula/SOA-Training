package webservices;
import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/modules")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ModuleRestAPI {
    private static final ModuleBusiness moduleBusiness = new ModuleBusiness();
    private static final UniteEnseignementBusiness ueBusiness = new UniteEnseignementBusiness();

    @GET
    @Path("/list")
    public Response.GetAll(){
       return Response.status(200).entity(moduleBusiness.getAllModules()).build();

    }
    @Path("/new")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createModule(Module module){
        if (helper.addModule(moduleBusiness)){
            return Response.status(201).entity("ok").build();
        }
        else {
            return Response.status(400).entity("error").build();
        }
    }
    @GET
    @Path("/{matricule}")
    public Response getModule(@PathParam("matricule") String matricule) {
        Module m = moduleBusiness.getModuleByMatricule(matricule);
        if (m == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Module " + matricule + " introuvable").build();
        }
        return Response.ok(m).build();
    }
    @DELETE
    @Path("/{matricule}")
    public Response deleteModule(@PathParam("matricule") String matricule) {
        if (moduleBusiness.deleteModule(matricule)) {
            return Response.noContent().build(); // 204
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Module " + matricule + " introuvable").build
}
