package org.xproce.gestiondereservation_hotel.Dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalPrice;
    @ManyToOne
    private Customer customer;

    @OneToOne(mappedBy = "reservation")
    private Facture facture;
}
