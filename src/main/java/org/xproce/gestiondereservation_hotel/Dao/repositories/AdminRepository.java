package org.xproce.gestiondereservation_hotel.Dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.gestiondereservation_hotel.Dao.entities.Admin;


public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Page<Admin> findByDescriptionContains(String keyword, Pageable pageable);

}
