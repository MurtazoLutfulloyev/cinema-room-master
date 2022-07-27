package uz.pdp.cinemaroom.controller.rest;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.dto.CartDto;
import uz.pdp.cinemaroom.dto.TicketDto;
import uz.pdp.cinemaroom.helper.LoadPDF;
import uz.pdp.cinemaroom.helper.TicketQRCode;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.TicketService;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketQRCode ticketQRCode;


    @PostMapping("/add-to-cart")
    public ResponseEntity<ApiResponse> savePriceCategory(@RequestBody CartDto cartDto) {
        try {
            ticketService.addToCartTicket(cartDto);
            return new ResponseEntity<>(new ApiResponse(true, "success", cartDto), HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", null), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/transaction-ticket/{ticketId}")
    public ResponseEntity<ApiResponse> changeTicketStatus(@PathVariable String ticketId) {
        try {
//            ticketService.changeTicketStatus(ticketId, paymentIntentId);
            return new ResponseEntity<>(new ApiResponse(true, "success", ticketId), HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", null), HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/get-qr-code/{ticketId}")
    public ResponseEntity<ApiResponse> getTicketPdf(@PathVariable String ticketId, HttpServletResponse response) {
        try {
           TicketDto ticketDto = ticketService.getTicketDate(ticketId);
            response.setHeader("Content-Disposition", "attachment; filename\"" + "code" + "\"");
            response.setContentType("image/png");
            ticketQRCode.generateTicketPDF(ticketDto);
            FileCopyUtils.copy(ticketQRCode.getQRCodeImage(ticketDto.toString(), 150, 150), response.getOutputStream());
            return ResponseEntity.status(204).build();
        } catch (Exception e) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", null), HttpStatus.BAD_REQUEST);

    }

    @GetMapping(
            value = "/get-qr-code/pdf/{ticketId}")
    public ResponseEntity<?> getTicketPdf(@PathVariable String ticketId) {

        TicketDto ticketDto = ticketService.getTicketDate(ticketId);
        ticketQRCode.generateTicketPDF(ticketDto);

        InputStreamResource file = new InputStreamResource(exportPDF());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;attachment; filename=ticket.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }

    LoadPDF pdf = new LoadPDF();

    public InputStream exportPDF() {
        return pdf.getPDF();
    }


}


