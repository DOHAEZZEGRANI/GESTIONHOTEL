package org.xproce.gestiondereservation_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xproce.gestiondereservation_hotel.Dao.entities.Hotel;
import org.xproce.gestiondereservation_hotel.Dao.repositories.HotelRepository;

import java.util.List;

@Service
public class HotelManager implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Page<Hotel> searchHotel(String keyword, int page, int taille) {
        return hotelRepository.findByDescriptionContains(keyword, (Pageable)PageRequest.of(page, taille));
    }

    @Override
    public List<Hotel> getAllHotel() {
        try{
            return hotelRepository.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        try{

            return hotelRepository.save(hotel);

        }catch (Exception e){

            System.out.println(e.getMessage());

            return null;
        }
    }
    @Override
    public Hotel updateHotel(Hotel hotel) {
        try{

            return hotelRepository.save(hotel);

        }catch (Exception e){

            System.out.println(e.getMessage());

            return null;
        }
    }
    @Override
    public Boolean deleteHotel(Hotel hotel) {
        try {
            hotelRepository.delete(hotel);
            return true;

            }catch (Exception e){

            System.out.println(e.getMessage());

            return false;
        }
    }

    @Override
    public Hotel getHotelById(Integer id) {
        try {
            return hotelRepository.findById(id).get();

        }catch (Exception e){

            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteHotelById(Integer id) {
        try {

            hotelRepository.deleteById(id);
            return true;

        }catch (Exception e){

            System.out.println(e.getMessage());

            return false;
        }
    }}
