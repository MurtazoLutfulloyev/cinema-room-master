//package uz.pdp.cinemaroom.common;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import uz.pdp.cinemaroom.entity.attachment.Attachment;
//import uz.pdp.cinemaroom.entity.cinema.Hall;
//import uz.pdp.cinemaroom.entity.cinema.PriceCategory;
//import uz.pdp.cinemaroom.entity.cinema.Row;
//import uz.pdp.cinemaroom.entity.cinema.Seat;
//import uz.pdp.cinemaroom.entity.enums.Gender;
//import uz.pdp.cinemaroom.entity.movie.Movie;
//import uz.pdp.cinemaroom.entity.session.MovieAnnouncement;
//import uz.pdp.cinemaroom.entity.session.MovieSession;
//import uz.pdp.cinemaroom.entity.session.SessionDate;
//import uz.pdp.cinemaroom.entity.session.SessionTime;
//import uz.pdp.cinemaroom.entity.user.Role;
//import uz.pdp.cinemaroom.entity.user.User;
//import uz.pdp.cinemaroom.entity.user.UserDetail;
//import uz.pdp.cinemaroom.repository.*;
//
//import java.sql.Time;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.*;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//
//    @Value("${spring.sql.init.mode}")
//    String initMode;
//
//    final PriceCategoryRepo priceCategoryRepository;
//    final SeatRepo seatRepository;
//    final RowRepo rowRepository;
//    final HallRepo hallRepository;
//    final AttachmentRepo attachmentRepository;
//    final MovieRepo movieRepository;
//    final SessionDateRepo sessionDateRepository;
//    final SessionTimeRepo sessionTimeRepository;
//    final MovieAnnouncementRepo movieAnnouncementRepository;
//    final MovieSessionRepo movieSessionRepository;
//
//    final RoleRepo roleRepo;
////
////    @Autowired
////    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private UserRepo userRepo;
//
//    public DataLoader(PriceCategoryRepo priceCategoryRepository, SeatRepo seatRepository, RowRepo rowRepository, HallRepo hallRepository, AttachmentRepo attachmentRepository, MovieRepo movieRepository, SessionDateRepo sessionDateRepository, SessionTimeRepo sessionTimeRepository, MovieAnnouncementRepo movieAnnouncementRepository, MovieSessionRepo movieSessionRepository, RoleRepo roleRepo) {
//        this.priceCategoryRepository = priceCategoryRepository;
//        this.seatRepository = seatRepository;
//        this.rowRepository = rowRepository;
//        this.hallRepository = hallRepository;
//        this.attachmentRepository = attachmentRepository;
//        this.movieRepository = movieRepository;
//        this.sessionDateRepository = sessionDateRepository;
//        this.sessionTimeRepository = sessionTimeRepository;
//        this.movieAnnouncementRepository = movieAnnouncementRepository;
//        this.movieSessionRepository = movieSessionRepository;
//        this.roleRepo = roleRepo;
//    }
//
//
//    @Override
//    public void run(String... args) throws Exception {
//
////        Role role_admin = roleRepo.save(new Role("ROLE_ADMIN"));
////        Role role_user = roleRepo.save(new Role("ROLE_USER"));
////
////        Set<Role> roles = new HashSet<>();
////        roles.add(role_admin);
////        roles.add(role_user);
////
////        Set<Role> roles_user = new HashSet<>();
////        roles_user.add(role_user);
////
////        userRepo.save(
////                new User("asadbek",
////                        passwordEncoder.encode("1111"),
////                        new Date(2000,15,01),
////                        Gender.MALE,
////                        new UserDetail("Asadbek","Xalimjonov"),
////                        roles
////                        )
////        );
////
////        userRepo.save(
////                new User("eldor",
////                        passwordEncoder.encode("1111"),
////                        new Date(1999,15,01),
////                        Gender.MALE,
////                        new UserDetail("Eldor","Choriyev"),
////                        roles_user
////                )
////        );
//
//        if (initMode.equals("never")) {
//
//            //PRICE CATEGORY LIST CREATE QILINADI
//            List<PriceCategory> priceCategories = new ArrayList<>();
//            for (int i = 0; i < 4; i++) {
//                priceCategories.add(new PriceCategory(
//                        "Cat" + (i + 1),
//                        (2.0 * i),
//                        "#" + i + i + i
//                ));
//            }
//            priceCategoryRepository.saveAll(priceCategories);
//
//
//            // HALL OBJ CREATE QILINADI
//            Hall zal1 = new Hall("Zal 1");
//            Hall zal2 = new Hall("Zal 2");
//            Hall zal3Vip = new Hall("Zal 3 VIP", 20.0);
//
//
//            //ROW LIST CREATE QILINADI
//            //================ZAL1 ROWS=====================
//            List<Row> rowList1 = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                Row rowN = new Row(i + 1, zal1);
//                //SEAT LIST CREATE QILINADI
//                List<Seat> seats = new ArrayList<>();
//                for (int j = 0; j < 10; j++) {
//                    int priceCatIndex = j < 3 ? 1 : j < 6 ? 2 : 3;
//                    seats.add(new Seat(j + 1, rowN, priceCategories.get(priceCatIndex)));
//                }
//                rowN.setSeats(seats);
//                rowList1.add(rowN);
//            }
//            //=================ZAL2 ROWS====================
//            List<Row> rowList2 = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                Row rowN = new Row(i + 1, zal2);
//                //SEAT LIST CREATE QILINADI
//                List<Seat> seats = new ArrayList<>();
//                for (int j = 0; j < 10; j++) {
//                    int priceCatIndex = j < 3 ? 1 : j < 6 ? 2 : 3;
//                    seats.add(new Seat(j + 1, rowN, priceCategories.get(priceCatIndex)));
//                }
//                rowN.setSeats(seats);
//                rowList2.add(rowN);
//            }
//            //=================ZAL3VIP ROWS====================
//            List<Row> rowList3 = new ArrayList<>();
//            for (int i = 0; i < 10; i++) {
//                Row rowN = new Row(i + 1, zal3Vip);
//                //SEAT LIST CREATE QILINADI
//                List<Seat> seats = new ArrayList<>();
//                for (int j = 0; j < 10; j++) {
//                    int priceCatIndex = j < 3 ? 1 : j < 6 ? 2 : 3;
//                    seats.add(new Seat(j + 1, rowN, priceCategories.get(priceCatIndex)));
//                }
//                rowN.setSeats(seats);
//                rowList3.add(rowN);
//            }
//
//            zal1.setRows(rowList1);
//            zal2.setRows(rowList2);
//            zal3Vip.setRows(rowList3);
//
//            // ZAL1, ZAL2, ZAL3VIP DB GA SAQLANADI
//            hallRepository.save(zal1);
//            hallRepository.save(zal2);
//            hallRepository.save(zal3Vip);
//
//
//            //attachment img
//            Attachment movieImg = attachmentRepository.save(new Attachment("movieImg", "image/jpg", 100000L));
//            // MOVIES
//
//            Movie movie1 = new Movie("Batman", "dsgagadsgasgasdg", 120, 50000.0, movieImg, "youtube.com",
//                    LocalDate.now()
//            );
//            Movie movie2 = new Movie("Spiderman", "zxcvzxcv cbvxvxcbxxcv dgfshdfghdfghfg", 110, 40000.0, movieImg, "youtube.com", LocalDate.now());
//            Movie movie3 = new Movie("Superman", "xzcvzcx teyrtyuru bxcxbvcx", 90, 45000.0, movieImg, "youtube.com", LocalDate.now());
//            movieRepository.save(movie1);
//            Movie spiderman = movieRepository.save(movie2);
//            Movie superman = movieRepository.save(movie3);
//
//            // SESSION DATES
//            SessionDate may17 = new SessionDate(LocalDate.of(2022, 5, 17));
//
//            SessionDate may18 = new SessionDate(LocalDate.of(2022, 5, 18));
//            SessionDate may19 = new SessionDate(LocalDate.of(2022, 5, 19));
//            sessionDateRepository.save(may17);
//            sessionDateRepository.save(may18);
//            sessionDateRepository.save(may19);
//
//            //SESSION TIMES
//            SessionTime hour11 = new SessionTime(Time.valueOf(LocalTime.now()));
//            SessionTime hour13 = new SessionTime(Time.valueOf(LocalTime.of(13, 0)));
//            SessionTime hour15 = new SessionTime(Time.valueOf(LocalTime.of(15, 0)));
//            SessionTime hour18 = new SessionTime(Time.valueOf(LocalTime.of(18, 0)));
//            sessionTimeRepository.save(hour11);
//            sessionTimeRepository.save(hour13);
//            sessionTimeRepository.save(hour15);
//            sessionTimeRepository.save(hour18);
//
//            //MOVIE ANNOUNCEMENTS
//            MovieAnnouncement batmanAfisha = movieAnnouncementRepository.save(
//                    new MovieAnnouncement(movie1, true));
//            MovieAnnouncement spidermanAfisha = movieAnnouncementRepository.save(
//                    new MovieAnnouncement(spiderman, true));
//            MovieAnnouncement supermanAfisha = movieAnnouncementRepository.save(
//                    new MovieAnnouncement(superman, true));
//
//            // MOVIE SESSIONS
//
//            movieSessionRepository.save(
//                    new MovieSession(
//                            batmanAfisha,
//                            zal1,
//                            may18,
//                            hour11,
//                            hour13
//                    )
//            );
//            movieSessionRepository.save(
//                    new MovieSession(
//                            batmanAfisha,
//                            zal1,
//                            may18,
//                            hour15,
//                            hour18
//                    )
//            );
//            movieSessionRepository.save(
//                    new MovieSession(
//                            spidermanAfisha,
//                            zal3Vip,
//                            may18,
//                            hour15,
//                            hour18
//                    )
//            );
//
//            movieSessionRepository.save(
//                    new MovieSession(
//                            spidermanAfisha,
//                            zal2,
//                            may19,
//                            hour11,
//                            hour13
//                    )
//            );
//            movieSessionRepository.save(
//                    new MovieSession(
//                            spidermanAfisha,
//                            zal2,
//                            may19,
//                            hour15,
//                            hour18
//                    )
//            );
//
//            movieSessionRepository.save(
//                    new MovieSession(
//                            supermanAfisha,
//                            zal3Vip,
//                            may19,
//                            hour11,
//                            hour13
//                    )
//            );
//
//        }
//    }
//}
//
