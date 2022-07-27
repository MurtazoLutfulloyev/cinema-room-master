package uz.pdp.cinemaroom.service.email;


public interface EmailSender {
    void send(String subject, String text);
}
