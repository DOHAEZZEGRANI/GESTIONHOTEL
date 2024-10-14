package org.xproce.gestiondereservation_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.xproce.gestiondereservation_hotel.Dao.entities.Room;
import org.xproce.gestiondereservation_hotel.Dao.repositories.RoomRepository;

import java.util.List;

@Service
public class RoomManager implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRoom() {
        try {
            return roomRepository.findAll();
        }catch (Exception e){

            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Room addRoom(Room room) {
        try {

            return roomRepository.save(room);
        }catch (Exception e){

            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Room updateRoom(Room room) {
        try {

            return roomRepository.save(room);
        }catch (Exception e){

            System.out.println(e.getMessage());

            return null;
        }
    }
    @Override
    public Boolean deleteRoom(Room room) {
        try {
             roomRepository.delete(room);
             return true;
        }catch (Exception e){

            System.out.println(e.getMessage());

            return false;
        }
    }

    @Override
    public Room getRoomById(Integer id) {
        try {

            return roomRepository.findById(id).get();
        }catch (Exception e){

            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Boolean deleteRoomById(Integer id) {
        try {
             roomRepository.deleteById(id);
            return true;
        }catch (Exception e){

            System.out.println(e.getMessage());

            return false;
        }
    }

    @Override
    public Page<Room> searchRoom(String keyword, int page, int taille) {
        return null;
    }
}
