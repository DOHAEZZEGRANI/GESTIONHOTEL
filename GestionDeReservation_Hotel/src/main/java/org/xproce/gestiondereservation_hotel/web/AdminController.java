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
import org.xproce.gestiondereservation_hotel.Dao.entities.Admin;
import org.xproce.gestiondereservation_hotel.service.AdminService;


@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/saveAdmin")
    public String ajouteradmin(Model model,
                              @Valid Admin admin ,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterhotel";
        }
        adminService.addAdmin(admin);
        return "redirect:/indexpage";
    }


    @GetMapping("indexlayoutadmin")
    public String acc() {
        return "redirect:/listadmin";
    }







    @GetMapping("/detailsadmin")
    public String details(Model model,
                          @RequestParam(name = "id") Integer id) {
        Admin admin  = adminService.getAdminById(id);
        model.addAttribute("AdminWithDetails", admin);
        return "/detailadmin";
    }



    @GetMapping("/deleteAdmin")
    public String deleteadmin(@RequestParam(name = "id") Integer id) {
        if (adminService.deleteAdminById(id)) {
            return "redirect:/listadmin";
        } else {
            return "error";
        }
    }


    @PostMapping("/ajouterr")
    public String modifieradminAction(Model model,
                                            @RequestParam(name = "id") Integer id,
                                            @RequestParam(name = "name") String name,
                                            @RequestParam(name = "phone") String phone,
                                            @RequestParam(name = "description") String description) {
        Admin admin  = adminService.getAdminById(id);
        if (admin != null) {
            admin.setName(name);
            admin.setPhone(phone);
            admin.setDescription(description);
            adminService.updateAdmin(admin);
            return "redirect:/listadmin";
        } else {
            return "error";
        }
    }



    @GetMapping("/ajouteradmin")
    public String ajouteradmin(Model model) {
        model.addAttribute("Admin", new Admin());
        return "ajouteradmin";
    }



    @PostMapping("/ajouterOOnce")
    public String ajouteraadmin(Model model,
                                 @Valid Admin admin ,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouteradmin";
        }
        adminService.addAdmin(admin);
        return "redirect:/listadmin";
    }



    @GetMapping("/listadmin")
    public String listadmin(Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "taille", defaultValue = "6") int taille,
                                  @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Admin> admin = adminService.searchAdmin(keyword, page, taille);
        model.addAttribute("listAdmin", admin.getContent());

        int[] pages = new int[admin.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "layoutadmin";
    }



    @GetMapping("/editadmin")
    public String editadminAction(Model model, @RequestParam(name = "id") Integer id) {
        Admin admin = adminService.getAdminById(id);
        if (admin != null) {
            model.addAttribute("AdminToBeUpdated", admin);
            return "updateadmin";
        } else {
            return "error";
        }
    }
}







