package uz.pdp.cinemaroom.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemaroom.dto.RowDto;
import uz.pdp.cinemaroom.dto.SeatDto;
import uz.pdp.cinemaroom.entity.cinema.Hall;
import uz.pdp.cinemaroom.entity.cinema.PriceCategory;
import uz.pdp.cinemaroom.entity.cinema.Row;
import uz.pdp.cinemaroom.entity.cinema.Seat;
import uz.pdp.cinemaroom.repository.HallRepo;
import uz.pdp.cinemaroom.repository.PriceCategoryRepo;
import uz.pdp.cinemaroom.repository.RowRepo;
import uz.pdp.cinemaroom.repository.SeatRepo;

@Service
public class RowService {


    @Autowired
    private RowRepo rowRepo;

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private HallRepo hallRepo;

    @Autowired
    private PriceCategoryRepo priceCategoryRepo;


    public void saveRow(RowDto rowDto) {
        if (hallRepo.findById(rowDto.getHall_id()).isPresent()) {

            Hall hall = hallRepo.findById(rowDto.getHall_id()).get();

            Row row = new Row();

            row.setHall(hall);
            row.setNumber(rowDto.getNumber());
            Row savedRow = rowRepo.save(row);

            //Add Seat with price Category
            for (SeatDto seatDto : rowDto.getSeats()) {
                if (priceCategoryRepo.findById(seatDto.getPrice_category_id()).isPresent()) {
                    PriceCategory priceCategory = priceCategoryRepo.findById(seatDto.getPrice_category_id()).get();
                    for (int i = seatDto.getStartNumber(); i <= seatDto.getEndNumber(); i++) {
                        Seat seat = new Seat();
                        seat.setRow(savedRow);
                        seat.setPriceCategory(priceCategory);
                        seat.setNumber(i);
                        seatRepo.save(seat);
                    }
                }
            }

            //Row Saved With Seat
        }

    }


    public void updateRow(String rowId, RowDto rowDto) {
    }

    public void deleteRow(String rowId) {
    }

}
