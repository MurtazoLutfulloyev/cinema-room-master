package uz.pdp.cinemaroom.entity.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;
import uz.pdp.cinemaroom.entity.attachment.Attachment;
import uz.pdp.cinemaroom.entity.cinema.Seat;
import uz.pdp.cinemaroom.entity.session.MovieAnnouncement;
import uz.pdp.cinemaroom.entity.session.MovieSession;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tickets")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Ticket extends AbsEntity {

    private Double price;

    @OneToOne
    private Attachment qrCode;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @ManyToOne
    private Cart cart;

    @OneToOne
    private MovieSession movieSession;

    @OneToOne
    private Seat seat;

    private Time startTime;

}
