package uz.pdp.cinemaroom.controller.rest;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.dto.RowDto;
import uz.pdp.cinemaroom.entity.cinema.PriceCategory;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.repository.SeatRepo;
import uz.pdp.cinemaroom.service.RowService;
import uz.pdp.cinemaroom.service.SeatService;

@RestController
@RequestMapping("/api/row")
public class RowController {


    @Autowired
    private RowService rowService;

    @Autowired
    private SeatService seatService;

    @GetMapping("/show-available-seats/{movieSessionId}/{startTimeId}")
    public ResponseEntity<ApiResponse> savePriceCategory(@PathVariable String movieSessionId, @PathVariable String startTimeId) {
        try {

            return new ResponseEntity<>(new ApiResponse(true, "success", seatService.getAvailableSeats(movieSessionId, startTimeId)), HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", null), HttpStatus.BAD_REQUEST);
    }


    //Create
    @PostMapping
    public ResponseEntity<ApiResponse> savePriceCategory(@RequestBody RowDto rowDto) {
        try {
            rowService.saveRow(rowDto);
            return new ResponseEntity<>(new ApiResponse(true, "created", rowDto), HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", rowDto), HttpStatus.BAD_REQUEST);
    }


}
