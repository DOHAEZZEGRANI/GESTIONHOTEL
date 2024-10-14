package org.xproce.gestiondereservation_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xproce.gestiondereservation_hotel.Dao.entities.Facture;
import org.xproce.gestiondereservation_hotel.Dao.entities.Hotel;
import org.xproce.gestiondereservation_hotel.Dao.repositories.FactureRepository;

import java.util.List;
 @Service
public class FactureManager implements FactureService {
     @Autowired

     private FactureRepository factureRepository;

    @Override
    public List<Facture> getAllFacture() {
        try{
            return factureRepository.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Facture addFacture(Facture facture) {
        try{
            return factureRepository.save(facture);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Facture updateFacture(Facture facture) {
        try{
            return factureRepository.save(facture);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public Boolean deleteFacture(Facture facture) {
        try{
             factureRepository.delete(facture);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public Facture getFactureById(Integer id) {
        try{
            return factureRepository.findById(id).get();

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public Boolean deleteFactureById(Integer id) {
        try {
            factureRepository.deleteById(id);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

     @Override
     public Page<Facture> searchFacture(String keyword, int page, int taille) {

         return factureRepository.findByDescriptionContains(keyword, (Pageable) PageRequest.of(page, taille));

     }

     @Override
     public List<Hotel> getByKeyword(String keyword) {
         return null;
     }
 }