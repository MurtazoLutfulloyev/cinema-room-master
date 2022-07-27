package uz.pdp.cinemaroom.controller.rest;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.entity.cinema.Hall;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.HallService;

@RestController
@RequestMapping("/api/hall")

public class HallController {


    @Autowired
    private HallService hallService;


    //Get All
    @GetMapping
    public ResponseEntity<ApiResponse> getAllHalls() {
        return new ResponseEntity<>(new ApiResponse(true, "success", hallService.getAllHall()), HttpStatus.OK);
    }

    //Create
    @PostMapping
    public ResponseEntity<ApiResponse> saveHall(@RequestBody Hall hall) {
        try {
            hallService.saveHall(hall);
            return new ResponseEntity<>(new ApiResponse(true, "created", hall), HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", hall), HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping("/{hallId}")
    public ResponseEntity<ApiResponse> updateHall(@PathVariable String hallId, @RequestBody Hall hall) {
        try {
            hallService.updateHall(hallId, hall);
            return new ResponseEntity<>(new ApiResponse(true, "updated", hall),
                    HttpStatus.OK);
        } catch (Exception e) {
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", hall),
                HttpStatus.BAD_REQUEST);
    }

    //delete
    @DeleteMapping("/{hallId}")
    public ResponseEntity<ApiResponse> deleteHall(@PathVariable String hallId) {
        try {
            hallService.deleteHall(hallId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", hallId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", hallId), HttpStatus.BAD_REQUEST);
    }


}
