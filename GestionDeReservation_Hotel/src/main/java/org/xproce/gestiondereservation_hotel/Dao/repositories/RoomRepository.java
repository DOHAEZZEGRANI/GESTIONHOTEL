package org.xproce.gestiondereservation_hotel.Dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.gestiondereservation_hotel.Dao.entities.Room;

public interface RoomRepository extends JpaRepository <Room,Integer> {
}
