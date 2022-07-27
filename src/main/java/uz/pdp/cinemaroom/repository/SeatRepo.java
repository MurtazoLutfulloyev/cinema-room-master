package uz.pdp.cinemaroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.cinema.Seat;
import uz.pdp.cinemaroom.projection.SeatProjection;

import java.util.List;

@Repository
public interface SeatRepo extends JpaRepository<Seat, String> {

    @Query(nativeQuery = true,
            value = "select seats.id                                                    as id,\n" +
                    "       seats.number                                                as number,\n" +
                    "       pc.name                                                     as type,\n" +
                    "       sum((1 + pc.additional_fee_in_percent / 100) * m.min_price) as price,\n" +
                    "       h.name                                                      as hall\n" +
                    "from seats\n" +
                    "         join price_categories pc on pc.id = seats.price_category_id\n" +
                    "         join hall_rows r on r.id = seats.row_id\n" +
                    "         join halls h on r.hall_id = h.id\n" +
                    "         join movie_sessions rh on h.id = rh.hall_id\n" +
                    "         join session_times st on st.id = rh.start_time_id\n" +
                    "         join tickets t on rh.id = t.movie_session_id\n" +
                    "         join movie_announcements ma on ma.id = rh.movie_announcement_id\n" +
                    "         join movies m on m.id = ma.movie_id\n" +
                    "\n" +
                    "where rh.id = :movieSessionId\n" +
                    "  and rh.start_time_id = :startTimeId\n" +
                    "  and seats.id <> t.seat_id and t.status='REFUNDED' \n" +
                    "group by h.name, pc.name, seats.number, seats.id")
    List<SeatProjection> getAvailableSeats(@Param("movieSessionId") String movieSessionId,
                                           @Param("startTimeId") String startTimeId);


    @Query(nativeQuery = true,
            value = "select sum((1 + pc.additional_fee_in_percent / 100) * m.min_price)\n" +
                    "from seats\n" +
                    "         join price_categories pc on pc.id = seats.price_category_id\n" +
                    "         join hall_rows r on r.id = seats.row_id\n" +
                    "         join halls h on r.hall_id = h.id\n" +
                    "         join movie_sessions rh on h.id = rh.hall_id\n" +
                    "         join session_times st on st.id = rh.start_time_id\n" +
                    "         join tickets t on rh.id = t.movie_session_id\n" +
                    "         join movie_announcements ma on ma.id = rh.movie_announcement_id\n" +
                    "         join movies m on m.id = ma.movie_id\n" +
                    "\n" +
                    "where rh.id = :movieSessionId\n" +
                    "  and rh.start_time_id = :startTimeId\n" +
                    "  and seats.id = :seatId")
    Double getTicketPrice(@Param("movieSessionId") String movieSessionId,
                          @Param("startTimeId") String startTimeId,
                          @Param("seatId") String seatId);
}
