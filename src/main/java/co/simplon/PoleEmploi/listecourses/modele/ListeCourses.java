package co.simplon.PoleEmploi.listecourses.modele;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LISTECOURSES")
@NamedQueries({
		@NamedQuery(name = "LISTECOURSES.findAll", query = " SELECT v FROM Ville v ORDER BY v.nom "),
		@NamedQuery(name = "LISTECOURSES.deleteById", query = " DELETE FROM Ville v WHERE v.id = :id") })
public class ListeCourses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String nom;

	@Column(name = "LATITUDE")
	private Double latitude;

	@Column(name = "LONGITUDE")
	private Double longitude;

	@JsonIgnore
	@OneToMany(mappedBy = "ville", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Produits> monuments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
