package uz.pdp.cinemaroom.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemaroom.entity.cinema.Hall;
import uz.pdp.cinemaroom.repository.HallRepo;

import java.util.List;
import java.util.Optional;

@Service
public class HallService {


    @Autowired
    private HallRepo hallRepo;

    public List<Hall> getAllHall() {
        return hallRepo.findAll();
    }

    public void saveHall(Hall hall) {
        hallRepo.save(hall);
    }

    public void updateHall(String hallId, Hall hallData) {

        Optional<Hall> hallOptional = hallRepo.findById(hallId);
        if (hallOptional.isPresent()) {
            Hall hall = hallOptional.get();
            hallData.setId(hall.getId());
            hallRepo.save(hallData);

        }
    }

    public void deleteHall(String hallId) {
        hallRepo.deleteById(hallId);
    }

}
