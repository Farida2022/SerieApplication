package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Episode.
 */
@Entity
@Table(name = "episode")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Episode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "duree")
    private Integer duree;

    @OneToMany(mappedBy = "episodes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "series", "episodes" }, allowSetters = true)
    private Set<Saison> saisons = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Episode id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Episode nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getDuree() {
        return this.duree;
    }

    public Episode duree(Integer duree) {
        this.setDuree(duree);
        return this;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Set<Saison> getSaisons() {
        return this.saisons;
    }

    public void setSaisons(Set<Saison> saisons) {
        if (this.saisons != null) {
            this.saisons.forEach(i -> i.setEpisodes(null));
        }
        if (saisons != null) {
            saisons.forEach(i -> i.setEpisodes(this));
        }
        this.saisons = saisons;
    }

    public Episode saisons(Set<Saison> saisons) {
        this.setSaisons(saisons);
        return this;
    }

    public Episode addSaison(Saison saison) {
        this.saisons.add(saison);
        saison.setEpisodes(this);
        return this;
    }

    public Episode removeSaison(Saison saison) {
        this.saisons.remove(saison);
        saison.setEpisodes(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Episode)) {
            return false;
        }
        return id != null && id.equals(((Episode) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Episode{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", duree=" + getDuree() +
            "}";
    }
}
