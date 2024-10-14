package org.xproce.gestiondereservation_hotel.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xproce.gestiondereservation_hotel.Dao.entities.Reservation;
import org.xproce.gestiondereservation_hotel.service.ReservationService;

import java.time.LocalDate;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/savereservation")
    public String ajouterreservation(Model model,
                                     @Valid Reservation reservation  ,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterhotel";
        }
        reservationService.addReservation(reservation);
        return "redirect:/indexpage";
    }


    @GetMapping("indexlayoutreservation")
    public String acc() {
        return "redirect:/listreservation";
    }







    @GetMapping("/detailsreservation")
    public String details(Model model,
                          @RequestParam(name = "id") Integer id) {
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
    public String modifierreservationAction(Model model,
                                            @RequestParam(name = "id") Integer id,
                                            @RequestParam(name = "totalePrice") Double totalePrice,
                                            @RequestParam(name = "startDate") LocalDate startDate,
                                            @RequestParam(name = "endDate") LocalDate endDate) {
        Reservation reservation  = reservationService.getReservationById(id);
        if (reservation != null) {
            reservation.setEndDate(endDate);
            reservation.setStartDate(startDate);
            reservation.setTotalPrice(totalePrice);
            reservationService.updateReservation(reservation);
            return "redirect:/listreservation";
        } else {
            return "error";
        }
    }



    @GetMapping("/ajouterreservation")
    public String ajouterreservation(Model model) {
        model.addAttribute("Reservation", new Reservation());
        return "ajouterreservation";
    }



    @PostMapping("/ajouterrOnce")
    public String ajouterareservation(Model model,
                                      @Valid Reservation reservation   ,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterreclamation";
        }
        reservationService.addReservation(reservation);
        return "redirect:/listreservation";
    }



    @GetMapping("/listreservation")
    public String listreservation(Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "taille", defaultValue = "6") int taille,
                                  @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Reservation> reservation = reservationService.searchReservation(keyword, page, taille);
        model.addAttribute("listreservation", reservation.getContent());

        int[] pages = new int[reservation.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "layoutreservation";
    }



    @GetMapping("/editreservation")
    public String editreservationAction(Model model, @RequestParam(name = "id") Integer id) {
        Reservation reservation  = reservationService.getReservationById(id);
        if (reservation != null) {
            model.addAttribute("reservationToBeUpdated", reservation);
            return "updatereservation";
        } else {
            return "error";
        }
    }
}



