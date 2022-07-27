package uz.pdp.cinemaroom.controller.rest;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemaroom.entity.movie.Distributor;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.DistributorService;

import java.io.IOException;

@RestController
@RequestMapping("/api/distributor")
public class DistributorController {


    @Autowired
    private DistributorService distributorService;


    //Get All
    @GetMapping
    public ResponseEntity<ApiResponse> getAllDistributors() {
        return new ResponseEntity<>(new ApiResponse(true, "success", distributorService.getAllDistributors()), HttpStatus.OK);
    }


    //Create
    @PostMapping
    public ResponseEntity<ApiResponse> saveDistributor(
            @RequestPart("brand") MultipartFile brand,
            @RequestPart("distributor") Distributor distributor
    ) {
        try {
            distributorService.saveDistributor(distributor, brand);
            return new ResponseEntity<>(new ApiResponse(true, "created", distributor), HttpStatus.OK);
        } catch (IOException ignored) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", distributor), HttpStatus.BAD_REQUEST);
    }


    //update
    @PutMapping("/{distributorId}")
    public ResponseEntity<ApiResponse> updateDistributor(
            @PathVariable String distributorId,
            @RequestPart(value = "brand", required = false) MultipartFile brand,
            @RequestPart("distributor") Distributor distributor
    ) {
        try {

            distributorService.updateDistributor(distributorId, distributor, brand);
            return new ResponseEntity<>(
                    new ApiResponse(true,
                            "updated",
                            distributor),
                    HttpStatus.OK);

        } catch (IOException ignored) {
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", distributor), HttpStatus.BAD_REQUEST);
    }

    //delete
    @DeleteMapping("/{distributorId}")
    public ResponseEntity<ApiResponse> deleteDistributor(@PathVariable String distributorId) {
        try {
            distributorService.deleteDistributor(distributorId);
            return new ResponseEntity<>(
                    new ApiResponse(true, "deleted", distributorId),
                    HttpStatus.OK);

        } catch (Exception ignored) {
            ignored.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", distributorId), HttpStatus.BAD_REQUEST);
    }


}
