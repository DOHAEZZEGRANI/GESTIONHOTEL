package org.xproce.gestiondereservation_hotel.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xproce.gestiondereservation_hotel.Dao.entities.Reservation;
import org.xproce.gestiondereservation_hotel.Dao.entities.Room;
import org.xproce.gestiondereservation_hotel.service.ReservationService;
import org.xproce.gestiondereservation_hotel.service.RoomService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    private RoomService roomService;

    @PostMapping("/savereservation")
    public String ajouterReservation(Model model,
                                     @Valid Reservation reservation,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("reservation", reservation);
            return "ajouterreservation";
        }

        // Ajouter la réservation
        reservationService.addReservation(reservation);

        // Mettre à jour la disponibilité de la chambre
        Room room = reservation.getRoom();
        if (room != null) {
            System.out.println("Chambre trouvée : " + room.getRoomNumber());
            room.setAvailable(false); // Mettre à jour la disponibilité à false
            roomService.updateRoom(room); // Sauvegarder la mise à jour

            // Vérification si la mise à jour est bien effectuée
            Room updatedRoom = roomService.getRoomById(room.getId());
            System.out.println("Disponibilité de la chambre après mise à jour : " + updatedRoom.isAvailable());
        } else {
            System.out.println("Chambre non trouvée pour la réservation.");
        }

        return "redirect:/listreservation";
    }


    @PostMapping("/submitReservation")
    public String submitReservation(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("reservation", reservation);
            return "ajouterreservation"; // Ensure this template exists
        }
        reservationService.addReservation(reservation);
        return "redirect:/listreservation";
    }

    @GetMapping("indexlayoutreservation")
    public String acc() {
        return "redirect:/listreservation";
    }

    @GetMapping("/detailsreservation")
    public String details(Model model, @RequestParam(name = "id") Integer id) {
        Reservation reservation = reservationService.getReservationById(id);
        model.addAttribute("reservationWithDetails", reservation);
        return "/detailreservation";
    }

    @GetMapping("/deletereservation")
    public String deletereservation(@RequestParam(name = "id") Integer id) {
        if (reservationService.deleteReservationById(id)) {
            return "redirect:/listreservation";
        } else {
            return "error";
        }
    }

    @PostMapping("/ajouterreservationn")
    public String modifierreservationAction(Model model, @Valid Room room ,
                                            @RequestParam(name = "id") Integer id,
                                            @RequestParam(name = "name") String name,
                                            @RequestParam(name = "date") LocalDate date,
                                            @RequestParam(name = "startDate") LocalDate startDate,
                                            @RequestParam(name = "endDate") LocalDate endDate) {
        Reservation reservation = new Reservation();

            reservation.setEndDate(endDate);
            reservation.setName(name);
            reservation.setDate(date);
            reservation.setStartDate(startDate);
            reservation.setRoom(room);
            reservationService.updateReservation(reservation);
            return "redirect:/listreservation";

    }

    @GetMapping("/ajouterreservation")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());

        return "ajouterreservation";
    }

    @PostMapping("/ajouterrOnce")
    public String ajouterareservation(Model model,
                                      @Valid Reservation reservation,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("reservation", reservation);
            return "ajouterreservation";
        }
        reservationService.addReservation(reservation);
        return "redirect:/listreservation";
    }

    @GetMapping("/listreservation")
    public String listReservation(Model model
                                 ) {
        List<Reservation> reservations = reservationService.searchReservation();
        model.addAttribute("listreservation", reservations);

        return "layoutreservation";
    }

    @GetMapping("/editreservation")
    public String editreservationAction(Model model, @RequestParam(name = "id") Integer id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            model.addAttribute("reservationToBeUpdated", reservation);
            return "updatereservation";
        } else {
            return "error";
        }
    }
}
