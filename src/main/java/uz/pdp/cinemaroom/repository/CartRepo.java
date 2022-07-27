package uz.pdp.cinemaroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.cinemaroom.entity.ticket.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, String> {
}
