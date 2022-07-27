package uz.pdp.cinemaroom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.movie.Distributor;

@Repository
public interface DistributorRepo extends JpaRepository<Distributor, String> {

}
