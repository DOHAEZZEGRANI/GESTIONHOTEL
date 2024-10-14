package org.xproce.gestiondereservation_hotel.Dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.gestiondereservation_hotel.Dao.entities.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType,Integer> {
}
