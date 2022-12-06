package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "movie_name")
    private String movieName;

    @Column(name = "movie_genre")
    private String movieGenre;

    @Column(name = "movie_country")
    private String movieCountry;

    @Column(name = "movie_duration")
    private String movieDuration;

    @Column(name = "movie_price")
    private int moviePrice;

    @ManyToOne
    @JoinColumn(name = "cinemahall_id")
    private CinemaHall cinemaHallId;

}