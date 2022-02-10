package caldas.moises.restfull.resources;

import java.util.List;

import javax.inject.Inject;
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
import javax.ws.rs.core.Response.Status;

import caldas.moises.restfull.model.Pessoa;
import caldas.moises.restfull.model.PessoaRepository;

@Path("pessoa")
public class PessoasResource {
	@Inject
	PessoaRepository rep;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPessoa(Pessoa pessoa) {
		try {
			rep.add(pessoa);
			return Response.status(Response.Status.CREATED)
					.entity(pessoa).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pessoa> getList(){
		return rep.list();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa getPessoa(@PathParam("id") Integer id) {
		return rep.load(id);
	}
	
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePessoa(@PathParam("id") Integer id, Pessoa pessoa) {
		Pessoa sc = rep.load(id);
		
		if (sc == null)
			return Response.status(Status.NOT_FOUND).build();

		try {
			rep.update(pessoa);
			return Response.status(Status.OK).build();
		}catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePessoa(@PathParam("id") Integer id) {
		Pessoa sc = rep.load(id);
		
		if(sc == null)
			return Response.status(Status.NOT_FOUND).build();
		
		try {
			rep.remove(id);
			return Response.status(Status.OK).build();
		}catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}
