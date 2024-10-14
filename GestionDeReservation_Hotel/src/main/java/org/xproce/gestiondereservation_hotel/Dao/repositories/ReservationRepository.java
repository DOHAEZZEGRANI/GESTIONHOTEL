package org.xproce.gestiondereservation_hotel.Dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.gestiondereservation_hotel.Dao.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
}
