package org.xproce.gestiondereservation_hotel.Dao.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class Facture {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double totale;
    private String description;

    @OneToOne
    private Reservation reservation;
}
