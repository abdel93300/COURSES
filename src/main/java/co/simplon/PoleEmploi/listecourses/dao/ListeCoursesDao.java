package co.simplon.PoleEmploi.listecourses.dao;

import java.util.List;

import co.simplon.PoleEmploi.listecourses.modele.ListeCourses;

public interface ListeCoursesDao {
	List<ListeCourses> findAll(int first, int size);
	
	ListeCourses getListeCoursesById(Long id);

	void deleteListeCoursesById(Long id);

	ListeCourses createListeCourses(ListeCourses listeCourses);
	
	ListeCourses updateListeCourses(ListeCourses listeCourses);
}
