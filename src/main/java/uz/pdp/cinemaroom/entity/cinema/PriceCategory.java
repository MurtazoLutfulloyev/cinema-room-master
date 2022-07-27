package uz.pdp.cinemaroom.entity.cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;

import javax.persistence.Entity;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "price_categories")
public class PriceCategory extends AbsEntity {

    private String name;
    private Double additional_fee_in_percent;
    private String color;

    public PriceCategory(String id, Timestamp created_at, Timestamp updated_at, String name, Double additional_fee_in_percent, String color) {
        super(id, created_at, updated_at);
        this.name = name;
        this.additional_fee_in_percent = additional_fee_in_percent;
        this.color = color;
    }
}
