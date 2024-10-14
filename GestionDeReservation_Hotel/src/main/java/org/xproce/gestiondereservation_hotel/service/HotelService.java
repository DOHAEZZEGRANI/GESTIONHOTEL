package org.xproce.gestiondereservation_hotel.service;

import org.springframework.data.domain.Page;
import org.xproce.gestiondereservation_hotel.Dao.entities.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotel();
    Hotel addHotel(Hotel hotel);
    Hotel updateHotel(Hotel hotel);
    Boolean deleteHotel(Hotel hotel);
    Hotel getHotelById(Integer id);
    Boolean deleteHotelById(Integer id);
    public Page<Hotel> searchHotel(String keyword, int page, int taille);
    public List<Hotel> getByKeyword(String keyword);
}
