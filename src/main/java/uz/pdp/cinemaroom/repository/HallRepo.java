package uz.pdp.cinemaroom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.cinema.Hall;

@Repository
public interface HallRepo extends JpaRepository<Hall, String> {
}
