package uz.pdp.cinemaroom.projection;

public interface TicketProjection {

    String getTicketId();
    String getMovieName();
    String getFullName();
    String getHallNumber();
    String getRowNumber();
    String getSeatNumber();
    String getStartTime();
    String getStartDate();
    double getPrice();

}
