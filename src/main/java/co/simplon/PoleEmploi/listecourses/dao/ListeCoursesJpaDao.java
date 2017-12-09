package co.simplon.PoleEmploi.listecourses.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import co.simplon.PoleEmploi.listecourses.modele.ListeCourses;

@Named
public class ListeCoursesJpaDao implements ListeCoursesDao {

	@Inject
	private EntityManager entityManager;

	public ListeCoursesJpaDao() {
		super();
	}

	@Override
	public ListeCourses getListeCoursesById(Long id) {
		return entityManager.find(ListeCourses.class, id);
	}

	@Override
	public void deleteListeCoursesById(Long id) {
		entityManager.getTransaction().begin();
		entityManager.createNamedQuery("ListeCourses.deleteById")
				.setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
	}

	@Override
	public ListeCourses createListeCourses(ListeCourses listeCourses) {
		entityManager.getTransaction().begin();
		entityManager.persist(listeCourses);
		entityManager.getTransaction().commit();
		return listeCourses;
	}

	@Override
	public ListeCourses updateListeCourses(ListeCourses listeCourses) {
		entityManager.getTransaction().begin();
		listeCourses = entityManager.merge(listeCourses);
		entityManager.getTransaction().commit();
		return listeCourses;
	}

	@Override
	public List<ListeCourses> findAll(int first, int size) {
		return entityManager.createNamedQuery("ListeCourses.findAll", ListeCourses.class)
				.setFirstResult(first).setMaxResults(size).getResultList();
	}

}
