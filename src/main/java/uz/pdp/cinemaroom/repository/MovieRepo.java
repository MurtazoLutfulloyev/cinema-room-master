package uz.pdp.cinemaroom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.movie.Movie;
import uz.pdp.cinemaroom.projection.MovieProjection;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie, String> {


    @Query(nativeQuery = true,
            value = "select cast(m.id as varchar) as id,\n" +
                    "       m.title as movieTitle,\n" +
                    "       cast(a.id as varchar) as coverImageId \n" +
                    "from movies m " +
                    "join attachments a on a.id = m.attachment_id")
    Page<MovieProjection> findAllByPage(Pageable pageable);

    @Query(nativeQuery = true,
            value = "select cast(json_build_object('id', cte.id, 'movieTitle',cte.movieTitle,'coverImageId',cte.coverImageId,'halls',cte.halls) as text)\n" +
                    "from (select cast(movies.id as varchar) as id,\n" +
                    "             movies.title as movieTitle,\n" +
                    "             cast(a2.id as varchar) as coverImageId,\n" +
                    "             json_agg(distinct h.*) as halls\n" +
                    "      from movies\n" +
                    "               join movie_announcements a on movies.id = a.movie_id\n" +
                    "               join movie_sessions rh on a.id = rh.movie_announcement_id\n" +
                    "               join attachments a2 on a2.id = movies.attachment_id\n" +
                    "               join (select h.id,h.name from halls h )h on h.id = rh.hall_id\n" +
                    "      group by movies.id, movies.title, a2.id)cte")
    List<String> getMoviesWithHall();

    @Query(nativeQuery = true,
            value = "select cast(json_build_object('id', cte.id, 'movieTitle', cte.movieTitle, 'coverImageId', cte.coverImageId, 'startDate',\n" +
                    "                              cte.startDate, 'halls', cte.halls) as text)\n" +
                    "from (select cast(movies.id as varchar) as id,\n" +
                    "             movies.title               as movieTitle,\n" +
                    "             cast(a2.id as varchar)     as coverImageId,\n" +
                    "             sd.date                    as startDate,\n" +
                    "             json_agg(distinct h.*)     as halls\n" +
                    "      from movies\n" +
                    "               join movie_announcements a on movies.id = a.movie_id\n" +
                    "               join movie_sessions rh on a.id = rh.movie_announcement_id\n" +
                    "               join attachments a2 on a2.id = movies.attachment_id\n" +
                    "               join session_dates sd on sd.id = rh.start_date_id\n" +
                    "               join (select h.id,r.id as movie_session_id, h.name\n" +
                    "                     from halls h\n" +
                    "                              join movie_sessions r on h.id = r.hall_id\n" +
                    "      ) h on h.id = rh.hall_id and h.movie_session_id = rh.id\n" +
                    "      group by sd.date, movies.id, movies.title, a2.id\n" +
                    "      order by sd.date) cte")
    List<String> getMoviesWithHallDate();

    @Query(nativeQuery = true,
            value = "select cast(json_build_object('startTime', cte.startDate, 'endTime',cte.endDate) as text)\n" +
                    "from(select\n" +
                    "         st.time as startDate,\n" +
                    "         s.time as endDate\n" +
                    "     from movie_sessions\n" +
                    "              join session_dates sd on sd.id = movie_sessions.start_date_id\n" +
                    "              join session_times st on st.id = movie_sessions.start_time_id\n" +
                    "              join session_times s on s.id = movie_sessions.end_time_id\n" +
                    "              join movie_announcements a on a.id = movie_sessions.movie_announcement_id\n" +
                    "              join movies m on m.id = a.movie_id\n" +
                    "     where m.id=:oafish\n" +
                    "       and movie_sessions.hall_id=:hall\n" +
                    "       and cast(sd.date as varchar)=:startDate)cte")
    List<String> getSessionTime(
            @Param("oafish") String oafish,
            @Param("hall") String hall,
            @Param("startDate") String startDate);


}
