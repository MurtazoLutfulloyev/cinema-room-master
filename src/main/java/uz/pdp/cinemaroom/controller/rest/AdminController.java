package uz.pdp.cinemaroom.controller.rest;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemaroom.payload.ApiResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @GetMapping
    public ResponseEntity<ApiResponse> getStats() {
        return new ResponseEntity<>(new ApiResponse(
                true, "success", null
        ), HttpStatus.OK);
    }


}
