package uz.pdp.cinemaroom.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDto {

    private String receiver;
    private String subject;
    private String text;
}
