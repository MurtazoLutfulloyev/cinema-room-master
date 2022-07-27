package uz.pdp.cinemaroom.controller.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.entity.attachment.Attachment;
import uz.pdp.cinemaroom.entity.attachment.AttachmentContent;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {


    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("/{attachmentId}")
    public ResponseEntity<ApiResponse> getAttachment(@PathVariable String attachmentId, HttpServletResponse response) {

        try {

            AttachmentContent attachmentContent = attachmentService.getFileAttachment(attachmentId);
            Attachment attachment = attachmentService.getAttachment(attachmentId);
            response.setHeader("Content-Disposition", "attachment; filename\"" + attachment.getName() + "\"");
            response.setContentType(attachment.getContent_type());
            FileCopyUtils.copy(attachmentContent.getData(), response.getOutputStream());
            return ResponseEntity.status(204).build();

        } catch (Exception e) {
        }
        return new ResponseEntity<>(
                new ApiResponse(
                        true,
                        "error",
                        null),
                HttpStatus.NOT_FOUND);
    }


}
