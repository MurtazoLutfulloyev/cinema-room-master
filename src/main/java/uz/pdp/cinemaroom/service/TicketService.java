package uz.pdp.cinemaroom.service;



import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.param.RefundCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cinemaroom.dto.CartDto;
import uz.pdp.cinemaroom.dto.TicketDto;
import uz.pdp.cinemaroom.entity.cinema.Seat;
import uz.pdp.cinemaroom.entity.paytype.TransactionHistory;
import uz.pdp.cinemaroom.entity.session.MovieSession;
import uz.pdp.cinemaroom.entity.session.SessionTime;
import uz.pdp.cinemaroom.entity.ticket.Cart;
import uz.pdp.cinemaroom.entity.ticket.Ticket;
import uz.pdp.cinemaroom.entity.ticket.TicketStatus;
import uz.pdp.cinemaroom.entity.user.User;
import uz.pdp.cinemaroom.projection.TicketProjection;
import uz.pdp.cinemaroom.repository.*;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MovieSessionRepo movieSessionRepo;

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private SessionTimeRepo sessionTimeRepo;

    @Autowired
    private TransactionHistoryRepo transactionHistoryRepo;

    @Transactional
    public void addToCartTicket(CartDto cartDto) {

        Cart cartFind = cartRepo.findAll().stream().filter(cart -> cart.getUser().getId().equals(cartDto.getUserId())).findFirst().orElse(null);

        Cart userCard = null;
        if (cartFind == null && userRepo.findById(cartDto.getUserId()).isPresent()) {
            User user = userRepo.findById(cartDto.getUserId()).get();
            Cart cart = new Cart();
            cart.setUser(user);
            userCard = cartRepo.save(cart);
        } else if (userRepo.findById(cartDto.getUserId()).isPresent()) {
            userCard = cartFind;
        }


        if (movieSessionRepo.findById(cartDto.getMovieSessionId()).isPresent() && seatRepo.findById(cartDto.getSeatId()).isPresent() && sessionTimeRepo.findById(cartDto.getTimeId()).isPresent()) {

            SessionTime sessionTime = sessionTimeRepo.findById(cartDto.getTimeId()).get();
            MovieSession movieSession = movieSessionRepo.findById(cartDto.getMovieSessionId()).get();
            Seat seat = seatRepo.findById(cartDto.getSeatId()).get();
            Ticket ticket = new Ticket();
            ticket.setCart(userCard);

            ticket.setPrice(seatRepo.getTicketPrice(cartDto.getMovieSessionId(), cartDto.getTimeId(), cartDto.getSeatId()));

            ticket.setStatus(TicketStatus.NEW);
            ticket.setMovieSession(movieSession);
            ticket.setSeat(seat);
            ticket.setStartTime(sessionTime.getTime());

            Ticket save = ticketRepository.save(ticket);

//            ticketScheduleTime(TicketStatus.NEW, userCard.getId(), save.getId());

        }


    }

    public List<TicketDto> getUserCartTicket(String userId) {
        return ticketRepository.getUserCartTicket(userId);
    }

    public void ticketScheduleTime(TicketStatus status, String cartId, String ticketId) {

        try {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (ticketRepository.findById(ticketId).isPresent()) {
                        if (ticketRepository.findById(ticketId).get().getStatus().equals(status)) {
                            deleteTicket(ticketId);
                        }
                    }
                }

                ;
            };

            Timer timer = new Timer();
            timer.schedule(timerTask, 20000);
        } catch (Exception ignored) {
        }

    }

    public void deleteTicket(String ticketId) {

        try {
            ticketRepository.deleteById(ticketId);
            System.out.print("Deleted Id " + ticketId);
        } catch (Exception ignored) {
        }

    }

    @Transactional
    public List<Ticket> changeTicketStatus(List<Ticket> userTickets, TicketStatus status) {

        List<Ticket> ticketList = new ArrayList<>();
        for (Ticket ticket : userTickets) {
            ticket.setStatus(status);
            ticketList.add(ticketRepository.save(ticket));
        }
        return ticketList;

    }

    @Transactional
    public void addTransactionHistory(List<Ticket> ticketList, String paymentIntentId, boolean isRefunded) {

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setDate(LocalDateTime.now());
        transactionHistory.setTicketList(ticketList);
        transactionHistory.setStripePaymentIntentId(paymentIntentId);
        transactionHistory.setAmount(ticketList.stream().mapToDouble(Ticket::getPrice).sum());
        transactionHistory.setDate(LocalDateTime.now());
        transactionHistory.setRefunded(isRefunded);
        transactionHistoryRepo.save(transactionHistory);

    }


    public String refundTicket(List<String> ticketDtoList) {

        List<Ticket> tickets = ticketRepository.findAllById(ticketDtoList);
        double sum = tickets.stream().mapToDouble(Ticket::getPrice).sum();

        String stripePaymentIntentId = transactionHistoryRepo
                .findByTicketId(ticketDtoList.get(0));

        double totalAmount = 0.0;
        for (Ticket ticket : tickets) {
            totalAmount += calculateAmount(ticketRepository.getPercent(ticket.getId()), ticket.getPrice());
        }


        System.out.println(stripePaymentIntentId);
        RefundCreateParams params = RefundCreateParams
                .builder()
                .setPaymentIntent(stripePaymentIntentId)
                .setAmount((long) (totalAmount * 100L))
                .build();


        try {
            Refund refund = Refund.create(params);

            if (refund.getStatus().equals("succeeded")) {

                changeTicketStatus(tickets, TicketStatus.REFUNDED);
                addTransactionHistory(tickets, null, true);

                return "succeeded";

            }
        } catch (StripeException e) {
            e.printStackTrace();
        }

        return "error";
    }

    private double calculateAmount(Double percent, Double price) {
        return (price - (percent * price / 100));
    }


    public TicketDto getTicketDate(String ticketId) {
        TicketProjection ticket = ticketRepository.getTicket(ticketId);
        TicketDto ticketDto = new TicketDto(
                ticket.getTicketId(),
                ticket.getMovieName(),
                ticket.getFullName(),
                ticket.getHallNumber(),
                ticket.getRowNumber(),
                ticket.getSeatNumber(),
                ticket.getStartTime(),
                ticket.getStartDate(), ticket.getPrice());
        return ticketDto;
    }
}
