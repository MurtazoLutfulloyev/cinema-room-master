package uz.pdp.cinemaroom.entity.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;
import uz.pdp.cinemaroom.entity.attachment.Attachment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Indexed
@Entity(name = "movies")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Movie extends AbsEntity {

    @FullTextField()
    @NaturalId()
    private String title;

    @FullTextField()
    @NaturalId()
    private String description;

    private Integer durationMinutes;

    private Double minPrice;
    @OneToOne
    private Attachment attachment;

    private String trailerVideo;

    @ManyToMany
    @JoinTable(name = "movie_directors",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "director_id", referencedColumnName = "id"))
    private Collection<Director> directors = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private Collection<Genre> genres = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
    private Collection<Actor> actors = new ArrayList<>();


    @ManyToMany
    @JoinTable(name = "movie_photos",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id", referencedColumnName = "id"))
    private Collection<Attachment> moviePhotos = new ArrayList<>();



    @OneToOne(cascade = CascadeType.REMOVE)
    private Distributor distributor;

    private LocalDate releaseDate;

    private Double distributor_share_in_percent;


    public Movie(String title, String description, Integer durationMinutes, Double minPrice, Attachment attachment, String trailerVideo,LocalDate releaseDate) {

        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.minPrice = minPrice;
        this.attachment = attachment;
        this.trailerVideo = trailerVideo;
        this.releaseDate=releaseDate;
    }
}
