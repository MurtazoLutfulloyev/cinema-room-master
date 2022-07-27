package uz.pdp.cinemaroom.entity.paytype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;
import uz.pdp.cinemaroom.entity.ticket.Ticket;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "transaction_history")
@OnDelete(action = OnDeleteAction.CASCADE)
public class TransactionHistory extends AbsEntity {


    @ManyToMany
    @JoinTable(name = "transaction_histories_tickets",
            joinColumns = @JoinColumn(name = "transaction_history_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"))
    private List<Ticket> ticketList;

    private Double amount;

    private boolean isRefunded;

    private LocalDateTime date;

    @ManyToOne
    private PayType payType;

    private String stripePaymentIntentId;

}
