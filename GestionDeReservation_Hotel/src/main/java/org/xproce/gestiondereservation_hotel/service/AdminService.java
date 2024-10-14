package org.xproce.gestiondereservation_hotel.service;

import org.springframework.data.domain.Page;
import org.xproce.gestiondereservation_hotel.Dao.entities.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmin();
    Admin addAdmin(Admin admin);
    Admin updateAdmin(Admin admin);
    Boolean deleteAdmin(Admin admin);
    Admin getAdminById(Integer id);

    Boolean deleteAdminById(Integer id);

    Page<Admin> searchAdmin(String keyword, int page, int taille);
}
