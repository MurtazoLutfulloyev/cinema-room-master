package uz.pdp.cinemaroom.service.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemaroom.entity.movie.Movie;

import java.util.List;



@Slf4j
@RestController
@RequestMapping("/api/search")
public class MovieSearchController {


    @Autowired
    private MovieSearchService movieSearchService;


    @GetMapping("/movie")
    public List<Movie> searchMovies(SearchRequestDTO searchRequestDTO) {

        log.info("Request for plant search received with data : " + searchRequestDTO);

        return movieSearchService.searchMovie(searchRequestDTO.getText(), searchRequestDTO.getFields(), searchRequestDTO.getLimit());
    }

}
