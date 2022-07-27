package uz.pdp.cinemaroom.controller.rest;




import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.entity.ticket.Ticket;
import uz.pdp.cinemaroom.entity.user.User;
import uz.pdp.cinemaroom.payload.ApiResponse;
import uz.pdp.cinemaroom.repository.*;
import uz.pdp.cinemaroom.service.TicketService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TransactionHistoryRepo transactionHistoryRepo;
    @Autowired
    private WaitingPurchaseTimeRepo waitingPurchaseTimeRepo;


    @GetMapping("/checkout")
    public ResponseEntity<ApiResponse> changeTicketStatus() throws StripeException {

        Stripe.apiKey = "sk_test_51IqCFFDIEXdmza4YQKAgHhQZR67Eja7m2lmtzchNkU1uA1SKpHMGTsehAGtQbiORxihtUyB4B5GLAEznRTbaJx3M00ieeY0fhB";

        User currentUser = userRepo.findById("6cb00a48-160a-47b5-a396-4a9cd030f457").get();

        List<Ticket> userTickets = ticketRepository.getCartTicketsUser(currentUser.getId());

        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        for (Ticket userTicket : userTickets) {

            String id = userTicket.getId();
            Double price = userTicket.getPrice();

            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams
                    .LineItem.PriceData.ProductData.builder().setName(id).build();

            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder().setProductData(productData).setCurrency("usd").setUnitAmount((long) (price * 100)).build();

            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder().setPriceData(priceData).setQuantity(1L).build();
            lineItems.add(lineItem);
        }

        SessionCreateParams params = SessionCreateParams
                .builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl("http://localhost:8080/failed")
                .setSuccessUrl("http://localhost:8080/success")
                .setClientReferenceId(currentUser.getId())
                .addAllLineItem(lineItems)
                .build();

        Session session = Session.create(params);

        String checkOutUrl = session.getUrl();

        return new ResponseEntity<>(new ApiResponse(true, "success", checkOutUrl), HttpStatus.OK);

    }

    @PostMapping("/refund")
    public ResponseEntity refundTicket(@RequestBody List<String> ticketDtoList) {

        return new ResponseEntity<>(
                new
                        ApiResponse(
                        true,
                        ticketService.refundTicket(ticketDtoList),
                        null),
                HttpStatus.OK);

    }

}
