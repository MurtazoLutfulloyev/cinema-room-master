package uz.pdp.cinemaroom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.attachment.Attachment;

@Repository
public interface AttachmentRepo extends JpaRepository<Attachment, String> {
}
