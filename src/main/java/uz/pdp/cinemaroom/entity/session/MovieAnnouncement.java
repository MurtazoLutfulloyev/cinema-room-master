package uz.pdp.cinemaroom.entity.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;
import uz.pdp.cinemaroom.entity.movie.Movie;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "movie_announcements")
@OnDelete(action = OnDeleteAction.CASCADE)
public class MovieAnnouncement extends AbsEntity {

    private Boolean active;
    @OneToOne
    private Movie movie;


    public MovieAnnouncement( Movie movie,Boolean active) {

        this.active = active;
        this.movie = movie;
    }
}
