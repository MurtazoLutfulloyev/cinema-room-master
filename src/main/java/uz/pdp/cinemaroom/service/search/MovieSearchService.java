package uz.pdp.cinemaroom.service.search;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemaroom.entity.movie.Movie;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieSearchService {

    @Autowired
    private MovieRepository movieRepository;


    private static final List<String> SEARCHABLE_FIELDS = Arrays.asList("title", "description");

    public List<Movie> searchMovie(String text, List<String> fields, int limit) {

        List<String> fieldsToSearchBy = fields.isEmpty() ? SEARCHABLE_FIELDS : fields;

        boolean containsInvalidField = fieldsToSearchBy.stream().anyMatch(f -> !SEARCHABLE_FIELDS.contains(f));

        if (containsInvalidField) {
            throw new IllegalArgumentException();
        }

        return movieRepository.searchBy(text, limit, fieldsToSearchBy.toArray(new String[0]));
    }
}
