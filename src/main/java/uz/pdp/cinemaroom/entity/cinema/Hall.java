package uz.pdp.cinemaroom.entity.cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "halls")
public class Hall extends AbsEntity {

    private String name;
    private Double vip_additional_fee_in_percent;

    @OneToMany(mappedBy = "hall")
    private List<Row> rows;

    public Hall(String s) {
        this.name=s;
    }

    public Hall(String s, double v) {
        this.name=s;
        this.vip_additional_fee_in_percent=v;
    }
}
