package uz.pdp.cinemaroom.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemaroom.entity.attachment.Attachment;
import uz.pdp.cinemaroom.entity.attachment.AttachmentContent;
import uz.pdp.cinemaroom.repository.AttachmentContentRepo;
import uz.pdp.cinemaroom.repository.AttachmentRepo;

import java.util.List;

@Service
public class AttachmentService {


    @Autowired
    private AttachmentRepo attachmentRepo;

    @Autowired
    private AttachmentContentRepo attachmentContentRepo;


    public Attachment getAttachment(String id) {
        return attachmentRepo.findById(id).get();
    }

    public AttachmentContent getFileAttachment(String id) {

        List<AttachmentContent> all = attachmentContentRepo.findAll();

        for (AttachmentContent fileAttachment : all) {
            if (fileAttachment.getAttachment().getId().equals(id)) {
                return fileAttachment;
            }
        }
        return null;
    }
}
