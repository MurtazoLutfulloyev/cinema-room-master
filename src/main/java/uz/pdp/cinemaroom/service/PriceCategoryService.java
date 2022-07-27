package uz.pdp.cinemaroom.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemaroom.entity.cinema.PriceCategory;
import uz.pdp.cinemaroom.repository.PriceCategoryRepo;

import java.util.List;

@Service
public class PriceCategoryService {


    @Autowired
    private PriceCategoryRepo priceCategoryRepo;

    public List<PriceCategory> getPriceCategory() {
        return priceCategoryRepo.findAll();
    }

    public void savePriceCategory(PriceCategory priceCategory) {
        priceCategoryRepo.save(priceCategory);
    }

    public void updatePriceCategory(String priceCategoryId, PriceCategory priceCategory) {
        if (priceCategoryRepo.findById(priceCategoryId).isPresent()) {
            priceCategory.setId(priceCategoryId);
            priceCategoryRepo.save(priceCategory);
        }
    }

    public void deletePriceCategory(String priceCategoryId) {
        try {
            priceCategoryRepo.deleteById(priceCategoryId);
        } catch (Exception e) {
        }
    }


}
