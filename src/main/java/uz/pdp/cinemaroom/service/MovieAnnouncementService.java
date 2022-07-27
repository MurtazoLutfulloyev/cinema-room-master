package uz.pdp.cinemaroom.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemaroom.dto.MovieAnnouncementDto;
import uz.pdp.cinemaroom.entity.movie.Movie;
import uz.pdp.cinemaroom.entity.session.MovieAnnouncement;
import uz.pdp.cinemaroom.repository.MovieRepo;
import uz.pdp.cinemaroom.repository.MovieAnnouncementRepo;

import java.util.List;

@Service
public class MovieAnnouncementService {

    @Autowired
    private MovieAnnouncementRepo movieAnnouncementRepo;

    @Autowired
    private MovieRepo movieRepo;

    public List<MovieAnnouncement> getAllAfishas() {
        return movieAnnouncementRepo.findAll();
    }

    public void saveMovieSession(MovieAnnouncementDto movieAnnouncementDto) {
        if (movieRepo.findById(movieAnnouncementDto.getMovieId()).isPresent()) {
            Movie movie = movieRepo.findById(movieAnnouncementDto.getMovieId()).get();
            MovieAnnouncement movieAnnouncement = new MovieAnnouncement();
            movieAnnouncement.setMovie(movie);
            movieAnnouncement.setActive(movieAnnouncementDto.getActive());
            movieAnnouncementRepo.save(movieAnnouncement);
        }
    }

    public void updateMovieSessionId(String movieSessionId, MovieAnnouncementDto movieAnnouncementDto) {
        if (movieAnnouncementRepo.findById(movieSessionId).isPresent()) {
            MovieAnnouncement movieAnnouncementFind = movieAnnouncementRepo.findById(movieSessionId).get();


            MovieAnnouncement movieAnnouncement = new MovieAnnouncement();
            movieAnnouncement.setId(movieSessionId);

            if (movieAnnouncementDto.getMovieId() != null && movieRepo.findById(movieAnnouncementDto.getMovieId()).isPresent()) {
                Movie movie = movieRepo.findById(movieAnnouncementDto.getMovieId()).get();
                movieAnnouncement.setMovie(movie);
            } else {
                movieAnnouncement.setMovie(movieAnnouncementFind.getMovie());
            }

            movieAnnouncement.setActive(movieAnnouncementDto.getActive());
            movieAnnouncementRepo.save(movieAnnouncement);
        }
    }

    public void deleteMovieSession(String movieSessionId) {
        movieAnnouncementRepo.deleteById(movieSessionId);
    }

}
