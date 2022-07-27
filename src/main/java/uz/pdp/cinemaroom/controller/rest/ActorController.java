package uz.pdp.cinemaroom.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemaroom.entity.movie.Actor;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.ActorService;

import java.io.IOException;

@RestController
@RequestMapping("/api/actor")
public class ActorController {


    @Autowired
    private ActorService actorService;


    //Get All
    @GetMapping
    public ResponseEntity<ApiResponse> getAllActors() {
        return new ResponseEntity<>(new ApiResponse(true, "success", actorService.getAllActors()), HttpStatus.OK);
    }

    //Create
    @PostMapping
    public ResponseEntity<ApiResponse> saveActor(
            @RequestPart("actor") Actor actor,
            @RequestPart("image") MultipartFile image
    ) {
        try {
            actorService.saveActor(actor, image);
            return new ResponseEntity<>(new ApiResponse(true,
                    "created",
                    actor),
                    HttpStatus.OK);

        } catch (IOException ignored) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", actor), HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping("/{actorId}")
    public ResponseEntity<ApiResponse> updateActor(
            @PathVariable String actorId,
            @RequestPart("actor") Actor actor,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        try {
            actorService.updateActor(actorId, actor, image);
            return new ResponseEntity<>(new ApiResponse(true, "updated", actor), HttpStatus.OK);
        } catch (IOException ignored) {
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", actor), HttpStatus.BAD_REQUEST);
    }

    //delete
    @DeleteMapping("/{actorId}")
    public ResponseEntity<ApiResponse> deleteActor(@PathVariable String actorId) {
        try {
            actorService.deleteActor(actorId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", actorId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", actorId), HttpStatus.BAD_REQUEST);
    }


}
