package co.simplon.PoleEmploi.listecourses.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PRODUITS")
@NamedQueries({
		@NamedQuery(name = "Produits.findAll", query = " SELECT p FROM Produits p ORDER BY p.libelle "),
		@NamedQuery(name = "Produits.findAllByListecoursesId", query = " SELECT p FROM Produits p JOIN p.listeCourses c WHERE c.id = :id ORDER BY p.libelle "),
		@NamedQuery(name = "Produits.deleteById", query = " DELETE FROM Produits p WHERE p.id = :id") })
public class Produits {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "LIBELLE", nullable = false, length = 30)
	private String libelle;

	@Column(name = "RAYON", nullable = false, length = 30)
	private String rayon;

	@Column(name = "QUANTITE", nullable = false)
	private Integer quantite;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ListeCourses_ID")
	private ListeCourses listeCourses;

	public Produits(String libelle) {
		super();
		this.libelle = libelle;
	}

	public Produits() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getRayon() {
		return rayon;
	}

	public void setRayon(String rayon) {
		this.rayon = rayon;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public ListeCourses getListeCourses() {
		return listeCourses;
	}

	public void setListeCourses(ListeCourses listeCourses) {
		this.listeCourses = listeCourses;
	}

	@Override
	public String toString() {
		return "Produits [id=" + id + ", libelle=" + libelle + ", rayon=" + rayon + ", quantite=" + quantite
				+ ", listeCourses=" + listeCourses + "]";
	}

	
}
