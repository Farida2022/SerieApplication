package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Serie.
 */
@Entity
@Table(name = "serie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Serie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "date_heure_ajout")
    private Instant dateHeureAjout;

    @ManyToOne
    @JsonIgnoreProperties(value = { "series", "episodes" }, allowSetters = true)
    private Saison saisons;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Serie id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Serie nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Instant getDateHeureAjout() {
        return this.dateHeureAjout;
    }

    public Serie dateHeureAjout(Instant dateHeureAjout) {
        this.setDateHeureAjout(dateHeureAjout);
        return this;
    }

    public void setDateHeureAjout(Instant dateHeureAjout) {
        this.dateHeureAjout = dateHeureAjout;
    }

    public Saison getSaisons() {
        return this.saisons;
    }

    public void setSaisons(Saison saison) {
        this.saisons = saison;
    }

    public Serie saisons(Saison saison) {
        this.setSaisons(saison);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Serie)) {
            return false;
        }
        return id != null && id.equals(((Serie) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Serie{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", dateHeureAjout='" + getDateHeureAjout() + "'" +
            "}";
    }
}
