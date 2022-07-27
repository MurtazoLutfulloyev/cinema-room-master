package uz.pdp.cinemaroom.entity.cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "hall_rows")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Row extends AbsEntity {

    private Integer number;

    @OneToMany(mappedBy = "row")
    private List<Seat> seats;

    @ManyToOne
    private Hall hall;

    public Row(Integer number,Hall hall) {
        this.number = number;
        this.hall = hall;
    }
}
