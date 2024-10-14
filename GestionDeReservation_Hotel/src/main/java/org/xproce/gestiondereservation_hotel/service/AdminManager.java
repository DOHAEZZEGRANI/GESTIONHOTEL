package org.xproce.gestiondereservation_hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.xproce.gestiondereservation_hotel.Dao.entities.Admin;
import org.xproce.gestiondereservation_hotel.Dao.repositories.AdminRepository;

import java.util.List;


@Service

public class AdminManager implements AdminService{
 @Autowired
    private AdminRepository adminRepository;
    @Override
    public List<Admin> getAllAdmin() {
        try {
            return adminRepository.findAll();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }}

    @Override
    public Admin addAdmin(Admin admin) {

        try {

            return adminRepository.save(admin);

    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }}


    @Override
    public Admin updateAdmin(Admin admin) {
        try {

            return adminRepository.save(admin);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }}

    @Override
    public Boolean deleteAdmin(Admin admin) {
        try {

            adminRepository.delete(admin);
            return true;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }}

    @Override
    public Admin getAdminById(Integer id) {
        try {
            return adminRepository.findById(id).get();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }}

    @Override
    public Boolean deleteAdminById(Integer id) {
        try {
            adminRepository.deleteById(id);
            return true;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }}

    @Override
    public Page<Admin> searchAdmin(String keyword, int page, int taille) {
        return adminRepository.findByDescriptionContains(keyword, (Pageable) PageRequest.of(page, taille));
    }

}
