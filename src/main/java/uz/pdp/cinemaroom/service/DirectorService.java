package uz.pdp.cinemaroom.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemaroom.entity.attachment.Attachment;
import uz.pdp.cinemaroom.entity.attachment.AttachmentContent;
import uz.pdp.cinemaroom.entity.movie.Actor;
import uz.pdp.cinemaroom.entity.movie.Director;
import uz.pdp.cinemaroom.repository.AttachmentContentRepo;
import uz.pdp.cinemaroom.repository.AttachmentRepo;
import uz.pdp.cinemaroom.repository.DirectorRepo;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepo directorRepo;

    @Autowired
    private AttachmentRepo attachmentRepo;

    @Autowired
    private AttachmentContentRepo attachmentContentRepo;

    public List<Director> getAllDirectors() {
        return directorRepo.findAll();
    }

    public void saveDirector(Director director, MultipartFile file) throws IOException {

        Attachment attachment = new Attachment();
        attachment.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        attachment.setContent_type(file.getContentType());
        attachment.setSize(file.getSize());
        Attachment savedFile = attachmentRepo.save(attachment);

        director.setAttachment(savedFile);
        directorRepo.save(director);

        AttachmentContent fileAttachment = new AttachmentContent();
        fileAttachment.setAttachment(savedFile);
        fileAttachment.setData(file.getBytes());
        attachmentContentRepo.save(fileAttachment);

    }

    public void updateDirector(String directorId, Director director, MultipartFile file) throws IOException {
        if (directorRepo.findById(directorId).isPresent()) {

            director.setId(directorId);

            Director directorFind = directorRepo.findById(directorId).get();

            if (file != null) {

                Attachment attachment = new Attachment();
                attachment.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
                attachment.setContent_type(file.getContentType());
                attachment.setSize(file.getSize());
                Attachment savedFile = attachmentRepo.save(attachment);
                director.setAttachment(savedFile);
                AttachmentContent fileAttachment = new AttachmentContent();
                fileAttachment.setAttachment(savedFile);
                fileAttachment.setData(file.getBytes());
                attachmentContentRepo.save(fileAttachment);

            } else {
                director.setAttachment(directorFind.getAttachment());
            }

            directorRepo.save(director);
        }
    }

    public void deleteDirector(String directorId) {

        directorRepo.deleteById(directorId);

    }


}
