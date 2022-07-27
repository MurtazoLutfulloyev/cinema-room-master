package uz.pdp.cinemaroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class CartDto {
    private String userId;
    private String movieSessionId;
    private String timeId;
    private String seatId;
}
