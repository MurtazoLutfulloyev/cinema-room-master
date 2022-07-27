package uz.pdp.cinemaroom.service;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemaroom.dto.HallDto;
import uz.pdp.cinemaroom.dto.MovieDto;
import uz.pdp.cinemaroom.dto.MovieProjectionDto;
import uz.pdp.cinemaroom.dto.SessionTimeDto;
import uz.pdp.cinemaroom.entity.attachment.Attachment;
import uz.pdp.cinemaroom.entity.attachment.AttachmentContent;
import uz.pdp.cinemaroom.entity.cinema.Hall;
import uz.pdp.cinemaroom.entity.movie.*;
import uz.pdp.cinemaroom.entity.user.User;
import uz.pdp.cinemaroom.entity.user.UserDetail;
import uz.pdp.cinemaroom.projection.MovieProjection;
import uz.pdp.cinemaroom.repository.*;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private AttachmentRepo attachmentRepo;

    @Autowired
    private AttachmentContentRepo attachmentContentRepo;

    @Autowired
    private ActorRepo actorRepo;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private DirectorRepo directorRepo;

    @Autowired
    private DistributorRepo distributorRepo;

    public Page<MovieProjection> getAllMoviesWithPagination(int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size);

        return movieRepo.findAllByPage(pageable);
    }

    @Transactional
    public void saveMovie(MovieDto movieDto, MultipartFile image, List<MultipartFile> moviePhotos) throws IOException {

        //Cover Image
        Attachment attachment = new Attachment();
        attachment.setName(StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename())));
        attachment.setContent_type(image.getContentType());
        attachment.setSize(image.getSize());
        Attachment coverImage = attachmentRepo.save(attachment);

        AttachmentContent fileAttachment = new AttachmentContent();
        fileAttachment.setAttachment(coverImage);
        fileAttachment.setData(image.getBytes());
        attachmentContentRepo.save(fileAttachment);

        //Movie Data
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setMinPrice(movieDto.getMinPrice());
        movie.setDistributor_share_in_percent(movieDto.getDistributor_share_in_percent());
        movie.setTrailerVideo(movieDto.getTrailerVideo());
        movie.setDurationMinutes(movieDto.getDurationMinutes());
        movie.setAttachment(coverImage);

        //Actors
        List<Actor> actors = actorRepo.findAllById(movieDto.getActors());

        movie.setActors(actors);

        //Genres
        List<Genre> genres = genreRepo.findAllById(movieDto.getGenres());

        movie.setGenres(genres);

        //Directors
        List<Director> directors = directorRepo.findAllById(movieDto.getDirectors());

        movie.setDirectors(directors);

        //Distributors
        Distributor distributor = distributorRepo.findById(movieDto.getDistributor()).get();

        movie.setDistributor(distributor);

        //Photos
        List<Attachment> moviePhotoList = new ArrayList<>();
        for (MultipartFile moviePhoto : moviePhotos) {

            Attachment attachment2 = new Attachment();
            attachment2.setName(StringUtils.cleanPath(Objects.requireNonNull(moviePhoto.getOriginalFilename())));
            attachment2.setContent_type(moviePhoto.getContentType());
            attachment2.setSize(moviePhoto.getSize());
            Attachment moviImages = attachmentRepo.save(attachment2);

            AttachmentContent fileAttachment2 = new AttachmentContent();
            fileAttachment2.setAttachment(moviImages);
            fileAttachment2.setData(moviePhoto.getBytes());
            attachmentContentRepo.save(fileAttachment2);

            moviePhotoList.add(attachment2);
        }

        movie.setMoviePhotos(moviePhotoList);

        movieRepo.save(movie);

    }

    public Movie getMovieById(String movieId) {
        return movieRepo.findById(movieId).get();
    }

    @Transactional
    public void updateMovie(MovieDto movieDto, MultipartFile image, List<MultipartFile> moviePhotos, String movieId) throws IOException {

        if (movieRepo.findById(movieId).isPresent()) {
            Movie findMovie = movieRepo.findById(movieId).get();
            //Cover Image
            Movie movie = new Movie();
            if (image != null) {

                Attachment attachment = new Attachment();
                attachment.setName(StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename())));
                attachment.setContent_type(image.getContentType());
                attachment.setSize(image.getSize());
                Attachment coverImage = attachmentRepo.save(attachment);

                movie.setAttachment(coverImage);

                AttachmentContent fileAttachment2 = new AttachmentContent();
                fileAttachment2.setAttachment(coverImage);
                fileAttachment2.setData(image.getBytes());
                attachmentContentRepo.save(fileAttachment2);

            } else {
                movie.setAttachment(findMovie.getAttachment());
            }
            //Movie Data
            movie.setId(movieId);
            movie.setTitle(movieDto.getTitle());
            movie.setDescription(movieDto.getDescription());
            movie.setMinPrice(movieDto.getMinPrice());
            movie.setDistributor_share_in_percent(movieDto.getDistributor_share_in_percent());
            movie.setTrailerVideo(movieDto.getTrailerVideo());
            movie.setDurationMinutes(movieDto.getDurationMinutes());

            //Actors
            List<Actor> actors = actorRepo.findAllById(movieDto.getActors());

            movie.setActors(actors);

            //Genres
            List<Genre> genres = genreRepo.findAllById(movieDto.getGenres());

            movie.setGenres(genres);

            //Directors
            List<Director> directors = directorRepo.findAllById(movieDto.getDirectors());

            movie.setDirectors(directors);

            //Distributors
            Distributor distributor = distributorRepo.findById(movieDto.getDistributor()).get();

            movie.setDistributor(distributor);

            //Photos
            List<Attachment> moviePhotoList = new ArrayList<>();
            if (moviePhotos != null) {

                for (MultipartFile moviePhoto : moviePhotos) {

                    Attachment attachment2 = new Attachment();
                    attachment2.setName(StringUtils.cleanPath(Objects.requireNonNull(moviePhoto.getOriginalFilename())));
                    attachment2.setContent_type(moviePhoto.getContentType());
                    attachment2.setSize(moviePhoto.getSize());
                    Attachment moviImages = attachmentRepo.save(attachment2);

                    AttachmentContent fileAttachment2 = new AttachmentContent();
                    fileAttachment2.setAttachment(moviImages);
                    fileAttachment2.setData(moviePhoto.getBytes());
                    attachmentContentRepo.save(fileAttachment2);

                    moviePhotoList.add(attachment2);

                }
                movie.setMoviePhotos(moviePhotoList);
            } else {
                movie.setMoviePhotos(findMovie.getMoviePhotos());
            }


            movieRepo.save(movie);
        }

    }

    public void deleteMovie(String movieId) {
        movieRepo.deleteById(movieId);
    }

    public List<MovieProjectionDto> getMoviesWithHall() throws JsonProcessingException {

        List<MovieProjectionDto> movieProjectionDtoList = new ArrayList<>();
        List<String> moviesWithHall = movieRepo.getMoviesWithHall();

        ObjectMapper mapper = new ObjectMapper();

        for (String s : moviesWithHall) {
            MovieProjectionDto movieProjectionDto = mapper.readValue(s, MovieProjectionDto.class);
            movieProjectionDtoList.add(movieProjectionDto);
        }

        return movieProjectionDtoList;

    }

    public List<MovieProjectionDto> getMoviesWithHallDate() throws JsonProcessingException {

        List<MovieProjectionDto> movieProjectionDtoList = new ArrayList<>();
        List<String> moviesWithHall = movieRepo.getMoviesWithHallDate();

//        User user = new User();
//        user.setFullName(new UserDetail("Asadbek","Xalimjonov"));
//        user.setUsername("asadbek");
//        user.setPassword("1111");
//        userRepo.save(user);


        ObjectMapper mapper = new ObjectMapper();

        for (String s : moviesWithHall) {
            MovieProjectionDto movieProjectionDto = mapper.readValue(s, MovieProjectionDto.class);
            for (HallDto hall : movieProjectionDto.getHalls()) {
                List<SessionTimeDto> sessionTimeDtos = new ArrayList<>();
                List<String> sessionTime = movieRepo.getSessionTime(movieProjectionDto.getId(), hall.getId(), movieProjectionDto.getStartDate());
                for (String s1 : sessionTime) {
                    SessionTimeDto sessionTimeDto = mapper.readValue(s1, SessionTimeDto.class);
                    sessionTimeDtos.add(sessionTimeDto);
                }
                hall.setStartEndTime(sessionTimeDtos);
            }
            movieProjectionDtoList.add(movieProjectionDto);
        }

        return movieProjectionDtoList;

    }
}
