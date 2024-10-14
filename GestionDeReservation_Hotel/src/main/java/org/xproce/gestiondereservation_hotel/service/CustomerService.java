package org.xproce.gestiondereservation_hotel.service;

import org.springframework.data.domain.Page;
import org.xproce.gestiondereservation_hotel.Dao.entities.Admin;
import org.xproce.gestiondereservation_hotel.Dao.entities.Customer;
import org.xproce.gestiondereservation_hotel.Dao.entities.Hotel;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomer();
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Boolean deleteCustomer(Customer customer);
    Customer getCustomerById(Integer id);
    Boolean deleteCustomerById(Integer id);

    public Page<Customer> searchCustomer(String keyword, int page, int taille);
    public List<Hotel> getByKeyword(String keyword);

}