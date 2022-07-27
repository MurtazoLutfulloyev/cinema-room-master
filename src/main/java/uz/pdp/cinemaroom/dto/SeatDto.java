package uz.pdp.cinemaroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class SeatDto {

    private Integer startNumber;
    private Integer endNumber;
    private String price_category_id;
}
