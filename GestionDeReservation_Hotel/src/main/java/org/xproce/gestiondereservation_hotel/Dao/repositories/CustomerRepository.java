package org.xproce.gestiondereservation_hotel.Dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.gestiondereservation_hotel.Dao.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Page<Customer> findByDescriptionContains(String keyword, Pageable pageable);
}
