package uz.pdp.cinemaroom.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.cinemaroom.entity.movie.Genre;
import uz.pdp.cinemaroom.repository.GenreRepo;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepo genreRepo;


    public List<Genre> getAllGenre() {
        return genreRepo.findAll();
    }

    public void saveGenre(Genre genre) {
        genreRepo.save(genre);
    }


    public void updateGenre(String genreId, Genre genre) {
        Optional<Genre> genreOp = genreRepo.findById(genreId);
        if (genreOp.isPresent()) {
            Genre gen = genreOp.get();
            genre.setId(gen.getId());
            genreRepo.save(genre);

        }
    }

    public void deleteGenre(String genreId) {
        genreRepo.deleteById(genreId);
    }

}
