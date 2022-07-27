package uz.pdp.cinemaroom.projection;


import java.util.List;
import java.util.UUID;

public interface MovieProjection {

    UUID getId();

    String getMovieTitle();

    UUID getCoverImageId();

    List<String> getHalls();


}
