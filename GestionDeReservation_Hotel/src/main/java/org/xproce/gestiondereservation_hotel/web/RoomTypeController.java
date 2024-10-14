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
import org.xproce.gestiondereservation_hotel.Dao.entities.RoomType;
import org.xproce.gestiondereservation_hotel.service.RoomTypeService;

@Controller

public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;


    @PostMapping("/saveroomType")
    public String ajouterroomType(Model model,
                              @Valid RoomType roomType ,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterhotel";
        }
        roomTypeService.addRoomType(roomType);
        return "redirect:/indexpage";
    }


    @GetMapping("indexlayoutroomType")
    public String acc() {
        return "redirect:/listroomType";
    }







    @GetMapping("/detailsroomType")
    public String details(Model model,
                          @RequestParam(name = "id") Integer id) {
        RoomType roomType  = roomTypeService.getRoomTypeById(id);
        model.addAttribute("RoomTypeWithDetails", roomType);
        return "/detailroomType";
    }



    @GetMapping("/deleteroomType")
    public String deleteroomType(@RequestParam(name = "id") Integer id) {
        if (roomTypeService.deleteRoomTypeById(id)) {
            return "redirect:/listroomType";
        } else {
            return "error";
        }
    }


    @PostMapping("/ajouterroomTypee")
    public String modifierroomTypeAction(Model model,
                                     @RequestParam(name = "id") Integer id,
                                     @RequestParam(name = "type") String type) {
        RoomType roomType = roomTypeService.getRoomTypeById(id);
        if (roomType != null) {
            roomType.setType(type);
            roomTypeService.updateRoomType(roomType);
            return "redirect:/listroomType";
        } else {
            return "error";
        }
    }



    @GetMapping("/ajouterroomType")
    public String ajouterroomType(Model model) {
        model.addAttribute("RoomType", new RoomType());
        return "ajouterroomType";
    }



    @PostMapping("/ajouterOOnceTYPE")
    public String ajouteraroomType(Model model,
                               @Valid RoomType roomType  ,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterroomType";
        }
        roomTypeService.addRoomType(roomType);
        return "redirect:/listroomType";
    }



    @GetMapping("/listroomType")
    public String listroomType(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "taille", defaultValue = "6") int taille,
                           @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<RoomType> roomType = RoomTypeService.searchRoomType(keyword, page, taille);
        model.addAttribute("listRoomType", roomType.getContent());

        int[] pages = new int[roomType.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "layoutroomType";
    }



    @GetMapping("/editroomType")
    public String editroomTypeAction(Model model, @RequestParam(name = "id") Integer id) {
        RoomType roomType  = roomTypeService.getRoomTypeById(id);
        if (roomType != null) {
            model.addAttribute("RoomTypeToBeUpdated", roomType);
            return "updateroomType";
        } else {
            return "error";
        }
    }
}

