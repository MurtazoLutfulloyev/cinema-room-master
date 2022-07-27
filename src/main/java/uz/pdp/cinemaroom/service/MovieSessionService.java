package uz.pdp.cinemaroom.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemaroom.dto.MovieSessionDto;
import uz.pdp.cinemaroom.entity.cinema.Hall;
import uz.pdp.cinemaroom.entity.session.MovieAnnouncement;
import uz.pdp.cinemaroom.entity.session.MovieSession;
import uz.pdp.cinemaroom.entity.session.SessionDate;
import uz.pdp.cinemaroom.entity.session.SessionTime;
import uz.pdp.cinemaroom.repository.*;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MovieSessionService {


    @Autowired
    private MovieSessionRepo movieSessionRepo;

    @Autowired
    private HallRepo hallRepo;

    @Autowired
    private MovieAnnouncementRepo movieAnnouncementRepo;

    @Autowired
    private SessionTimeRepo sessionTimeRepo;

    @Autowired
    private SessionDateRepo sessionDateRepo;

    @Autowired
    private MovieRepo movieRepo;


    public List<MovieSession> getAllReservedHall() {
        return movieSessionRepo.findAll();
    }


    @Transactional
    public void saveSessionHall(MovieSessionDto movieSessionDto) throws ParseException {
        MovieSession movieSession = new MovieSession();
        if (movieSessionDto.getMovieAnnouncementId() != null && movieSessionDto.getHallId() != null) {
            if (movieAnnouncementRepo.findById(movieSessionDto.getMovieAnnouncementId()).isPresent() && hallRepo.findById(movieSessionDto.getHallId()).isPresent()) {

                MovieAnnouncement movieAnnouncement = movieAnnouncementRepo.findById(movieSessionDto.getMovieAnnouncementId()).get();

                LocalDate startDate = LocalDate.parse(movieSessionDto.getStartDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));

                Time startTime = Time.valueOf(movieSessionDto.getStartTime());


                Time endTime = calculateEndTime(startTime, movieAnnouncement.getMovie().getDurationMinutes());

                //SessionDate
                SessionDate sessionDate = sessionDateRepo.findAll()
                        .stream()
                        .filter(sessionDate1 -> sessionDate1.getDate().toString().equals(startDate.toString()))
                        .findFirst()
                        .orElse(null);

                SessionDate savedSessionDate = null;
                if (sessionDate != null) {
                    savedSessionDate = sessionDate;
                    movieSession.setStartDate(sessionDate);
                } else {
                    sessionDate = new SessionDate();
                    sessionDate.setDate(startDate);
                    savedSessionDate = sessionDateRepo.save(sessionDate);
                    movieSession.setStartDate(savedSessionDate);
                }

                //SessionTime

                SessionTime sessionTime = sessionTimeRepo
                        .findAll()
                        .stream()
                        .filter(sessionTime1 -> sessionTime1.getTime().toString().equals(startTime.toString()))
                        .findFirst().orElse(null);

                if (sessionTime != null) {
                    movieSession.setStartTime(sessionTime);
                } else {
                    sessionTime = new SessionTime();
                    sessionTime.setSessionDate(savedSessionDate);
                    sessionTime.setTime(startTime);
                    SessionTime savedSessionTime = sessionTimeRepo.save(sessionTime);
                    movieSession.setStartTime(savedSessionTime);
                }

                SessionTime sessionEndTime = new SessionTime();
                sessionEndTime.setSessionDate(sessionDate);
                sessionEndTime.setTime(endTime);
                SessionTime savedSessionEndTime = sessionTimeRepo.save(sessionEndTime);

                Hall hall = hallRepo.findById(movieSessionDto.getHallId()).get();

                //Microsoft team password = saas2312SS23##

                //Save ReservedHall
                movieSession.setMovieAnnouncement(movieAnnouncement);
                movieSession.setHall(hall);

                movieSession.setEndTime(savedSessionEndTime);

                movieSessionRepo.save(movieSession);

            }
        }
    }

    @Transactional
    public void updateSessionHall(String sessionHallId, MovieSessionDto movieSessionDto) {
        if (movieSessionRepo.findById(sessionHallId).isPresent()) {


        }
    }

    public void deleteSessionHall(String sessionHallId) {
        movieSessionRepo.deleteById(sessionHallId);
    }

    public Time calculateEndTime(Time startTime, Integer movieDuration) {
        long timeInSecs = startTime.getTime();
        Time endTime = new Time(timeInSecs + (movieDuration * 60 * 1000));
        return endTime;
    }

}
