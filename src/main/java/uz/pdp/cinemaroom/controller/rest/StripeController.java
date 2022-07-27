package uz.pdp.cinemaroom.controller.rest;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemaroom.dto.EmailDto;
import uz.pdp.cinemaroom.entity.ticket.Ticket;
import uz.pdp.cinemaroom.entity.ticket.TicketStatus;
import uz.pdp.cinemaroom.repository.TicketRepository;
import uz.pdp.cinemaroom.stripePayload.Object;
import uz.pdp.cinemaroom.stripePayload.StripeData;
import uz.pdp.cinemaroom.service.SendEmailMessage;
import uz.pdp.cinemaroom.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/webhook")
public class StripeController {

    @Autowired
    private TicketService ticketService;


    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SendEmailMessage sendEmailMessage;

    String endpointSecret = "whsec_3ec031468db0a062dc15e184af0fabbce09adb2da5965eff8648edaaff40f4f4";

    @PostMapping
    public Object handle(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {

        System.out.println("Got payload: " + payload);

        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (Exception e) {
            return null;
        }

        if ("checkout.session.completed".equals(event.getType())) {
            System.out.printf(String.valueOf(event));

            StripeData stripeData = null;
            String email = "";
            try {
                JsonObject rawJsonObject = event.getRawJsonObject();
                Gson gson = new Gson();
                stripeData = gson.fromJson(event.toJson(), StripeData.class);
                System.out.printf(stripeData.getData().getObject().getCustomerDetails().getEmail().toString());
                email = stripeData.getData().getObject().getCustomerDetails().getEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();
            fulfillOrder(session, email);
        }

        return null;
    }



    public void fulfillOrder(Session session, String email) {


        EmailDto emailDto = new EmailDto();
        emailDto.setReceiver(email);

        String paymentIntentId = session.getPaymentIntent();
        String userId = session.getClientReferenceId();

        List<Ticket> cartTicketsUser = ticketRepository.getCartTicketsUser(userId);
        sendEmailMessage.sendEmail(emailDto, cartTicketsUser);


        List<Ticket> ticketList = ticketService.changeTicketStatus(cartTicketsUser, TicketStatus.PURCHASED);
        ticketService.addTransactionHistory(ticketList, paymentIntentId, false);


        System.out.println("Payment Intent....." + session.getPaymentIntent());
        System.out.println("Fulfilling order..." + session.getClientReferenceId());
    }

}
