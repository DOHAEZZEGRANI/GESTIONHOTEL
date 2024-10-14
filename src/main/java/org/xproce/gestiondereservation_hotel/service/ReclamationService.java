package org.xproce.gestiondereservation_hotel.service;

import org.springframework.data.domain.Page;
import org.xproce.gestiondereservation_hotel.Dao.entities.Reclamation;

import java.util.List;

public interface ReclamationService {

    List<Reclamation> getAllReclamation();
    Reclamation addReclamation(Reclamation reclamation);
    Reclamation updateReclamation(Reclamation reclamation);
    Boolean deleteReclamation(Reclamation reclamation);
    Reclamation getReclamationById(Integer id);
    Boolean deleteReclamationById(Integer id);
    Page<Reclamation> searchReclamation(String keyword, int page, int taille);
}
