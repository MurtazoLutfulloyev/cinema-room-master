package uz.pdp.cinemaroom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.cinemaroom.entity.attachment.Attachment;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class MovieDto {

    private String title;
    private String description;
    private Integer durationMinutes;
    private String trailerVideo;
    private List<String> directors = new ArrayList<>();
    private List<String> genres = new ArrayList<>();
    private List<String> actors = new ArrayList<>();
    private Double minPrice;
    private String distributor;
    private Double distributor_share_in_percent;
}
