package uz.pdp.cinemaroom.controller.rest.emial;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.dto.EmailDto;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.SendEmailMessage;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailSenderController {

    private final SendEmailMessage sendEmailMessage;

    @PostMapping("/{ticketId}")
    public ResponseEntity<ApiResponse> savePriceCategory(@RequestBody EmailDto emailDto, @PathVariable String ticketId) {
        try {
            sendEmailMessage.sendEmail(emailDto,null);
            return new ResponseEntity<>(new ApiResponse(true, "send", null), HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "sending error", null), HttpStatus.BAD_REQUEST);
    }
}
