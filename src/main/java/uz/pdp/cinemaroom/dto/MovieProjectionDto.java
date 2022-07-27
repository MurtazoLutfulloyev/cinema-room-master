package uz.pdp.cinemaroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemaroom.entity.cinema.Hall;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class MovieProjectionDto {

    private String id;

    private String movieTitle;

    private String coverImageId;

    private String startDate;

    private List<HallDto> halls = new ArrayList<>();

}
