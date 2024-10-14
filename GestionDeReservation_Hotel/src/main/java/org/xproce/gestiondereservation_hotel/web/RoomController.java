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
import org.xproce.gestiondereservation_hotel.Dao.entities.Room;
import org.xproce.gestiondereservation_hotel.service.RoomService;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;


    @PostMapping("/saveroom")
    public String ajouterroom(Model model,
                               @Valid Room room ,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterhotel";
        }
        roomService.addRoom(room);
        return "redirect:/indexpage";
    }


    @GetMapping("indexlayoutroom")
    public String acc() {
        return "redirect:/listroom";
    }







    @GetMapping("/detailsroom")
    public String details(Model model,
                          @RequestParam(name = "id") Integer id) {
        Room room  = roomService.getRoomById(id);
        model.addAttribute("RoomWithDetails", room);
        return "/detailroom";
    }



    @GetMapping("/deleteroom")
    public String deleteroom(@RequestParam(name = "id") Integer id) {
        if (roomService.deleteRoomById(id)) {
            return "redirect:/listroom";
        } else {
            return "error";
        }
    }


    @PostMapping("/ajouterroo")
    public String modifierroomAction(Model model,
                                      @RequestParam(name = "id") Integer id,
                                      @RequestParam(name = "roomNum") String roomNum,
                                      @RequestParam(name = "available") Boolean available,
                                      @RequestParam(name = "price") Double price,
                                      @RequestParam(name = "description") String description) {
        Room room  = roomService.getRoomById(id);
        if (room != null) {
            room.setRoomNumber(roomNum);
            room.setAvailable(available);
            room.setPricePerNight(price);
            room.setDescription(description);
            roomService.updateRoom(room);
            return "redirect:/listroom";
        } else {
            return "error";
        }
    }



    @GetMapping("/ajouterroom")
    public String ajouterroom(Model model) {
        model.addAttribute("Room", new Room());
        return "ajouterroom";
    }



    @PostMapping("/ajouterOOnceroom")
    public String ajouteraroom(Model model,
                                @Valid Room room  ,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterroom";
        }
        roomService.addRoom(room);
        return "redirect:/listroom";
    }



    @GetMapping("/listroom")
    public String listroom(Model model,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "taille", defaultValue = "6") int taille,
                            @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Room> room = roomService.searchRoom(keyword, page, taille);
        model.addAttribute("listRoom", room.getContent());

        int[] pages = new int[room.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "layoutroom";
    }



    @GetMapping("/editroom")
    public String editroomAction(Model model, @RequestParam(name = "id") Integer id) {
        Room room  = roomService.getRoomById(id);
        if (room != null) {
            model.addAttribute("RoomToBeUpdated", room);
            return "updateroom";
        } else {
            return "error";
        }
    }

}
