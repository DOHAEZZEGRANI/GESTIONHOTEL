package org.xproce.gestiondereservation_hotel.service;

import org.springframework.data.domain.Page;
import org.xproce.gestiondereservation_hotel.Dao.entities.Reservation;
import org.xproce.gestiondereservation_hotel.Dao.entities.Room;

import java.util.List;

public interface RoomService {

    List<Room> getAllRoom();
    Room addRoom(Room room);
    Room updateRoom(Room room);
    Boolean deleteRoom(Room room);
    Room getRoomById(Integer id);
    Boolean deleteRoomById(Integer id);

    Page<Room> searchRoom(String keyword, int page, int taille);
}
