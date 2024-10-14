package org.xproce.gestiondereservation_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.xproce.gestiondereservation_hotel.Dao.entities.Reclamation;
import org.xproce.gestiondereservation_hotel.Dao.repositories.ReclamationRepository;

import java.util.List;

@Service
public class ReclamationManager implements ReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepository;

    @Override
    public List<Reclamation> getAllReclamation() {
        try {
            return reclamationRepository.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        try {
            return reclamationRepository.save(reclamation);
        }catch (Exception e){
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Reclamation updateReclamation(Reclamation reclamation) {
        try {
            return reclamationRepository.save(reclamation);
        }catch (Exception e){
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Boolean deleteReclamation(Reclamation reclamation) {
        try {
             reclamationRepository.delete(reclamation);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());

            return false;
        }
    }

    @Override
    public Reclamation getReclamationById(Integer id) {
        try {
            return reclamationRepository.findById(id).get();

        }catch (Exception e){
            System.out.println(e.getMessage());

            return null;
        }
    }

    @Override
    public Boolean deleteReclamationById(Integer id) {
        try {
            reclamationRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());

            return false;
        }
    }

    @Override
    public Page<Reclamation> searchReclamation(String keyword, int page, int taille) {
        return null;
    }
}


