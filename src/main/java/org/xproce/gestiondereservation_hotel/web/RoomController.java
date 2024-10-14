package org.xproce.gestiondereservation_hotel.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.xproce.gestiondereservation_hotel.Dao.entities.Hotel;
import org.xproce.gestiondereservation_hotel.Dao.entities.Reservation;
import org.xproce.gestiondereservation_hotel.Dao.entities.Room;
import org.xproce.gestiondereservation_hotel.service.HotelService;
import org.xproce.gestiondereservation_hotel.service.ReservationService;
import org.xproce.gestiondereservation_hotel.service.RoomService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;

    @PostMapping("/saveroom")
    public String ajouterRoom(Model model,
                              @Valid Room room,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterroom";
        }
        roomService.addRoom(room);
        return "redirect:/listroom";
    }

    @GetMapping("/detailsroom")
    public String details(Model model,
                          @RequestParam(name = "id") Integer id) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            model.addAttribute("RoomWithDetails", room);
            return "detailroom";
        } else {
            return "error";
        }
    }

    @GetMapping("/deleteroom")
    public String deleteRoom(@RequestParam(name = "id") Integer id) {
        if (roomService.deleteRoomById(id)) {
            return "redirect:/listroom";
        } else {
            return "error";
        }
    }

    @PostMapping("/ajouterroom")
    public String ajouterRoomAction(Model model,
                                    @ModelAttribute("room") @Valid Room room,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterroom";
        }
        roomService.addRoom(room);
        return "redirect:/listroom";
    }

    @GetMapping("/ajouterroom")
    public String showAjouterRoomForm(Model model) {
        model.addAttribute("room", new Room());
        List<Hotel> hotels=hotelService.getAllHotel();
        model.addAttribute("hotel",hotels) ;

        return "ajouterroom";
    }

    @GetMapping("/listroom")
    public String listRoom(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "6") int size,
                           @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Room> roomPage = roomService.searchRoom(keyword, page, size);
        model.addAttribute("listRoom", roomPage.getContent());
        model.addAttribute("pages", new int[roomPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);


        return "indexlayoutroom";
    }

    @GetMapping("/editroom")
    public String editRoomAction(Model model, @RequestParam(name = "id") Integer id) {
        Room room = roomService.getRoomById(id);

        if (room != null) {
            model.addAttribute("RoomToBeUpdated", room);
            return "updateroom";
        } else {
            return "error";
        }
    }
    @PostMapping("/modifierroom")
    public String modifierRoomAction(Model model,
                                     @RequestParam(name = "hotel_id") Integer hotelId, // Correction ici, hotel_id doit être de type Integer
                                     @RequestParam(name = "id") Integer id,
                                     @RequestParam(name = "roomNum") String roomNum,
                                     @RequestParam(name = "price") Double price,
                                     @RequestParam(name = "imageUrl") String imageUrl,
                                     @RequestParam(name = "description") String description,
                                     @RequestParam(name = "available", defaultValue = "false") boolean available,
                                     @RequestParam(name = "roomType") String roomType) {

        Room room = roomService.getRoomById(id);
        Hotel hotel = hotelService.getHotelById(hotelId); // Récupération de l'hôtel par son id

        if (room != null && hotel != null) {
            room.setRoomNumber(roomNum);
            room.setPricePerNight(price);
            room.setDescription(description);
            room.setImageUrl(imageUrl);
            room.setAvailable(available);
            room.setRoomType(roomType);
            room.setHotel(hotel); // Assigner l'hôtel à la chambre
            roomService.updateRoom(room); // Mise à jour de la chambre

            return "redirect:/listroom";
        } else {
            return "error"; // Gestion d'erreur si la chambre ou l'hôtel est introuvable
        }
    }

}



