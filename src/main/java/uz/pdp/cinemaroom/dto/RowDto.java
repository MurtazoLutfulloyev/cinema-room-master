package uz.pdp.cinemaroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemaroom.entity.cinema.Hall;
import uz.pdp.cinemaroom.entity.cinema.Seat;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class RowDto {

    private Integer number;

    private String hall_id;

    private List<SeatDto> seats = new ArrayList<>();
}
