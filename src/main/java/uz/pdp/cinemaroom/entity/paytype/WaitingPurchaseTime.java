package uz.pdp.cinemaroom.entity.paytype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "waiting_purchase_time")
public class WaitingPurchaseTime extends AbsEntity {

    private Long minute;

}
