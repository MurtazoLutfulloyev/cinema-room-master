package uz.pdp.cinemaroom.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemaroom.entity.enums.Gender;
import uz.pdp.cinemaroom.entity.user.User;
import uz.pdp.cinemaroom.entity.user.UserDetail;
import uz.pdp.cinemaroom.projection.SeatProjection;
import uz.pdp.cinemaroom.repository.SeatRepo;
import uz.pdp.cinemaroom.repository.UserRepo;

import java.util.Date;
import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepo seatRepo;


    public List<SeatProjection> getAvailableSeats(String movieSessionId, String startTimeId) {
        return seatRepo.getAvailableSeats(movieSessionId, startTimeId);
    }
}
