package org.xproce.gestiondereservation_hotel.service;

import org.springframework.data.domain.Page;
import org.xproce.gestiondereservation_hotel.Dao.entities.Room;
import org.xproce.gestiondereservation_hotel.Dao.entities.RoomType;

import java.util.List;

public interface RoomTypeService {
    static Page<RoomType> searchRoomType(String keyword, int page, int taille) {
        return null;
    }

    List<RoomType> getAllRoomType();
    RoomType addRoomType(RoomType roomType);
    RoomType updateRoomType(RoomType roomType);
    Boolean deleteRoomType(RoomType roomType);
    RoomType getRoomTypeById(Integer id);
    Boolean deleteRoomTypeById(Integer id);
}
