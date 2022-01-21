package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Saison.
 */
@Entity
@Table(name = "saison")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Saison implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "saisons")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "saisons" }, allowSetters = true)
    private Set<Serie> series = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "saisons" }, allowSetters = true)
    private Episode episodes;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Saison id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Saison nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Serie> getSeries() {
        return this.series;
    }

    public void setSeries(Set<Serie> series) {
        if (this.series != null) {
            this.series.forEach(i -> i.setSaisons(null));
        }
        if (series != null) {
            series.forEach(i -> i.setSaisons(this));
        }
        this.series = series;
    }

    public Saison series(Set<Serie> series) {
        this.setSeries(series);
        return this;
    }

    public Saison addSerie(Serie serie) {
        this.series.add(serie);
        serie.setSaisons(this);
        return this;
    }

    public Saison removeSerie(Serie serie) {
        this.series.remove(serie);
        serie.setSaisons(null);
        return this;
    }

    public Episode getEpisodes() {
        return this.episodes;
    }

    public void setEpisodes(Episode episode) {
        this.episodes = episode;
    }

    public Saison episodes(Episode episode) {
        this.setEpisodes(episode);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Saison)) {
            return false;
        }
        return id != null && id.equals(((Saison) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Saison{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
