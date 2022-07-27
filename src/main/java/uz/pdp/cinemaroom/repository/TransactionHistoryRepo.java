package uz.pdp.cinemaroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.paytype.TransactionHistory;

@Repository
public interface TransactionHistoryRepo extends JpaRepository<TransactionHistory, String> {

    @Query(nativeQuery = true, value = "select ph.stripe_payment_intent_id\n" + "from transaction_history ph\n" + "         join transaction_histories_tickets pht on ph.id = pht.transaction_history_id\n" + "where pht.ticket_id=:ticketId and is_refunded=false")
    String findByTicketId(String ticketId);
}
