package org.xproce.gestiondereservation_hotel.service;

import org.springframework.data.domain.Page;
import org.xproce.gestiondereservation_hotel.Dao.entities.Customer;
import org.xproce.gestiondereservation_hotel.Dao.entities.Facture;
import org.xproce.gestiondereservation_hotel.Dao.entities.Hotel;

import java.util.List;

public interface FactureService {
    List<Facture> getAllFacture();
    Facture addFacture(Facture facture);
    Facture updateFacture(Facture facture);
    Boolean deleteFacture(Facture facture);
    Facture getFactureById(Integer id);
    Boolean deleteFactureById(Integer id);

    public Page<Facture> searchFacture(String keyword, int page, int taille);
    public List<Hotel> getByKeyword(String keyword);

}
