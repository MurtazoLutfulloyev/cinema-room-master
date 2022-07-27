package uz.pdp.cinemaroom.entity.paytype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "refund_charge_fee")
public class RefundChargeFee extends AbsEntity {


    private int intervalInMinutes;
    private Double percentage;

}
