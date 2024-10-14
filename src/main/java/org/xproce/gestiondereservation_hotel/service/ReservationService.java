package org.xproce.gestiondereservation_hotel.service;

import org.springframework.data.domain.Page;
import org.xproce.gestiondereservation_hotel.Dao.entities.Hotel;
import org.xproce.gestiondereservation_hotel.Dao.entities.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservation();

    Reservation addReservation(Reservation reservation);
    Reservation updateReservation(Reservation reservation);
    Boolean deleteReservation(Reservation reservation);
    Reservation getReservationById(Integer id);
    Boolean deleteReservationById(Integer id);


    public List<Reservation> searchReservation();
}
