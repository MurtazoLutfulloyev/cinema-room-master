package uz.pdp.cinemaroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class RefundDto {

    private String ticketId;
    private String paymentIntentId;
}
