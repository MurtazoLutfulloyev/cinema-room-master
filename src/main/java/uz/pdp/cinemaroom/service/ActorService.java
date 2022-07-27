package uz.pdp.cinemaroom.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemaroom.entity.attachment.Attachment;
import uz.pdp.cinemaroom.entity.attachment.AttachmentContent;
import uz.pdp.cinemaroom.entity.movie.Actor;
import uz.pdp.cinemaroom.repository.ActorRepo;
import uz.pdp.cinemaroom.repository.AttachmentContentRepo;
import uz.pdp.cinemaroom.repository.AttachmentRepo;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ActorService {

    @Autowired
    private ActorRepo actorRepo;

    @Autowired
    private AttachmentRepo attachmentRepo;

    @Autowired
    private AttachmentContentRepo attachmentContentRepo;

    public List<Actor> getAllActors() {
        return actorRepo.findAll();
    }

    public void saveActor(Actor actor, MultipartFile file) throws IOException {

        Attachment attachment = new Attachment();
        attachment.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        attachment.setContent_type(file.getContentType());
        attachment.setSize(file.getSize());
        Attachment savedFile = attachmentRepo.save(attachment);

        actor.setAttachment(savedFile);
        actorRepo.save(actor);

        AttachmentContent fileAttachment = new AttachmentContent();
        fileAttachment.setAttachment(savedFile);
        fileAttachment.setData(file.getBytes());
        attachmentContentRepo.save(fileAttachment);

    }

    public void updateActor(String actorId, Actor actor, MultipartFile file) throws IOException {

        if (actorRepo.findById(actorId).isPresent()) {

            actor.setId(actorId);

            Actor actorFind = actorRepo.findById(actorId).get();

            if (file != null) {

                Attachment attachment = new Attachment();
                attachment.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
                attachment.setContent_type(file.getContentType());
                attachment.setSize(file.getSize());
                Attachment savedFile = attachmentRepo.save(attachment);
                actor.setAttachment(savedFile);
                AttachmentContent fileAttachment = new AttachmentContent();
                fileAttachment.setAttachment(savedFile);
                fileAttachment.setData(file.getBytes());
                attachmentContentRepo.save(fileAttachment);

            } else {
                actor.setAttachment(actorFind.getAttachment());
            }

            actorRepo.save(actor);
        }

    }

    public void deleteActor(String actorId) {

        actorRepo.deleteById(actorId);

    }
}
