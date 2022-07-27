package uz.pdp.cinemaroom.controller.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemaroom.entity.movie.Director;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.DirectorService;

import java.io.IOException;

@RestController
@RequestMapping("/api/director")
public class DirectorController {


    @Autowired
    private DirectorService directorService;


    //Get All
    @GetMapping
    public ResponseEntity<ApiResponse> getAllDirectors() {
        return new ResponseEntity<>(new ApiResponse(true, "success", directorService.getAllDirectors()), HttpStatus.OK);
    }

    //Create
    @PostMapping
    public ResponseEntity<ApiResponse> saveDirector(
            @RequestPart("director") Director director,
            @RequestPart("image") MultipartFile image) {
        try {
            directorService.saveDirector(director, image);
            return new ResponseEntity<>(new ApiResponse(true, "created", director), HttpStatus.OK);
        } catch (IOException ignored) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", director), HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping("/{directorId}")
    public ResponseEntity<ApiResponse> updateDirector(
            @PathVariable String directorId,
            @RequestPart("director") Director director,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        try {
            directorService.updateDirector(directorId, director, image);
            return new ResponseEntity<>(new ApiResponse(true, "updated", director), HttpStatus.OK);
        } catch (IOException ignored) {
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", director), HttpStatus.BAD_REQUEST);
    }

    //delete
    @DeleteMapping("/{directorId}")
    public ResponseEntity<ApiResponse> deleteDirector(@PathVariable String directorId) {
        try {
            directorService.deleteDirector(directorId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", directorId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", directorId), HttpStatus.BAD_REQUEST);
    }

}
