package uz.pdp.cinemaroom.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketDto {

    private String ticketId;
    private String movieName;
    private String fullName;
    private String hallNumber;
    private String rowNumber;
    private String seatNumber;
    private String startTime;
    private String startDate;
    private double price;



    @Override
    public String toString() {
        return
                "\nTicket Number : " + ticketId +
                        "\nMovie Name    : " + movieName +
                        "\nFull Name     : " + fullName +
                        "\nHall Number   : " + hallNumber +
                        "\nRow Number    :"  + rowNumber+
                        "\nSeat Number   : " + seatNumber +
                        "\nStart Time    : " + startTime +
                        "\nStart Date    : " + startDate +
                "\nPrice  :" + price;
    }
}
