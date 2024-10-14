package org.xproce.gestiondereservation_hotel.web;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.xproce.gestiondereservation_hotel.Dao.entities.Hotel;
import org.xproce.gestiondereservation_hotel.service.HotelService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping("/savehotel")
    public String ajouterhotel(Model model,
                               @Valid Hotel hotel,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterhotel";
        }
        hotelService.addHotel(hotel);
        return "redirect:/indexpage";
    }


    @GetMapping("indexlayout")
    public String acc() {
        return "redirect:/indexpage";
    }
    @GetMapping("/about")
        public String about() {
            return "about";
        }



    @GetMapping("hello")
    public String hello() {
        return "redirect:/indexpage";}


    @GetMapping("")
    public String av() {
        return "hello";

    } @GetMapping("welcome")
    public String welcome() {
        return "welcome";

    }


    @GetMapping("/login")
    public String login() {
        return "login"; }




    @GetMapping("/details")
    public String detail(Model model,
                         @RequestParam(name = "id") Integer id) {
        Hotel hotel = hotelService.getHotelById(id);
        model.addAttribute("hotelWithDetails", hotel);
        return "/detailhotel";
    }



    @GetMapping("/delete")
    public String deletehotel(@RequestParam(name = "id") Integer id) {
        if (hotelService.deleteHotelById(id)) {
            return "redirect:/indexpage";
        } else {
            return "error";
        }
    }


    @PostMapping("/ajouterh")
    public String modifierHotelAction(Model model,
                                        @RequestParam(name = "id") Integer id,
                                        @RequestParam(name = "name") String name,
                                        @RequestParam(name = "adresse") String adresse,
                                        @RequestParam(name = "phone") String phone,
                                        @RequestParam(name = "email") String email,
                                        @RequestParam(name = "description") String description) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            hotel.setName(name);
            hotel.setAddress(adresse);
            hotel.setPhone(phone);
            hotel.setEmail(email);
            hotel.setDescription(description);
            hotelService.updateHotel(hotel);
            return "redirect:/indexpage";
        } else {
            return "error";
        }
    }



    @GetMapping("/ajouterhotel")
    public String ajouterhotel(Model model) {
        model.addAttribute("Hotel", new Hotel());
        return "ajouterhotel";
    }



    @PostMapping("/ajouterOncee")
    public String ajouterhotelOnce(Model model,
                                 @Valid Hotel hotel,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterhotel";
        }
        hotelService.addHotel(hotel);
        return "redirect:/indexpage";
    }



    @GetMapping("/indexpage")
    public String listProduit(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "taille", defaultValue = "6") int taille,
                              @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Hotel> hotels = hotelService.searchHotel(keyword, page, taille);
        model.addAttribute("listHotel", hotels.getContent());

        int[] pages = new int[hotels.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "indexlayout";
    }




    @GetMapping("/editHotel")
    public String edithotelAction(Model model, @RequestParam(name = "id") Integer id) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            model.addAttribute("HotelToBeUpdated", hotel);
            return "updateHotel";
        } else {
            return "error";
        }
    }
}