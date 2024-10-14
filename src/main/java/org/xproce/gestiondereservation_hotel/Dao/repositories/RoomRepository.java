package org.xproce.gestiondereservation_hotel.Dao.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.gestiondereservation_hotel.Dao.entities.Hotel;
import org.xproce.gestiondereservation_hotel.Dao.entities.Room;
@Transactional
public interface RoomRepository extends JpaRepository <Room,Integer> {
    Page<Room> findByDescriptionContains(String keyword, Pageable pageable);


}
