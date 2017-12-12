package co.simplon.PoleEmploi.listecourses.endpoint;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.simplon.PoleEmploi.listecourses.dao.ListeCoursesDao;
import co.simplon.PoleEmploi.listecourses.dao.ProduitsDao;
import co.simplon.PoleEmploi.listecourses.modele.ListeCourses;
import co.simplon.PoleEmploi.listecourses.modele.Produits;

@Path("/listecourses")
@RequestScoped
public class ListeCoursesResource {

	private static int DEFAULT_PAGE_SIZE = 10;

	@Inject
	private ListeCoursesDao listeCoursesDao;

	@GET
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
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
}
