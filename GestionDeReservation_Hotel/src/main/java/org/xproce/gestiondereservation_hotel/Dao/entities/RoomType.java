package org.xproce.gestiondereservation_hotel.Dao.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity@Setter@AllArgsConstructor@NoArgsConstructor@Getter@ToString
public class RoomType {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;

    @ManyToMany(mappedBy = "roomTypes")
    private List<Room> rooms;
}
