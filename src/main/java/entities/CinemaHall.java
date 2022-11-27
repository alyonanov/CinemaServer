package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cinemahalls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaHall {

    @Id
    @Column(name = "hall_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hallId;

    @Column(name = "hall_type")
    private String hallType;
    
    @Column(name = "hall_seatsnumber")
    private int hallSeatsNumber;

}