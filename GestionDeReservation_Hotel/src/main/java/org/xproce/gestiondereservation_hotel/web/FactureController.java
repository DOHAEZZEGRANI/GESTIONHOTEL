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

import org.xproce.gestiondereservation_hotel.Dao.entities.Facture;
import org.xproce.gestiondereservation_hotel.service.FactureService;

@Controller
public class FactureController {
    @Autowired
    private FactureService factureService;


    @PostMapping("/savefacture")
    public String ajouterfacture(Model model,
                              @Valid Facture facture ,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterhotel";
        }
        factureService.addFacture(facture);
        return "redirect:/indexpage";
    }


    @GetMapping("indexlayoutfacture")
    public String acc() {
        return "redirect:/listfacture";
    }







    @GetMapping("/detailsfacture")
    public String details(Model model,
                          @RequestParam(name = "id") Integer id) {
        Facture facture   = factureService.getFactureById(id);
        model.addAttribute("factureWithDetails", facture);
        return "/detailfacture";
    }



    @GetMapping("/deletefacture")
    public String deletefacture(@RequestParam(name = "id") Integer id) {
        if (factureService.deleteFactureById(id)) {
            return "redirect:/listfacture";
        } else {
            return "error";
        }
    }


    @PostMapping("/ajouterf")
    public String modifierfactureAction(Model model,
                                         @RequestParam(name = "id") Integer id,
                                         @RequestParam(name = "totale") Double totale,
                                         @RequestParam(name = "description") String description) {
        Facture facture  = factureService.getFactureById(id);
        if (facture != null) {
            facture.setDescription(description);
            facture.setTotale(totale);
            factureService.updateFacture(facture);
            return "redirect:/listfacture";
        } else {
            return "error";
        }
    }



    @GetMapping("/ajouterfacture")
    public String ajouterfacture(Model model) {
        model.addAttribute("Facture", new Facture());
        return "ajouterfacture";
    }



    @PostMapping("/ajouterOOOnce")
    public String ajouterafacture(Model model,
                                   @Valid Facture facture   ,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterfacture";
        }
        factureService.addFacture(facture);
        return "redirect:/listfacture";
    }



    @GetMapping("/listfacture")
    public String listfacture(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "taille", defaultValue = "6") int taille,
                               @RequestParam(name = "search", defaultValue = "") String keyword) {
        Page<Facture> facture = factureService.searchFacture(keyword, page, taille);
        model.addAttribute("listFacture", facture.getContent());

        int[] pages = new int[facture.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "layoutfacture";
    }



    @GetMapping("/editfacture")
    public String editcustomerAction(Model model, @RequestParam(name = "id") Integer id) {
        Facture facture  = factureService.getFactureById(id);
        if (facture != null) {
            model.addAttribute("FactureToBeUpdated", facture);
            return "updatefacture";
        } else {
            return "error";
        }
    }
}


