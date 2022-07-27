package uz.pdp.cinemaroom.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemaroom.entity.session.NightSessionAddFee;
import uz.pdp.cinemaroom.repository.NightSessionAddFeeRepo;

import java.util.List;

@Service
public class NightSessionAddFeeService {


    @Autowired
    private NightSessionAddFeeRepo nightSessionAddFeeRepo;


    public List<NightSessionAddFee> getNightSessionFee() {
        return nightSessionAddFeeRepo.findAll();
    }

    public void saveNightSessionAddFee(NightSessionAddFee nightSessionAddFee) {

        nightSessionAddFeeRepo.save(nightSessionAddFee);
    }

    public void updateNightSessionAddFee(String nightSessionAddFeeId, NightSessionAddFee nightSessionAddFee) {
        if (nightSessionAddFeeRepo.findById(nightSessionAddFeeId).isPresent()) {
            nightSessionAddFee.setId(nightSessionAddFeeId);
            nightSessionAddFeeRepo.save(nightSessionAddFee);
        }
    }

    public void deleteNightSessionAddFee(String nightSessionAddFee) {
        nightSessionAddFeeRepo.deleteById(nightSessionAddFee);
    }


}
