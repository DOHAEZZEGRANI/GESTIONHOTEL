package org.xproce.gestiondereservation_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xproce.gestiondereservation_hotel.Dao.entities.RoomType;
import org.xproce.gestiondereservation_hotel.Dao.repositories.RoomTypeRepository;

import java.util.List;
@Service
public class RoomTypeManager implements  RoomTypeService {
    @Autowired
    RoomTypeRepository roomTypeRepository;
    @Override
    public List<RoomType> getAllRoomType() {
        try {
            return roomTypeRepository.findAll();
        }catch (Exception e){

            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public RoomType addRoomType(RoomType roomType) {

        try {

            return roomTypeRepository.save(roomType);
        }catch (Exception e){

            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public RoomType updateRoomType(RoomType roomType) {
        try {

            return roomTypeRepository.save(roomType);
        }catch (Exception e){

            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Boolean deleteRoomType(RoomType roomType) {
        try {
            roomTypeRepository.delete(roomType);
            return true;
        }catch (Exception e){

            System.out.println(e.getMessage());

            return false;
        }
    }

    @Override
    public RoomType getRoomTypeById(Integer id) {
        try {

            return roomTypeRepository.findById(id).get();
        }catch (Exception e){

            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Boolean deleteRoomTypeById(Integer id) {
        try {
            roomTypeRepository.deleteById(id);
            return true;
        }catch (Exception e){

            System.out.println(e.getMessage());

            return false;
        }
    }
}
