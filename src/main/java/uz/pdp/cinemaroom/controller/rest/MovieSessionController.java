package uz.pdp.cinemaroom.controller.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.dto.MovieSessionDto;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.MovieSessionService;

@RestController
@RequestMapping("/api/movieSession")
public class MovieSessionController {


    @Autowired
    private MovieSessionService movieSessionService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllMovieSession() {
        return new ResponseEntity<>(new ApiResponse(true, "success", movieSessionService.getAllReservedHall()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveMovieSession(@RequestBody MovieSessionDto movieSessionDto) {
        try {
            movieSessionService.saveSessionHall(movieSessionDto);
            return new ResponseEntity<>(new ApiResponse(true, "created", movieSessionDto), HttpStatus.OK);
        } catch (Exception ignored) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", movieSessionDto), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{movieSessionId}")
    public ResponseEntity<ApiResponse> deleteMovieSession(@PathVariable String movieSessionId) {
        try {
//            reservedHallService.deleteSessionHall(reserveID);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", movieSessionId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", movieSessionId), HttpStatus.BAD_REQUEST);
    }

}
