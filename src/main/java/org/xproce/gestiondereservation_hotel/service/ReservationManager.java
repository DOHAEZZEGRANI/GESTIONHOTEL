package org.xproce.gestiondereservation_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xproce.gestiondereservation_hotel.Dao.entities.Reservation;

import org.xproce.gestiondereservation_hotel.Dao.repositories.ReservationRepository;

import java.util.List;

@Service
public class ReservationManager implements ReservationService{
    @Autowired
    private ReservationRepository reservationRepository;




    @Override
    public List<Reservation> getAllReservation() {
        try {
            return reservationRepository.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());

            return null;
        }
    }


    @Override
    public Reservation addReservation(Reservation reservation) {
        try {
            return reservationRepository.save(reservation);
        }catch (Exception e){
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        try {
            return reservationRepository.save(reservation);
        }catch (Exception e){
            System.out.println(e.getMessage());

            return null;
        }
    }


    @Override
    public Boolean deleteReservation(Reservation reservation) {
        try {
             reservationRepository.delete(reservation);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());

            return false;
        }
    }

    @Override
    public Reservation getReservationById(Integer id) {
        try {
            return reservationRepository.findById(id).get();

        }catch (Exception e){
            System.out.println(e.getMessage());

            return null;
        }
    }


    @Override
    public Boolean deleteReservationById(Integer id) {
        try {
             reservationRepository.deleteById(id);
            return true;


        }catch (Exception e){
            System.out.println(e.getMessage());

            return false;
        }
    }


    @Override
    public List<Reservation> searchReservation() {
        return reservationRepository.findAll();
    }}



