package co.simplon.PoleEmploi.listecourses.endpoint;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import co.simplon.PoleEmploi.listecourses.dao.ProduitsDao;
import co.simplon.PoleEmploi.listecourses.modele.Produits;

@Path("/produits")
@RequestScoped
public class ProduitsResource {

	private static int DEFAULT_PAGE_SIZE = 2;

	@Inject
	private ProduitsDao produitsDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produits> getProduits(@QueryParam("from") Integer from,
			@QueryParam("limit") Integer limit) {
		if (from == null) {
			from = 0;
		}
		if (limit == null) {
			limit = DEFAULT_PAGE_SIZE;
		}
		List<Produits> produits = produitsDao.findAll(from, limit);
		return produits;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduitsById(@PathParam("id") Long id) {
		Produits produits = produitsDao.getProduitsById(id);
		if (produits != null)
			return Response.ok(produits).build();
		return Response.status(Status.NOT_FOUND).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProduits(@PathParam("id") Long id, Produits produitsAModifier) {
		produitsAModifier.setId(id);
		produitsDao.updateProduits(produitsAModifier);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public void deleteProduitsById(@PathParam("id") Long id) {
		produitsDao.deleteProduitsById(id);
	}
}
