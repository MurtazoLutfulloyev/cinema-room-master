package uz.pdp.cinemaroom.controller.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.dto.MovieAnnouncementDto;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.MovieAnnouncementService;

@RestController
@RequestMapping("/api/movieAnnouncement")
public class MovieAnnouncementController {

    @Autowired
    private MovieAnnouncementService movieAnnouncementService;


    //Get All
    @GetMapping
    public ResponseEntity<ApiResponse> getAllMovieAnnouncement() {
        return new ResponseEntity<>(new ApiResponse(true,
                "success",
                movieAnnouncementService.getAllAfishas()), HttpStatus.OK);
    }

    @GetMapping("/movieAnnouncement")
    public ResponseEntity<ApiResponse> getAlMovieAnnouncementByDate() {
        return new ResponseEntity<>(new ApiResponse(true,
                "success",
                movieAnnouncementService.getAllAfishas()), HttpStatus.OK);
    }

    //Create
    @PostMapping
    public ResponseEntity<ApiResponse> saveMovieAnnouncement(@RequestBody MovieAnnouncementDto movieAnnouncementDto) {
        try {
            movieAnnouncementService.saveMovieSession(movieAnnouncementDto);
            return new ResponseEntity<>(new ApiResponse(true, "created", movieAnnouncementDto), HttpStatus.OK);
        } catch (Exception ignored) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", movieAnnouncementDto), HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping(value = "/{afishaId}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse> updateMovieAnnouncement(@PathVariable String afishaId, @RequestBody MovieAnnouncementDto movieAnnouncementDto) {
        try {
            movieAnnouncementService.updateMovieSessionId(afishaId, movieAnnouncementDto);
            return new ResponseEntity<>(new ApiResponse(true, "updated", movieAnnouncementDto), HttpStatus.OK);
        } catch (Exception ignored) {
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", movieAnnouncementDto), HttpStatus.BAD_REQUEST);
    }

    //delete
    @DeleteMapping("/{afishaId}")
    public ResponseEntity<ApiResponse> deleteMovieAnnouncement(@PathVariable String afishaId) {
        try {
            movieAnnouncementService.deleteMovieSession(afishaId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", afishaId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", afishaId), HttpStatus.BAD_REQUEST);
    }


}
