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
import javax.ws.rs.core.MediaType;
 

import co.simplon.PoleEmploi.listecourses.modele.ListeCourses;

@Path("/listecourses")
@RequestScoped
public class ListeCoursesResource {

	@Inject
	private EntityManager entityManager;

	@GET
	@javax.ws.rs.Produces(MediaType.APPLICATION_JSON)
	public List<ListeCourses> getListeCourse() {
		List<ListeCourses> courses = entityManager.createNamedQuery("Course.findAll", ListeCourses.class).getResultList();
		return courses;
	}
}


