package uz.pdp.cinemaroom.controller.rest;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.entity.session.NightSessionAddFee;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.NightSessionAddFeeService;

@RestController
@RequestMapping("/api/nightSessionFee")
public class NightSessionFeeController {

    @Autowired
    private NightSessionAddFeeService nightSessionAddFeeService;

    //Get All
    @GetMapping
    public ResponseEntity<ApiResponse> getAllNightSessions() {
        return new ResponseEntity<>(new ApiResponse(true, "success", nightSessionAddFeeService.getNightSessionFee()), HttpStatus.OK);
    }

    //Create
    @PostMapping
    public ResponseEntity<ApiResponse> saveNightSession(@RequestBody NightSessionAddFee nightSessionAddFee) {
        try {
            nightSessionAddFeeService.saveNightSessionAddFee(nightSessionAddFee);
            return new ResponseEntity<>(new ApiResponse(true, "created", nightSessionAddFee), HttpStatus.OK);
        } catch (Exception ignored) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", nightSessionAddFee), HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping(value = "/{nightSessionId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse> updateNightSession(@PathVariable String nightSessionId, @RequestBody NightSessionAddFee nightSessionAddFee) {
        try {
            nightSessionAddFeeService.updateNightSessionAddFee(nightSessionId, nightSessionAddFee);
            return new ResponseEntity<>(new ApiResponse(true, "updated", nightSessionAddFee), HttpStatus.OK);
        } catch (Exception ignored) {
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", nightSessionAddFee), HttpStatus.BAD_REQUEST);
    }

    //delete
    @DeleteMapping("/{nightSessionId}")
    public ResponseEntity<ApiResponse> deleteNightSession(@PathVariable String nightSessionId) {
        try {
            nightSessionAddFeeService.deleteNightSessionAddFee(nightSessionId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", nightSessionId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", nightSessionId), HttpStatus.BAD_REQUEST);
    }
}
