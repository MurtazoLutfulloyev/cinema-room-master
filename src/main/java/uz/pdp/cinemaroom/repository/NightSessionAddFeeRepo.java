package uz.pdp.cinemaroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.session.NightSessionAddFee;

@Repository
public interface NightSessionAddFeeRepo extends JpaRepository<NightSessionAddFee, String> {
}
