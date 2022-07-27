package uz.pdp.cinemaroom.helper;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
public class LoadPDF {


    public InputStream getPDF() {

        try {
            File file = ResourceUtils.getFile("src/main/resources/ticket.pdf");
            InputStream in = new FileInputStream(file);
            return in;
        } catch (IOException e) {
        }
        return null;
    }

}
