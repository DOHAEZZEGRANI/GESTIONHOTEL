package org.xproce.gestiondereservation_hotel.Dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.gestiondereservation_hotel.Dao.entities.Facture;
import org.xproce.gestiondereservation_hotel.Dao.entities.Hotel;

public interface FactureRepository extends JpaRepository<Facture,Integer> {
    Page<Facture> findByDescriptionContains(String keyword, Pageable pageable);

}
