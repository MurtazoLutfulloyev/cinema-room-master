package uz.pdp.cinemaroom.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemaroom.entity.paytype.WaitingPurchaseTime;

public interface WaitingPurchaseTimeRepo extends JpaRepository<WaitingPurchaseTime, String> {
}
