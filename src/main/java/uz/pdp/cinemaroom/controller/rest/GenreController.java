package uz.pdp.cinemaroom.controller.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.entity.movie.Genre;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.GenreService;

@RestController
@RequestMapping("/api/genre")
public class GenreController {


    @Autowired
    private GenreService genreService;

    //Get All
    @GetMapping
    public ResponseEntity<ApiResponse> getAllGenre() {
        return new ResponseEntity<>(new ApiResponse(true, "success", genreService.getAllGenre()), HttpStatus.OK);
    }

    //Create
    @PostMapping
    public ResponseEntity<ApiResponse> saveGenre(@RequestBody Genre genre) {
        try {
            genreService.saveGenre(genre);
            return new ResponseEntity<>(new ApiResponse(true, "created", genre), HttpStatus.OK);
        } catch (Exception ignored) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", genre), HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping(value = "/{genreId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse> updateGenre(@PathVariable String genreId, @RequestBody Genre genre) {
        try {
            genreService.updateGenre(genreId, genre);
            return new ResponseEntity<>(new ApiResponse(true, "updated", genre), HttpStatus.OK);
        } catch (Exception ignored) {
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", genre), HttpStatus.BAD_REQUEST);
    }

    //delete
    @DeleteMapping("/{genreId}")
    public ResponseEntity<ApiResponse> deleteGenre(@PathVariable String genreId) {
        try {
            genreService.deleteGenre(genreId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", genreId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", genreId), HttpStatus.BAD_REQUEST);
    }

}
