package co.simplon.PoleEmploi.listecourses.dao;

import java.util.List;

import co.simplon.PoleEmploi.listecourses.modele.Produits;

public interface ProduitsDao {
	List<Produits> findAll(int first, int size);
	
	List<Produits> findAllForListeCoursesId(Long idListeCourses, int first, int size);
	
	Produits getProduitsById(Long id);

	void deleteProduitsById(Long id);
	
	Produits updateProduits(Produits produits);
	
	Produits createProduitsForListeCourses(Produits produits, Long id);
}
