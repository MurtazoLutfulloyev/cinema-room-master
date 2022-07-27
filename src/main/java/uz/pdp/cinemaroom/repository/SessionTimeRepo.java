package uz.pdp.cinemaroom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.session.SessionTime;

import java.sql.Time;

@Repository
public interface SessionTimeRepo extends JpaRepository<SessionTime, String> {

}
