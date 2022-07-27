package uz.pdp.cinemaroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class HallDto {
    private String id;
    private String name;
    private String movie_session_id;
    private List<SessionTimeDto> startEndTime = new ArrayList<>();
}
