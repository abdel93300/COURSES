package co.simplon.PoleEmploi.listecourses.endpoint;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import co.simplon.PoleEmploi.listecourses.dao.ProduitsDao;
import co.simplon.PoleEmploi.listecourses.dao.ListeCoursesDao;
import co.simplon.PoleEmploi.listecourses.modele.Produits;
import co.simplon.PoleEmploi.listecourses.modele.ListeCourses;

@Path("/listecourses")
@RequestScoped
public class ListeCoursesResource {

	private static int DEFAULT_PAGE_SIZE = 10;

	@Inject
	private ListeCoursesDao listeCoursesDao;

	@Inject
	private ProduitsDao produitsDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ListeCourses> getListeCourses(@QueryParam("from") Integer from,
			@QueryParam("limit") Integer limit) {
		if (from == null) {
			from = 0;
		}
		if (limit == null) {
			limit = DEFAULT_PAGE_SIZE;
		}
		List<ListeCourses> listeCourses = listeCoursesDao.findAll(from, limit);
		return listeCourses;
	}

	@GET
	@Path("{id}/produits")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produits> getProduitsByListeCourses(@PathParam("id") Long id,
			@QueryParam("from") Integer from, @QueryParam("limit") Integer limit) {
		if (from == null) {
			from = 0;
		}
		if (limit == null) {
			limit = DEFAULT_PAGE_SIZE;
		}
		List<Produits> produits = produitsDao.findAllForListeCoursesId(id, from,
				limit);
		return produits;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVilleById(@PathParam("id") Long id) {
		ListeCourses listeCourses = listeCoursesDao.getListeCoursesById(id);
		if (listeCourses != null)
			return Response.ok(listeCourses).build();
		return Response.status(Status.NOT_FOUND).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createListeCourses(ListeCourses listeCourses, @Context UriInfo uriInfo) {
		if (!isListeCoursesValid(villeACreer)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		ListeCourses ville = villeDao.createVille(villeACreer);
		if (ville != null) {
			URL url;
			URI uri;
			try {
				url = new URL(uriInfo.getAbsolutePath().toURL()
						.toExternalForm()
						+ "/" + ville.getId());
				uri = url.toURI();
			} catch (MalformedURLException e) {
				return Response.status(Status.BAD_REQUEST).build();
			} catch (URISyntaxException e) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			return Response.created(uri).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@POST
	@Path("{id}/produits")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProduits(@PathParam("id") Long id,
			Produits produitsACreer, @Context UriInfo uriInfo) {
		Produits produits = produitsDao.createProduitsForListeCourses(produitsACreer,
				id);
		if (produits != null) {
			URL url;
			URI uri;
			try {
				url = new URL(uriInfo.getAbsolutePath().toURL()
						.toExternalForm().replaceFirst("/listeCourses/[0-9]+/", "/")
						+ "/" + produits.getId());
				uri = url.toURI();
			} catch (MalformedURLException e) {
				return Response.status(Status.BAD_REQUEST).build();
			} catch (URISyntaxException e) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			return Response.created(uri).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateLisiteCourses(@PathParam("id") Long id, ListeCourses listeCoursesAModifier) {
		if (!isListeCoursesValid(listeCoursesAModifier)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		listeCoursesAModifier.setId(id);
		listeCoursesDao.updateListeCourses(listeCoursesAModifier);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public void deleteVilleById(@PathParam("id") Long id) {
		villeDao.deleteVilleById(id);
	}
	
	private boolean isListeCoursesValid(ListeCourses listeCourses) {
		boolean valid = true;
		valid &= (listeCourses.get != null);
		valid &= (listeCourses.getLongitude() != null);
		return valid;
	}

}
