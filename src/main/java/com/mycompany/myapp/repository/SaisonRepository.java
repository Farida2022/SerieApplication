package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Saison;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Saison entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SaisonRepository extends JpaRepository<Saison, Long> {}
