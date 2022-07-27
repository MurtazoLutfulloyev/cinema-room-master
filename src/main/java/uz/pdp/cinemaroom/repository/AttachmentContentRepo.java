package uz.pdp.cinemaroom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.attachment.AttachmentContent;

@Repository
public interface AttachmentContentRepo extends JpaRepository<AttachmentContent, String> {
}
