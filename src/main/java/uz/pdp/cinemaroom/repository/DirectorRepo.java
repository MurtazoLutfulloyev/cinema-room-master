package uz.pdp.cinemaroom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.movie.Director;

@Repository
public interface DirectorRepo extends JpaRepository<Director, String> {
}
