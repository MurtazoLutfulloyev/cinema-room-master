package uz.pdp.cinemaroom.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemaroom.entity.attachment.Attachment;
import uz.pdp.cinemaroom.entity.attachment.AttachmentContent;
import uz.pdp.cinemaroom.entity.movie.Director;
import uz.pdp.cinemaroom.entity.movie.Distributor;
import uz.pdp.cinemaroom.repository.AttachmentContentRepo;
import uz.pdp.cinemaroom.repository.AttachmentRepo;
import uz.pdp.cinemaroom.repository.DistributorRepo;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class DistributorService {


    @Autowired
    private DistributorRepo distributorRepo;

    @Autowired
    private AttachmentRepo attachmentRepo;

    @Autowired
    private AttachmentContentRepo attachmentContentRepo;


    public List<Distributor> getAllDistributors() {
        return distributorRepo.findAll();
    }


    public void saveDistributor(Distributor distributor, MultipartFile file) throws IOException {

        Attachment attachment = new Attachment();
        attachment.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        attachment.setContent_type(file.getContentType());
        attachment.setSize(file.getSize());
        Attachment savedFile = attachmentRepo.save(attachment);

        distributor.setAttachment(savedFile);
        distributorRepo.save(distributor);

        AttachmentContent fileAttachment = new AttachmentContent();
        fileAttachment.setAttachment(savedFile);
        fileAttachment.setData(file.getBytes());
        attachmentContentRepo.save(fileAttachment);

    }

    public void updateDistributor(String distributorId, Distributor distributor, MultipartFile file) throws IOException {

        if (distributorRepo.findById(distributorId).isPresent()) {

            distributor.setId(distributorId);

            Distributor distributorFind = distributorRepo.findById(distributorId).get();

            if (file != null) {

                Attachment attachment = new Attachment();
                attachment.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
                attachment.setContent_type(file.getContentType());
                attachment.setSize(file.getSize());
                Attachment savedFile = attachmentRepo.save(attachment);
                distributorFind.setAttachment(savedFile);
                AttachmentContent fileAttachment = new AttachmentContent();
                fileAttachment.setAttachment(savedFile);
                fileAttachment.setData(file.getBytes());
                attachmentContentRepo.save(fileAttachment);

            } else {
                distributor.setAttachment(distributorFind.getAttachment());
            }

            distributorRepo.save(distributor);
        }

    }

    public void deleteDistributor(String distributorId) {

        distributorRepo.deleteById(distributorId);

    }
}
