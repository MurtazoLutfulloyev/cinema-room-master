package uz.pdp.cinemaroom.entity.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.cinemaroom.entity.absEntity.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Time;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "session_times")
@OnDelete(action = OnDeleteAction.CASCADE)
public class SessionTime extends AbsEntity {

    @ManyToOne
    private SessionDate sessionDate;

    private Time time;

    public SessionTime(Time of) {
        this.time= of;
    }
}
