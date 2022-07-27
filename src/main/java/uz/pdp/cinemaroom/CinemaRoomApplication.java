package uz.pdp.cinemaroom;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uz.pdp.cinemaroom.service.search.Indexer;

@SpringBootApplication
public class CinemaRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaRoomApplication.class, args);
    }


    @Bean
    public ApplicationRunner buildIndex(Indexer indexer) throws Exception {
        return (ApplicationArguments args) -> {
            indexer.indexPersistedData("uz.pdp.cinemaroom.entity.movie.Movie");
        };
    }
}
