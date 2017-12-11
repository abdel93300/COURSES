package co.simplon.PoleEmploi.listecourses.modele;

import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LISTECOURSES")
@NamedQueries({
		@NamedQuery(name = "ListeCourses.findAll", query = " SELECT l FROM ListeCourses l ORDER BY l.dateAchat"),
		@NamedQuery(name = "ListeCourses.deleteById", query = " DELETE FROM ListeCourses l WHERE l.id = :id") })
public class ListeCourses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "ENSEIGNE")
	private String enseigne;

	@Column(name = "VILLE")
	private String ville;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "DATE_ACHAT")
	private Date dateAchat;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnseigne() {
		return enseigne;
	}

	public void setEnseigne(String enseigne) {
		this.enseigne = enseigne;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	
	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	@Override
	public String toString() {
		return "ListeCourses [id=" + id + ", enseigne=" + enseigne + ", ville=" + ville + "]";
	}

	
}
