package uz.pdp.cinemaroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.dto.TicketDto;
import uz.pdp.cinemaroom.entity.ticket.Ticket;
import uz.pdp.cinemaroom.projection.TicketProjection;

import java.util.List;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    @Query(nativeQuery = true,
            value = "select t.id as ticketNumber,\n" +
                    "       m.title as movieName,\n" +
                    "       concat(u.first_name ,' ',u.last_name) as fullName,\n" +
                    "       h.name as hallName,\n" +
                    "       s.number as seatNumber,\n" +
                    "       st.time as startTime,\n" +
                    "       sd.date as startDate\n" +
                    "from tickets t\n" +
                    "join carts c on c.id = t.cart_id\n" +
                    "join users u on u.id = c.user_id\n" +
                    "join movie_sessions ms on ms.id = t.movie_session_id\n" +
                    "join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
                    "join movies m on m.id = ma.movie_id\n" +
                    "join halls h on h.id = ms.hall_id\n" +
                    "join seats s on s.id = t.seat_id\n" +
                    "join session_dates sd on sd.id = ms.start_date_id\n" +
                    "join session_times st on st.id = ms.start_time_id\n" +
                    "where t.id=:ticketId and u.id=:userId")
    TicketDto getTicketDetails(@Param("ticketId") String ticketId, @Param("userId") String userId);
    @Query(nativeQuery = true,
            value = "select tickets.id                             as ticketId,\n" +
                    "       m.title                                as movieName,\n" +
                    "       concat(u.first_name, ' ', u.last_name) as fullName,\n" +
                    "       h.name                                 as hallNumber,\n" +
                    "       s.number                               as seatNumber,\n" +
                    "       st.time                                as startTime,\n" +
                    "       sd.date                                as startDate,\n" +
                    "       tickets.price                          as price\n" +
                    "from tickets\n" +
                    "         join carts c on c.id = tickets.cart_id\n" +
                    "         join movie_sessions ms on ms.id = tickets.movie_session_id\n" +
                    "         join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
                    "         join movies m on m.id = ma.movie_id\n" +
                    "         join users u on u.id = c.user_id\n" +
                    "         join halls h on h.id = ms.hall_id\n" +
                    "         join seats s on s.id = tickets.seat_id\n" +
                    "         join session_times st on st.id = ms.start_time_id\n" +
                    "         join session_dates sd on sd.id = ms.start_date_id\n" +
                    "where c.user_id =:userId and tickets.status='NEW';")
    List<TicketDto> getUserCartTicket(String userId);

    @Query(nativeQuery = true, value = "select sum(t.price)\n" +
            " from tickets t\n" +
            " join carts c on c.id = t.cart_id \n" +
            " where c.user_id=:userId and t.status=:ticketStatus")
    String getTotalAmountOfTickets(@Param("userId") String userId,
                                   @Param("ticketStatus") String ticketStatus);

    @Query(nativeQuery = true,
            value = "select tickets.*\n" +
                    "from tickets\n" +
                    "join carts c on c.id = tickets.cart_id\n" +
                    "where user_id=:userId and status='NEW';")
    List<Ticket> getCartTicketsUser(String userId);

    @Query(nativeQuery = true,
            value = "with t as (\n" +
                    "    select (cast(sd.date as text) || ' ' || st.time):: timestamp\n" +
                    "    from tickets t\n" +
                    "             join movie_sessions ms on ms.id = t.movie_session_id\n" +
                    "             join session_dates sd on sd.id = ms.start_date_id\n" +
                    "             join session_times st on st.id = ms.start_time_id\n" +
                    "    where t.id =:ticketId\n" +
                    ")\n" +
                    "select coalesce(percentage,0.0)\n" +
                    "from refund_charge_fee rf\n" +
                    "where extract(epoch from ((select * from t) - now()) / 60) > rf.interval_in_minutes")
    Double getPercent(String ticketId);
    @Query(nativeQuery = true,
    value = "select tickets.id as ticketId,\n" +
            "       m.title as movieName,\n" +
            "       concat(u.first_name,' ',u.last_name) as fullName,\n" +
            "       h.name as hallNumber,\n" +
            "       ha.number as rowNumber,\n" +
            "       s.number as seatNumber,\n" +
            "       st.time as startTime,\n" +
            "       sd.date as startDate," +
            "       tickets.price as price\n" +
            "\n" +
            "\n" +
            "\n" +
            "from tickets\n" +
            "         join movie_sessions ms on ms.id = tickets.movie_session_id\n" +
            "         join movie_announcements ma on ma.id = ms.movie_announcement_id\n" +
            "         join movies m on m.id = ma.movie_id\n" +
            "         join carts c on c.id = tickets.cart_id\n" +
            "         join users u on u.id = c.user_id\n" +
            "         join halls h on h.id = ms.hall_id\n" +
            "         join seats s on s.id = tickets.seat_id\n" +
            "    join hall_rows ha on h.id = ha.hall_id\n" +
            "         join session_times st on st.id = ms.start_time_id\n" +
            "         join session_dates sd on sd.id = ms.start_date_id\n" +
            "where tickets.id=:ticketId")
    TicketProjection getTicket(String ticketId);
}
