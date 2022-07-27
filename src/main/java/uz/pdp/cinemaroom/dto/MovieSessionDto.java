package uz.pdp.cinemaroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class MovieSessionDto {

    private String movieAnnouncementId;
    private String hallId;
    private String startDate;
    private String startTime;

}
