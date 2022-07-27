package uz.pdp.cinemaroom.service.search;

import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.movie.Movie;



@Repository
public interface MovieRepository extends SearchRepository<Movie, String> {

}
