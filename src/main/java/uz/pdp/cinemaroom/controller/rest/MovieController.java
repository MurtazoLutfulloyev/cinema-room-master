package uz.pdp.cinemaroom.controller.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemaroom.dto.MovieDto;
import uz.pdp.cinemaroom.entity.movie.Movie;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.projection.MovieProjection;
import uz.pdp.cinemaroom.repository.MovieRepo;
import uz.pdp.cinemaroom.service.MovieService;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/movie")
public class MovieController {


    String DEFAULT_MOVIE_PAGE_SIZE = "1";
    String DEFAULT_MOVIE_PAGE = "1";

    @Autowired
    private MovieService movieService;


    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> getAllMovies(
            @RequestParam(required = false, defaultValue = "1") int size,
            @RequestParam(required = false, defaultValue = "1") int page
    ) {

        try {

            return new ResponseEntity<>(
                    new ApiResponse(
                            true,
                            "success",
                            movieService.getAllMoviesWithPagination(
                                    page,
                                    size
                            )
                    ),
                    HttpStatus.OK);

        } catch (Exception e) {
        }
        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "error",
                        null),
                HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/hall")
    public ResponseEntity<ApiResponse> getAllMoviesWithHall() {

        try {

            return new ResponseEntity<>(
                    new ApiResponse(
                            true,
                            "success",
                            movieService.getMoviesWithHall()
                    ),
                    HttpStatus.OK);

        } catch (Exception e) {
        }
        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "error",
                        null),
                HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/hall/date")
    public ResponseEntity<ApiResponse> getAllMoviesWithHallDate() {

        try {


            return new ResponseEntity<>(
                    new ApiResponse(
                            true,
                            "success",
                            movieService.getMoviesWithHallDate()
                    ),
                    HttpStatus.OK);

        } catch (Exception e) {
        }
        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "error",
                        null),
                HttpStatus.BAD_GATEWAY);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<ApiResponse> getMoviesById(@PathVariable String movieId
    ) {
        try {

            return new ResponseEntity<>(
                    new ApiResponse(
                            true,
                            "success",
                            movieService.getMovieById(movieId)),
                    HttpStatus.OK);
        } catch (Exception e) {
        }

        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "success",
                        null),
                HttpStatus.NOT_FOUND);

    }

    //Save Moie
    @PostMapping
    public ResponseEntity<ApiResponse> saveMovie(
            @RequestPart("movie") MovieDto movie,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart(value = "moviePhotos", required = false) List<MultipartFile> moviePhotos
    ) {


        try {

            movieService.saveMovie(movie, image, moviePhotos);

            return new ResponseEntity<>(
                    new ApiResponse(
                            true,
                            "created",
                            movie),
                    HttpStatus.OK);

        } catch (Exception ignored) {
        }
        return new ResponseEntity<>(
                new ApiResponse(
                        false,
                        "error",
                        movie),
                HttpStatus.BAD_REQUEST);

    }


    //Update Movie
    @PutMapping("/{movieId}")
    public ResponseEntity<ApiResponse> saveMovie(@PathVariable String movieId,
                                                 @RequestPart("movie") MovieDto movie,
                                                 @RequestPart(value = "image", required = false) MultipartFile image,
                                                 @RequestPart(value = "moviePhotos", required = false) List<MultipartFile> moviePhotos
    ) {


        try {

            movieService.updateMovie(movie, image, moviePhotos, movieId);

            return new ResponseEntity<>(
                    new ApiResponse(
                            true,
                            "updated",
                            movie),
                    HttpStatus.OK);

        } catch (Exception ignored) {
        }
        return new ResponseEntity<>(
                new ApiResponse(
                        false,
                        "error",
                        movie),
                HttpStatus.BAD_REQUEST);

    }


    //Delete Mapping
    @DeleteMapping("/{movieId}")
    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable String movieId) {
        try {
            movieService.deleteMovie(movieId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", movieId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", movieId), HttpStatus.BAD_REQUEST);
    }

}
