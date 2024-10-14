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
import org.xproce.gestiondereservation_hotel.Dao.entities.Reclamation;
import org.xproce.gestiondereservation_hotel.service.FactureService;
import org.xproce.gestiondereservation_hotel.service.ReclamationService;

@Controller
public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;

        @PostMapping("/savereclamation")
        public String ajouterreclamation(Model model,
                                     @Valid Reclamation reclamation ,
                                     BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "ajouterhotel";
            }
            reclamationService.addReclamation(reclamation);
            return "redirect:/indexpage";
        }


        @GetMapping("indexlayoutreclamation")
        public String acc() {
            return "redirect:/listreclamation";
        }







        @GetMapping("/detailsreclamation")
        public String details(Model model,
                              @RequestParam(name = "id") Integer id) {
            Reclamation reclamation = reclamationService.getReclamationById(id);
            model.addAttribute("reclamationWithDetails", reclamation);
            return "/detailreclamation";
        }



        @GetMapping("/deletereclamation")
        public String deletereclamation(@RequestParam(name = "id") Integer id) {
            if (reclamationService.deleteReclamationById(id)) {
                return "redirect:/listreclamation";
            } else {
                return "error";
            }
        }


        @PostMapping("/ajouterre")
        public String modifierreclamationAction(Model model,
                                            @RequestParam(name = "id") Integer id,
                                            @RequestParam(name = "description") String description) {
            Reclamation reclamation  = reclamationService.getReclamationById(id);
            if (reclamation != null) {
                reclamation.setDescription(description);
                reclamationService.updateReclamation(reclamation);
                return "redirect:/listreclamation";
            } else {
                return "error";
            }
        }



        @GetMapping("/ajouterreclamation")
        public String ajouterreclamation(Model model) {
            model.addAttribute("Reclamation", new Reclamation());
            return "ajouterreclamation";
        }



        @PostMapping("/ajouteraOnce")
        public String ajouterareclamation(Model model,
                                      @Valid Reclamation reclamation   ,
                                      BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "ajouterreclamation";
            }
            reclamationService.addReclamation(reclamation);
            return "redirect:/listreclamation";
        }



        @GetMapping("/listreclamation")
        public String listreclamation(Model model,
                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "taille", defaultValue = "6") int taille,
                                  @RequestParam(name = "search", defaultValue = "") String keyword) {
            Page<Reclamation> reclamation = reclamationService.searchReclamation(keyword, page, taille);
            model.addAttribute("listreclamation", reclamation.getContent());

            int[] pages = new int[reclamation.getTotalPages()];
            model.addAttribute("pages", pages);
            model.addAttribute("keyword", keyword);
            model.addAttribute("page", page);
            return "layoutreclamation";
        }



        @GetMapping("/editreclamation")
        public String editreclamationAction(Model model, @RequestParam(name = "id") Integer id) {
            Reclamation reclamation  = reclamationService.getReclamationById(id);
            if (reclamation != null) {
                model.addAttribute("reclamationToBeUpdated", reclamation);
                return "updatereclamation";
            } else {
                return "error";
            }
        }
    }



