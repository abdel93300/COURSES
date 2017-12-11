package co.simplon.PoleEmploi.listecourses.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import co.simplon.PoleEmploi.listecourses.modele.Produits;
import co.simplon.PoleEmploi.listecourses.modele.ListeCourses;

@Named
public class ProduitsJpaDao implements ProduitsDao {

	@Inject
	private EntityManager entityManager;

	public ProduitsJpaDao() {
		super();
	}
	
	@Override
	public List<Produits> findAll(int first, int size) {
		return entityManager
				.createNamedQuery("Produits.findAll", Produits.class)
				.setFirstResult(first).setMaxResults(size).getResultList();
	}

	@Override
	public List<Produits> findAllForListeCoursesId(Long idListeCourses, int first, int size) {
		return entityManager
				.createNamedQuery("Produits.findAllByListeCoursesId", Produits.class)
				.setParameter("id", idListeCourses).setFirstResult(first)
				.setMaxResults(size).getResultList();
	}

	@Override
	public Produits getProduitsById(Long id) {
		return entityManager.find(Produits.class, id);
	}

	@Override
	public void deleteProduitsById(Long id) {
		entityManager.getTransaction().begin();
		entityManager.createNamedQuery("P.deleteById")
				.setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
	}

	@Override
	public Produits updateProduits(Produits produits) {
		entityManager.getTransaction().begin();
		produits = entityManager.merge(produits);
		entityManager.getTransaction().commit();
		return produits;
	}

	@Override
	public Produits createProduitsForListeCourses(Produits produits, Long id) {
		entityManager.getTransaction().begin();
		ListeCourses listeCourses = entityManager.find(ListeCourses.class, id);
    	produits.setListeCourses(listeCourses);
		entityManager.persist(produits);
		entityManager.getTransaction().commit();
		return produits;
	}


	}



