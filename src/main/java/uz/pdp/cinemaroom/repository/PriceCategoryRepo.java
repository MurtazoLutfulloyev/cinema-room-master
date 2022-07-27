package uz.pdp.cinemaroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.cinema.PriceCategory;

@Repository
public interface PriceCategoryRepo extends JpaRepository<PriceCategory, String> {
}
