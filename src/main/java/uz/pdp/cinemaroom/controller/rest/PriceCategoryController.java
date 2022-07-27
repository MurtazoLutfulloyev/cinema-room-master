package uz.pdp.cinemaroom.controller.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.entity.cinema.PriceCategory;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.service.PriceCategoryService;

@RestController
@RequestMapping("/api/priceCategory")
public class PriceCategoryController {


    @Autowired
    private PriceCategoryService priceCategoryService;

    //Get All
    @GetMapping
    public ResponseEntity<ApiResponse> getAllPriceCategories() {
        return new ResponseEntity<>(new ApiResponse(true, "success", priceCategoryService.getPriceCategory()), HttpStatus.OK);
    }

    //Create
    @PostMapping
    public ResponseEntity<ApiResponse> savePriceCategory(@RequestBody PriceCategory priceCategory) {
        try {
            priceCategoryService.savePriceCategory(priceCategory);
            return new ResponseEntity<>(new ApiResponse(true, "created", priceCategory), HttpStatus.OK);
        } catch (Exception e) {
        }
        return new ResponseEntity<>(new ApiResponse(false, "error", priceCategory), HttpStatus.BAD_REQUEST);
    }

    //update
    @PutMapping("/{priceCategoryId}")
    public ResponseEntity<ApiResponse> updatePriceCAtegory(@PathVariable String priceCategoryId, @RequestBody PriceCategory priceCategory) {
        try {
            priceCategoryService.updatePriceCategory(priceCategoryId, priceCategory);
            return new ResponseEntity<>(new ApiResponse(true, "updated", priceCategory), HttpStatus.OK);
        } catch (Exception e) {
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", priceCategory), HttpStatus.BAD_REQUEST);
    }

    //delete
    @DeleteMapping("/{priceCategoryId}")
    public ResponseEntity<ApiResponse> deletePriceCategory(@PathVariable String priceCategoryId) {
        try {
            priceCategoryService.deletePriceCategory(priceCategoryId);
            return new ResponseEntity<>(new ApiResponse(true, "deleted", priceCategoryId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return new ResponseEntity<>(new ApiResponse(false, "error", priceCategoryId), HttpStatus.BAD_REQUEST);
    }

}
